package empapp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;

@Component
//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
@Data
public class RequestDetails {

    private String ip;

    private String postman;

    public RequestDetails() {
        log.info("Creating request details");
    }
}
