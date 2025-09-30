package com.mucheng.mucute.relay

import com.mucheng.mucute.relay.address.MuCuteAddress
import io.netty.bootstrap.Bootstrap
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.*
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioDatagramChannel
import org.cloudburstmc.netty.channel.raknet.RakChannelFactory
import org.cloudburstmc.netty.channel.raknet.config.RakChannelOption
import org.cloudburstmc.protocol.bedrock.BedrockPeer
import org.cloudburstmc.protocol.bedrock.BedrockPong
import org.cloudburstmc.protocol.bedrock.PacketDirection
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec
import org.cloudburstmc.protocol.bedrock.codec.v776.Bedrock_v776
import org.cloudburstmc.protocol.bedrock.netty.initializer.BedrockChannelInitializer
import kotlin.random.Random

class MuCuteRelay(
    private val localAddress: MuCuteAddress = MuCuteAddress("0.0.0.0", 19132),
    private val advertisement: BedrockPong = Companion.DefaultAdvertisement
) {
    private var channelFuture: ChannelFuture? = null
    private var muCuteRelaySession: MuCuteRelaySession? = null
    private var remoteAddress: MuCuteAddress? = null
    private val eventLoopGroup: EventLoopGroup = NioEventLoopGroup()

    val isRunning: Boolean
        get() = channelFuture != null

    /**
     * Start the relay server and capture connection.
     */
    fun capture(
        remoteAddress: MuCuteAddress = MuCuteAddress("geo.hivebedrock.network", 19132),
        onSessionCreated: (MuCuteRelaySession) -> Unit
    ): MuCuteRelay {
        if (isRunning) return this

        this.remoteAddress = remoteAddress
        advertisement.ipv4Port(localAddress.port).ipv6Port(localAddress.port)

        val serverBootstrap = ServerBootstrap()
            .group(eventLoopGroup)
            .channelFactory(RakChannelFactory.server(NioDatagramChannel::class.java))
            .option(RakChannelOption.RAK_ADVERTISEMENT, advertisement.toByteBuf())
            .option(RakChannelOption.RAK_GUID, Random.nextLong())
            .childHandler(object : BedrockChannelInitializer<MuCuteRelaySession.ServerSession>() {
                override fun createSession0(peer: BedrockPeer, subClientId: Int): MuCuteRelaySession.ServerSession {
                    val session = MuCuteRelaySession(peer, subClientId, this@MuCuteRelay)
                    this@MuCuteRelay.muCuteRelaySession = session
                    onSessionCreated(session)
                    return session.server
                }

                override fun initSession(session: MuCuteRelaySession.ServerSession) {
                    // No additional initialization
                }

                override fun preInitChannel(channel: Channel) {
                    channel.attr(PacketDirection.ATTRIBUTE).set(PacketDirection.CLIENT_BOUND)
                    super.preInitChannel(channel)
                }
            })

        val address = localAddress
        val future = serverBootstrap.localAddress(address.hostName, address.port).bind().awaitUninterruptibly()
        future.channel().pipeline().remove("rak-server-rate-limiter")
        this.channelFuture = future
        return this
    }

    /**
     * Connect as a client to the remote relay server.
     */
    fun connectToServer(onSessionCreated: (MuCuteRelaySession.ClientSession) -> Unit) {
        val clientGUID = Random.nextLong()
        val session = muCuteRelaySession
            ?: throw IllegalStateException("Relay session not established. Call capture() first.")

        val bootstrap = Bootstrap()
            .group(eventLoopGroup)
            .channelFactory(RakChannelFactory.client(NioDatagramChannel::class.java))
            .option(RakChannelOption.RAK_PROTOCOL_VERSION, session.server.codec.raknetProtocolVersion)
            .option(RakChannelOption.RAK_GUID, clientGUID)
            .option(RakChannelOption.RAK_REMOTE_GUID, clientGUID)
            .option(RakChannelOption.RAK_CONNECT_TIMEOUT, 200_000L)
            .handler(object : BedrockChannelInitializer<MuCuteRelaySession.ClientSession>() {
                override fun createSession0(peer: BedrockPeer, subClientId: Int): MuCuteRelaySession.ClientSession {
                    return session.ClientSession(peer, subClientId)
                }

                override fun initSession(clientSession: MuCuteRelaySession.ClientSession) {
                    onSessionCreated(clientSession)
                }

                override fun preInitChannel(channel: Channel) {
                    channel.attr(PacketDirection.ATTRIBUTE).set(PacketDirection.SERVER_BOUND)
                    super.preInitChannel(channel)
                }
            })

        val address = remoteAddress
            ?: throw IllegalStateException("Remote address not specified. Call capture() first.")
        bootstrap.remoteAddress(address.hostName, address.port).connect().awaitUninterruptibly()
    }

    /**
     * Disconnects the relay server and cleans up.
     */
    fun disconnect() {
        if (isRunning) {
            channelFuture?.channel()?.let { channel ->
                channel.close().awaitUninterruptibly()
                channel.parent()?.close()?.awaitUninterruptibly()
            }
            channelFuture = null
            muCuteRelaySession = null
        }
    }

    companion object {
        val DefaultCodec: BedrockCodec = Bedrock_v776.CODEC
        val DefaultAdvertisement: BedrockPong = BedrockPong().apply {
            edition("MCPE")
            gameType("Survival")
            version(DefaultCodec.minecraftVersion)
            protocolVersion(DefaultCodec.protocolVersion)
            motd("MuCuteRelay")
            playerCount(0)
            maximumPlayerCount(20)
            subMotd("t.me/mucuteclient")
            nintendoLimited(false)
        }
    }
}