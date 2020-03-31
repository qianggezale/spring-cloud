package cn.qiang.springcloud.filler;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.protocol.RequestContent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component //重要
public class FilerZuul extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getHeader("token");
        System.out.println(token);
        System.out.println(token != "123");
        System.out.println(!StringUtils.hasText(token));
        if (!StringUtils.hasText(token)) {
            currentContext.setResponseBody("token wuxiao");
            currentContext.setResponseStatusCode(401);
            currentContext.setSendZuulResponse(false);
            return null;
        }
        if (!token.equals("123")) {
            currentContext.setResponseBody("token error");
            currentContext.setResponseStatusCode(401);
            currentContext.setSendZuulResponse(false);
            return null;
        }
        currentContext.setSendZuulResponse(true);

        return null;
    }
}
