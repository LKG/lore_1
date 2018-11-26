package im.heart.core.web;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.web.enums.WebError;

/**
 * Created by admin on 2018/7/13.
 */
public class ResponseError {
    @JSONField(name = "error")
    private String name;

    @JSONField(name = "error_code")
    private String code;
    @JSONField(name = "error_description")
    private String description;

    @JSONField(name = "error_url")
    private String errorUrl;

    public ResponseError(WebError webError) {
        this(webError.getCode(),webError.getName(),webError.getDescription());
    }

    public ResponseError(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public ResponseError(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }
}
