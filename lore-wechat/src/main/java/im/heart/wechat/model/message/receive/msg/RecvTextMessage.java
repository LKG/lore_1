package im.heart.wechat.model.message.receive.msg;

import lombok.Data;

@Data
public class RecvTextMessage extends RecvMsg {

    private static final long serialVersionUID = -8070100690774814611L;

    /**
     * 文本内容
     */
    private String content;

}
