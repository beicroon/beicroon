package com.beicroon.construct.http.entity;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.json.utils.JsonUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.MultiValueMap;

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

    @JsonIgnore
    private String url;

    @JsonIgnore
    private String method;

    @JsonIgnore
    private Map<String, ?> headers;

    @JsonIgnore
    private Map<String, ?> request;

    @JsonIgnore
    private MultiValueMap<String, String> formData;

    @JsonIgnore
    private byte[] response;

    public Http(String url, String method, Map<String, ?> headers, Map<String, ?> request) {
        this.url = url;

        this.method = method;

        this.headers = headers;

        this.request = request;
    }

    public Http(String url, String method, Map<String, ?> headers, MultiValueMap<String, String> formData) {
        this.url = url;

        this.method = method;

        this.headers = headers;

        this.formData = formData;
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
            sb.append(" --data-raw '").append(JsonUtils.toJson(this.request)).append("'");
        }

        if (EmptyUtils.isNotEmpty(this.formData)) {
            StringBuilder formDataString = new StringBuilder();

            this.formData.forEach((key, values) -> {
                if (values == null || values.isEmpty()) {
                    formDataString.append(key).append("=").append("&");

                    return;
                }

                if (values.size() == 1) {
                    formDataString.append(key).append("=").append(values.get(0)).append("&");

                    return;
                }

                for (String value : values) {
                    formDataString.append(key).append("[]=").append(value).append("&");
                }
            });

            formDataString.deleteCharAt(formDataString.length() - 1);

            sb.append(" -d '").append(formDataString).append("'");
        }

        sb.append(" --compressed --insecure");

        return sb.toString();
    }

}
