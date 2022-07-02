package push.remoting.message.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import push.remoting.message.Packet;
import push.remoting.message.PacketConstants;
import push.remoting.message.PacketVersion;
import push.remoting.message.SerializeAlgorithm;

import java.util.List;

public class PacketDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        if (msg.readableBytes() < PacketConstants.PACKET_MIN_LEN) {
            return;
        }

        Packet packet = new Packet();
        packet.setMagicNun(msg.readInt());
        packet.setVersion(PacketVersion.getInstance(msg.readByte()));
        packet.setSerializeAlgorithm(SerializeAlgorithm.getInstance(msg.readByte()));
        int len = msg.readInt();
        if (len < 0) {
            ctx.channel().close();
            throw new RuntimeException("len = " + len + " is invalid.");
        }
        byte[] body = new byte[len];
        msg.readBytes(body);
        packet.setBody(body);
        out.add(packet);
        System.out.println("receive : " + packet);
    }
}
