package remoting;

import push.remoting.NettyConfig;
import push.remoting.client.Client;
import push.remoting.message.Packet;
import push.remoting.message.PacketConstants;
import push.remoting.message.PacketVersion;
import push.remoting.message.SerializeAlgorithm;
import push.remoting.server.Server;

public class PacketServer {
    public static void main(String[] args) throws InterruptedException {
        NettyConfig config = new NettyConfig();
        config.setIp("127.0.0.1");
        config.setPort(8888);
        new Thread(() -> {
            Server server = new Server(config);
            server.init();
            try {
                server.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
