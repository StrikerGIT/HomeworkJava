package GeekCloud;

import java.io.Serializable;

public class MyMessage implements Serializable {
    private static final long serialVersionUID = 5193392663743561680L;
    private String text;
    private MessageType messageType;

    public String getText() {
        return text;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public MyMessage(String text, MessageType messageType) {
        this.text = text;
        this.messageType = messageType;
    }
}
