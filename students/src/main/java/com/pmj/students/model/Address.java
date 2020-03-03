package com.pmj.students.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Integer id;
    private String address;
    private String city;

    public Address(Integer id, String address, String city) {
        this.id = id;
        this.address = address;
        this.city = city;
    }
}