package com.example.demo;

public class Categories {
    private Integer id;
    private String name;
    private Boolean isFictive;

    public Categories() {
    }

    public Categories(Integer id, String name, Boolean isFictive) {
        this.id = id;
        this.name = name;
        this.isFictive = isFictive;
    }

    public Boolean getFictive() {
        return isFictive;
    }

    public void setFictive(Boolean fictive) {
        isFictive = fictive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
