package empapp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
@Slf4j
public class RequestDetailsHandlerInterceptor implements HandlerInterceptor {

    private RequestDetails requestDetails;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String ip = request.getRemoteAddr();
        String postmanToken = request.getHeader("Postman-Token");
        log.info("Run RequestDetailsHandlerInterceptor: {} {}", ip, postmanToken);
        requestDetails.setIp(ip);
        requestDetails.setPostman(postmanToken);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
