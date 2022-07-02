package push.remoting.message;

import push.remoting.exception.VersionException;

public enum PacketVersion {
    VERSION_1_0((byte) 0, "1.0");

    public byte code;

    public String description;

    public static PacketVersion getInstance(byte code) {
        for (PacketVersion version : values()) {
            if (version.code == code) {
                return version;
            }
        }

        throw new VersionException("invalid exception code = " + code);
    }

    private PacketVersion(byte code, String description) {
        this.code = code;
        this.description = description;
    }
}
