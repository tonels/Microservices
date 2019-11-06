package com.tonels.microservice.example.bootiful.handle;

import com.tonels.microservice.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.tonels.microservice.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
public class CustomerUrlHandler implements UrlBlockHandler {

    @Override
    public void blocked(HttpServletRequest req, HttpServletResponse res, BlockException e) throws IOException {

        res.getWriter().append(">_<");
    }
}
