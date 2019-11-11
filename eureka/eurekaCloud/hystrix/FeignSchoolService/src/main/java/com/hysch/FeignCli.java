package com.hysch;

import com.hysch.domain.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "client-student",fallback = CustomerizeHystrix.class)
public interface FeignCli {

    @GetMapping("/getStudentDetailsForSchool/{schoolname}")
    String consumer(@PathVariable("schoolname") String schoolname);

    @GetMapping("/getAll")
    String getAll();

    @PostMapping("/post")
    String post(Student student);

}

