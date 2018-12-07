package im.heart.wechat.model.message.receive.msg;

import im.heart.wechat.model.message.receive.RecvMessage;
import lombok.Data;

@Data
public class RecvMsg extends RecvMessage {

    /**
     * 消息ID
     */
    protected Long msgId;
}