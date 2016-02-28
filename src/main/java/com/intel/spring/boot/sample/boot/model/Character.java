package com.intel.spring.boot.sample.boot.model;

import javax.persistence.*;

/**
 * Created by Ecic Chen on 2016/2/28.
 */
@Entity
@Table(name="T_CHARACTER")
public class Character {

    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    public Character(){

    }

    public Character(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
