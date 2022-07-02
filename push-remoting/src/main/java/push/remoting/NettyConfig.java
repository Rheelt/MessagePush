package push.remoting;

import lombok.Data;

@Data
public class NettyConfig {
    private int port = 8888;

    private String ip;

    private int parentThreadCount = 1;

    private int childThreadCount = Runtime.getRuntime().availableProcessors() * 2 + 1;

    private boolean keepAlive = true;

    private boolean tcpNoDelay = true;
}
