package com.gosciminski.testsapp.model;

import javax.persistence.Entity;

@Entity
public class Test extends BaseEntity{
    
    private String name;

    public Test() {
    }

    public Test(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}