package cn.edu.xmu.auth.config;

import cn.edu.xmu.auth.utils.JwtToken;
import cn.edu.xmu.core.utils.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author summer
 * @date 2021/2/26 20:22
 */
public class ShiroFilter extends BasicHttpAuthenticationFilter {

    private static final List<String> anonUrl=AnonUrl.getAnonUrl();

    private static final String  AUTHORIZATION = "Authorization";

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected boolean isAccessAllowed(
            ServletRequest request,
            ServletResponse response,
            Object mappedValue){

        HttpServletRequest httpServletRequest
                = (HttpServletRequest) request;
        //任意匹配
        boolean match = anonUrl
                .stream()
                .anyMatch(e->pathMatcher.match(e,httpServletRequest.getRequestURI()));
        if(match){
            return true;
        }
        if(isLoginAttempt(request,response)){
            return executeLogin(request,response);
        }
        return false;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep= (HttpServletResponse) response;
        String token = req.getHeader(AUTHORIZATION);
        if(token==null){
            log.error("token为空");
            Response<?> noTokenResponse = new Response<>().setCode(999).setMsg("错误,token为空");
            try {
                setResponseBody(rep,noTokenResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(AUTHORIZATION);
        JwtToken jwtToken = new JwtToken().setToken(token);
        try {
            getSubject(request, response).login(jwtToken);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    private void setResponseBody(HttpServletResponse response, Object obj) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(obj);
        writer.print(json);
        writer.close();
        response.flushBuffer();
    }


    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个 option请求，这里我们给 option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
