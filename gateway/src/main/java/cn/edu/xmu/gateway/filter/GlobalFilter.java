package cn.edu.xmu.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author summer
 * @date 2021/3/1 17:52
 * 全局过滤器
 */
@Configuration
public class GlobalFilter implements GatewayFilter, Ordered {

    private static Logger log = LoggerFactory.getLogger(GlobalFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url=exchange.getRequest().getPath().pathWithinApplication().value();
        log.debug("请求的url:"+url);
        HttpMethod method = exchange.getRequest().getMethod();
        log.debug("请求的方法:"+ Objects.requireNonNull(method).getClass().getName());

        return null;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
