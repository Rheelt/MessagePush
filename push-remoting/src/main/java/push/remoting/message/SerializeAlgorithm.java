package push.remoting.message;

import push.remoting.exception.VersionException;

public enum SerializeAlgorithm {
    JSON((byte) 0, "json");
    public byte code;
    public String description;

    public static SerializeAlgorithm getInstance(byte code) {
        for (SerializeAlgorithm algorithm : values()) {
            if (algorithm.code == code) {
                return algorithm;
            }
        }

        throw new VersionException("invalid exception code = " + code);
    }

    SerializeAlgorithm(byte code, String description) {
        this.code = code;
        this.description = description;
    }
}
