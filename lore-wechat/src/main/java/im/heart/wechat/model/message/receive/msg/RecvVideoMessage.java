package im.heart.wechat.model.message.receive.msg;

import lombok.Data;

@Data
public class RecvVideoMessage extends RecvMsg {

    private static final long serialVersionUID = -3750491257934605285L;

    /**
     * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String mediaId;

    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String thumbMediaId;

}
