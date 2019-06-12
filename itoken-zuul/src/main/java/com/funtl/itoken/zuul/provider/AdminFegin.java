package com.funtl.itoken.zuul.provider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

//当路由失败的时候提供的路由回调方法给前端
public class AdminFegin implements FallbackProvider {
    @Override
    public String getRoute() {
        //服务的id名 :为每个不同的服务都要提供相应的回调方法
        return null;
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        ClientHttpResponse clientHttpResponse = new ClientHttpResponse() {
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return httpHeaders;
            }

            //返回给前端的响应数据
            @Override
            public InputStream getBody() throws IOException {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> map = new HashMap<>();
                map.put("status",200);
                map.put("message","网络无法连接：请检查网络");

                return new ByteArrayInputStream(
                        objectMapper.writeValueAsString(map).getBytes("utf-8")
                );
            }
            //虽然前端api路由失败了 但不应向前端抛出404 500 等错误信息
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }
        };
        return clientHttpResponse;
    }
}
