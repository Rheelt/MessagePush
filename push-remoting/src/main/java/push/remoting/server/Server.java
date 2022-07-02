package push.remoting.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import push.remoting.AbstractLifeCycle;
import push.remoting.NettyConfig;
import push.remoting.message.codec.PacketDecoder;
import push.remoting.message.codec.PacketEncoder;
import push.remoting.message.codec.SplitFrame;

public class Server extends AbstractLifeCycle {
    private ServerBootstrap bootstrap;

    private NioEventLoopGroup parentGroup;

    private NioEventLoopGroup childGroup;

    private NettyConfig config;

    public Server(NettyConfig config) {
        this.config = config;
    }

    public void init() {
        bootstrap = new ServerBootstrap();
        parentGroup = new NioEventLoopGroup(config.getParentThreadCount());
        childGroup = new NioEventLoopGroup(config.getChildThreadCount());
    }

    public void start() throws InterruptedException {
        bootstrap.group(parentGroup, childGroup).
                channel(NioServerSocketChannel.class).
                handler(new LoggingHandler(LogLevel.INFO)).
                childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new SplitFrame()).
                                addLast(new PacketDecoder()).
                                addLast(new PacketEncoder());

                    }
                });

        ChannelFuture future = bootstrap.bind(config.getPort());
        future.sync().channel().closeFuture().addListeners((ChannelFutureListener) future1 -> {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        });
    }
}
