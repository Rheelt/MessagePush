package push.remoting.message.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import push.remoting.message.Packet;
import push.remoting.message.PacketConstants;

/**
 * (magicNum)4 byte + (version)1 byte + (serializeAlgorithm)1 byte + (bodyLen)4 byte + (body)bodyLen byte
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) {
        if (!Packet.isValid(msg)) {
            return;
        }
        out.writeInt(PacketConstants.MAGIC_NUM);
        out.writeByte(msg.getVersion().code);
        out.writeByte(msg.getSerializeAlgorithm().code);
        out.writeInt(msg.getBody().length);
        out.writeBytes(msg.getBody());
        System.out.println("send : " + msg.toString());
    }
}
