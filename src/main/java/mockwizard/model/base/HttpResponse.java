package mockwizard.model.base;

import mockwizard.model.component.Header;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpResponse {
    private Integer httpStatusCode;
    private List<Header> headers;
    private String body;

    public Map<String, List<String>> getHeadersAsResponse() {
        Map<String, List<String>> responseHeaders = new HashMap<>();
        for (Header header : headers) {
            String key = header.getKey();
            List<String> values = header.getValues();
            if (key != null && values != null) {
                responseHeaders.put(key, values);
            }
        }
        return responseHeaders;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
