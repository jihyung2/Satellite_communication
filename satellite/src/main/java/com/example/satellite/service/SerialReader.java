package com.example.satellite.service;

import com.fazecast.jSerialComm.SerialPort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Component
public class SerialReader {
    public String readData() {
        SerialPort comPort = SerialPort.getCommPorts()[0];
        comPort.openPort();
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

        Scanner data = new Scanner(comPort.getInputStream());

        StringBuilder result = new StringBuilder();

        while (data.hasNext()) {
            result.append(data.nextLine());
            result.append("\n");
        }

        return result.toString();
    }
}