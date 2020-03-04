package com.pmj.students.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="student_id")
    @JsonIgnore
    private Student student;
}
