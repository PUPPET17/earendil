package fun.puppet17.earendil.infrastructure.utils.sdk;

import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @author PUPPET17
 * @version 1.0
 * @date 2023/10/16
 */
public class HttpClient {

    /**
     * 向目的URL发送post请求
     */
    public static String sendPostRequest(String url, String params){
        HttpMethod method = HttpMethod.POST;
        return sendRequest(url, params, method);
    }

    /**
     * 向目的URL发送get请求
     */
    public static String sendGetRequest(String url, String params){
        HttpMethod method = HttpMethod.GET;
        return sendRequest(url, params, method);
    }

    /**
     * 发送http请求
     */
    private static String sendRequest(String url, String params, HttpMethod method) {
        RestTemplate client = new RestTemplate();
        // StringHttpMessageConverter默认是ISO-8859-1编码
        client.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        // 新建Http头，add方法可以添加参数
        HttpHeaders headers = new HttpHeaders();
        // 设置提交方式
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        // 将请求头部和参数合成一个请求
        HttpEntity<String> requestEntity = new HttpEntity<>(params, headers);
        // 执行HTTP请求，将返回的结构使用String类格式化（可设置为对应返回值格式的类）
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String .class);
        return response.getBody();
    }

}
