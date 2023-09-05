package com.example.satellite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SensorController {

    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public void setMessagingTemplate(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @ResponseBody
    @GetMapping("/api/data/read")
    public Map<String, Double> getSensorData() {
        // 임의의 센서 데이터 생성
        double sensorValue = Math.random() * 100;

        // Create a map to hold the sensor value
        Map<String, Double> data = new HashMap<>();
        data.put("value", sensorValue);
        messagingTemplate.convertAndSend("/topic/sensor-data", data);

        return data;
    }

    @GetMapping("/sensor-data")
    public String showSensorDataPage() {
        return "sensorData";
    }
}