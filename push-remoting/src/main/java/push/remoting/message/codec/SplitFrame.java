package push.remoting.message.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import push.remoting.message.PacketConstants;

public class SplitFrame extends LengthFieldBasedFrameDecoder {
    public SplitFrame() {
        super(Integer.MAX_VALUE, PacketConstants.PACKET_OFFSET, PacketConstants.PACKET_BODY_LEN);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf byteBuf = (ByteBuf) super.decode(ctx, in);
        byteBuf.markReaderIndex();
        if (byteBuf.readInt() != PacketConstants.MAGIC_NUM) {
            ctx.channel().close();
            return null;
        }
        byteBuf.resetReaderIndex();

        return byteBuf;
    }
}
