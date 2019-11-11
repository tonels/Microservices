package com.hysch;

import com.hysch.domain.Student;
import org.springframework.stereotype.Component;

@Component
public class CustomerizeHystrix implements FeignCli {

    @Override
    public String consumer(String schoolname) {
        return "request error...";
    }

    @Override
    public String getAll() {
        return "请求超时";
    }

    @Override
    public String post(Student student) {
        return null;
    }
}
