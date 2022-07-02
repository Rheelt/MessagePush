package push.remoting.message;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@Accessors(chain = true)
@ToString
public class Packet {
    public static int MAGIC_NUM = 0xffff;

    private Integer magicNun;

    private PacketVersion version;

    private SerializeAlgorithm serializeAlgorithm;

    private byte[] body;

    public static boolean isValid(Packet packet) {
        return Objects.nonNull(packet);
    }
}
