package com.example.satellite.service;

import java.io.InputStream;
import java.io.IOException;

public class StreamDataProcessor {
    public static void processStreamData(InputStream inputStream) {
        try {
            int data;
            while ((data = inputStream.read()) != -1) {
                // 아스키 코드값을 문자로 변환하여 출력
                char character = (char) data;
                System.out.print(character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 표준 입력에서 스트림 데이터 읽기
        InputStream inputStream = System.in;

        // 스트림 데이터 처리 함수 호출
        processStreamData(inputStream);
    }
}