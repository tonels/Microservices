package com.hysch.controller;

import com.hysch.FeignCli;
import com.hysch.delegate.StudentClient;
import com.hysch.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class SchoolController {

    @Autowired
    StudentClient studentServiceDelegate;
    @Resource
    private FeignCli feignCli;

    @RequestMapping(value = "/getSchoolDetails/{schoolname}", method = RequestMethod.GET)
    public String getStudents(@PathVariable String schoolname) {
        System.out.println("Going to call student service to get data!");
        return studentServiceDelegate.callStudentServiceAndGetData(schoolname);
    }

    @GetMapping(value = "/getSchool/{schoolname}")
    public String getSchool(@PathVariable String schoolname){
        String s = feignCli.consumer(schoolname);
        System.out.println(s);
        return s;
    }

    @GetMapping(value = "/getAll")
    public String getAllStudent(){
        String s = feignCli.getAll();
        System.out.println(s);
        return s;
    }

    @GetMapping("/post")
    public String po1(){

        Student student = new Student();
        student.setName("Name-one").setClassName("Class—ONE");
        String post = feignCli.post(student);
        System.out.println(post);
        return post;
    }
}
