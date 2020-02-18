package GeekCloud;

public enum MessageType {
        EMPTY((byte)-1), COMMAND((byte)7), FILE((byte)8), TEXT((byte) 9);

        byte firstMessageByte;

    MessageType(byte firstMessageByte) {
            this.firstMessageByte = firstMessageByte;
        }

        static MessageType getDataTypeFromByte(byte b) {
            if (b == FILE.firstMessageByte) {
                return FILE;
            }
            if (b == COMMAND.firstMessageByte) {
                return COMMAND;
            }
            return EMPTY;
        }

}
