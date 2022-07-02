package push.remoting.message;

public class PacketConstants {
    public static final int MAGIC_NUM = 0xffff;

    public static final int PACKET_OFFSET = 6;

    public static final int PACKET_BODY_LEN = 4;

    public static final int PACKET_MIN_LEN = PACKET_OFFSET + PACKET_BODY_LEN + 1;
}
