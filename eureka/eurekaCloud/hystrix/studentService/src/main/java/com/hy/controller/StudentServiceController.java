package com.hy.controller;

import com.google.common.collect.Lists;
import com.hy.domain.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class StudentServiceController {

    private static Map<String, List<Student>> schooDB = new HashMap<String, List<Student>>();

    static {
        schooDB = new HashMap<String, List<Student>>();

        Student s1 = new Student().setName("张三").setClassName("一班");
        Student s2 = new Student().setName("刘三").setClassName("二班");
        List<Student> lst = Lists.newArrayList(s1, s2);

        schooDB.put("abcschool", lst);

        schooDB.put("xyzschool", lst);

    }

    @RequestMapping(value = "/getStudentDetailsForSchool/{schoolname}", method = RequestMethod.GET)
    public List<Student> getStudents(@PathVariable String schoolname) {
        System.out.println("Getting Student details for " + schoolname);

        List<Student> studentList = schooDB.get(schoolname);
        if (studentList == null) {
            studentList = new ArrayList<Student>();
            Student std = new Student().setName("ss").setClassName("className");
            studentList.add(std);
        }
        return studentList;
    }


    @GetMapping(value = "/getAll")
    public String all() {
        return schooDB.toString();
    }

    @PostMapping(value = "/post")
    public String p1(@RequestBody Student student) {
        return student.toString();
    }


}
