package com.example.satellite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    private final SerialReader serialReader;
    private final DataProcessingService dataProcessingService; // 추가

    @Autowired
    public DataService(SerialReader serialReader, DataProcessingService dataProcessingService) {
        this.serialReader = serialReader;
        this.dataProcessingService = dataProcessingService; // 주입
    }

    public String readAndProcessDataFromSerial() {
        String rawData = serialReader.readData(); // 실제 시리얼 포트에서 데이터 읽기.

        String processedData = dataProcessingService.processReceivedData(rawData); // 데이터 가공 및 처리

        return processedData;
    }
}