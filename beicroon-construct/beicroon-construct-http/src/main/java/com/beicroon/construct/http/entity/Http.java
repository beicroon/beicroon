package com.beicroon.construct.http.entity;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.json.utils.JsonUtils;
import com.beicroon.construct.utils.EmptyUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Http implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer status;

    private String url;

    private String method;

    private Map<String, ?> headers;

    private Map<String, ?> request;

    private byte[] response;

    public Http(String url, String method, Map<String, ?> headers, Map<String, ?> request) {
        this.url = url;

        this.method = method;

        this.headers = headers;

        this.request = request;
    }

    public String getResponseString() {
        return this.getResponseString(SystemConstant.CHARSET);
    }

    public String getResponseString(Charset charset) {
        return new String(this.response, charset);
    }

    public <T> T getResponseAs(Class<T> clazz) {
        return this.getResponseAs(clazz, SystemConstant.CHARSET);
    }

    public <T> T getResponseAs(Class<T> clazz, Charset charset) {
        return JsonUtils.toObject(this.getResponseString(charset), clazz);
    }

    public String getCurl() {
        StringBuilder sb = new StringBuilder();

        sb.append("curl -X ").append(this.method).append(" '").append(this.url).append("'");

        if (EmptyUtils.isNotEmpty(this.headers)) {
            for (Map.Entry<String, ?> header : this.headers.entrySet()) {
                sb.append(" -H '").append(header.getKey()).append(": ").append(header.getValue()).append("'");
            }
        }

        if (EmptyUtils.isNotEmpty(this.request)) {
            sb.append(" -d '");

            sb.append(JsonUtils.toJson(this.request));

            sb.append("'");
        }

        sb.append(" --compressed --insecure");

        return sb.toString();
    }

    @Override
    public String toString() {
        return this.getCurl();
    }

}
