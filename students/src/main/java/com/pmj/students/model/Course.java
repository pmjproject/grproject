package com.pmj.students.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Course {

    private Integer id;
    private String course_name;
    private String course_code;

    @JsonIgnore
    private Integer studentId;
}
