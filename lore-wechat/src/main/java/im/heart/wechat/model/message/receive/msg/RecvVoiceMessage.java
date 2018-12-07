package im.heart.wechat.model.message.receive.msg;

import lombok.Data;

@Data
public class RecvVoiceMessage extends RecvMsg {
    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String mediaId;

    /**
     * 语音格式，如amr，speex等
     */
    private String format;

    /**
     * 语音识别结果，使用UTF8编码
     */
    private String recognition;

}
