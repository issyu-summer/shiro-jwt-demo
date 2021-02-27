package cn.edu.xmu.gateway.config;

import cn.edu.xmu.core.utils.Response;
import cn.edu.xmu.core.utils.ResponseCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author summer
 * @date 2021/2/27 11:55
 */
@Component

public class AuthFilter implements GatewayFilter, Ordered {

    private List<String> skipAuUrls;

    private ObjectMapper objectMapper;

    public AuthFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request=exchange.getRequest();
        String url= request.getPath().pathWithinApplication().value();
        HttpMethod method = request.getMethod();
        log.debug("请求URL为:"+url);
        log.debug("method:"+method);
        String token = request.getHeaders().getFirst("Authorization");
        if(token==null){
            return authError(exchange.getResponse());
        }else {
            //此处将调用userService的服务来检验Authorization
        }
        return null;
    }




    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

    private Mono<Void> authError(ServerHttpResponse response){
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
        Response<?> rep = new Response<>().setResponseCode(ResponseCode.AUTH_INVALID_JWT);
        String retString=null;
        try {
            retString=objectMapper.writeValueAsString(rep);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assert retString != null;
        DataBuffer buffer
                = response.bufferFactory().wrap(retString.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(buffer));
    }
}
