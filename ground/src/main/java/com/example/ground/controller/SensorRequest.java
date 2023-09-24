package com.example.ground.controller;

public class SensorRequest {
    private String name;
    private String description;
    private String code;

    public String getName(){return name;}
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription(){return description;}
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode(){return code;}
    public void setCode(String code) {
        this.code = code;
    }
    public SensorRequest(String name, String description, String code) {
        super();
        this.name = name;
        this.description = description;
        this.code = code;
    }

    // 생성자, getter 및 setter 메서드 추가
}