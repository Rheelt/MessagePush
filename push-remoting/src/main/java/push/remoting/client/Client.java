package push.remoting.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.Data;
import push.remoting.NettyConfig;
import push.remoting.message.codec.PacketDecoder;
import push.remoting.message.codec.PacketEncoder;
import push.remoting.message.codec.SplitFrame;

@Data
public class Client {
    private Bootstrap bootstrap;

    private NioEventLoopGroup workGroup;

    private NettyConfig config;

    private Channel channel;

    public Client(NettyConfig config) {
        this.config = config;
    }

    public void start() throws InterruptedException {
        bootstrap.group(workGroup).handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) {
                ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO)).addLast(new SplitFrame()).
                        addLast(new PacketDecoder()).
                        addLast(new PacketEncoder());
            }
        });
        ChannelFuture future = bootstrap.channel(NioSocketChannel.class).connect(config.getIp(), config.getPort()).sync();
        channel = future.channel();
        future.channel().closeFuture().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                workGroup.shutdownGracefully();
            }
        });
    }

    public void init() {
        bootstrap = new Bootstrap();
        workGroup = new NioEventLoopGroup(config.getParentThreadCount());
    }
}
