package com.example.satellite.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    private final SerialReader serialReader;

    @Autowired
    public DataService(SerialReader serialReader) {
        this.serialReader = serialReader;
    }

    public String readDataFromSerial() {

        return serialReader.readData(); // 실제 시리얼 포트에서 데이터 읽기.
    }
}
