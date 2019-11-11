package com.hy.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Student {
    private String name;
    private String className;

////    @JsonCreator


//    public Student(String name, String className) {
//        super();
//        this.name = name;
//        this.className = className;
//    }

}
