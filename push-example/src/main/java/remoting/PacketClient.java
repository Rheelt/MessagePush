package remoting;

import push.remoting.NettyConfig;
import push.remoting.client.Client;
import push.remoting.message.Packet;
import push.remoting.message.PacketConstants;
import push.remoting.message.PacketVersion;
import push.remoting.message.SerializeAlgorithm;

public class PacketClient {
    public static void main(String[] args) {
        NettyConfig config = new NettyConfig();
        Packet packet = new Packet();
        packet.setMagicNun(PacketConstants.MAGIC_NUM);
        packet.setVersion(PacketVersion.VERSION_1_0);
        packet.setSerializeAlgorithm(SerializeAlgorithm.JSON);
        packet.setBody(new byte[]{(byte)1,(byte)1,(byte)1});
        config.setIp("127.0.0.1");
        config.setPort(8888);
        new Thread(() -> {
            Client client = new Client(config);
            client.init();
            try {
                client.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            client.getChannel().writeAndFlush(packet);
        }).start();
    }
}
