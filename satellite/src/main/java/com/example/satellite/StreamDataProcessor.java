package com.example.satellite;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;


public class StreamDataProcessor {
    public void processData(InputStream inputStream) {
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            while (true) {
                // 데이터 유형 판별
                DataType dataType = DataType.valueOf(dataInputStream.readUTF());

                // 해당 데이터 유형에 따른 처리 로직 수행
                switch (dataType) {
                    case IMAGE:
                        processImage(dataInputStream);
                        break;
                    case AUDIO:
                        processAudio(dataInputStream);
                        break;
                    case ASCII:
                        processAsciiData(dataInputStream);
                        break;
                    case BINARY:
                        processBinaryData(dataInputStream);
                        break;
                    case JSON:
                        processJSONData(dataInputStream);
                        break;
                    default:
                        System.out.println("Unknown data type: " + dataType);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processImage(DataInputStream dataInputStream) throws IOException {
        int imageSize = dataInputStream.readInt();

        byte[] imageData = new byte[imageSize];
        dataInputStream.readFully(imageData);

        // 이미지 데이터 처리 로직 수행
        System.out.println("Received Image Data: " + Arrays.toString(imageData));

        // 추가적인 이미지 데이터 처리 로직 구현
    }

    private void processAudio(DataInputStream dataInputStream) throws IOException {
        int audioSize = dataInputStream.readInt();

        byte[] audioData = new byte[audioSize];
        dataInputStream.readFully(audioData);

        // 오디오 데이터 처리 로직 수행
        System.out.println("Received Audio Data: " + Arrays.toString(audioData));

        // 추가적인 오디오 데이터 처리 로직 구현
    }
    public void processAsciiData(DataInputStream dataInputStream) throws IOException {
        String rawData = String.valueOf(dataInputStream);
        // ASCII 문자열 데이터 파싱 및 가공
        String[] parts = rawData.split(",");

        // String Builer는 문자열을 연결해줌
        StringBuilder resultBuilder = new StringBuilder();
        for (String part : parts) {
            int asciiValue = Integer.parseInt(part); //(아스키코드)문자열로 표현된 숫자를 정수로 변환
            char character = (char) asciiValue; // char 캐스팅 연산자를 사용하여 정수값을 해당하는 ASCII 문자로 변환
            resultBuilder.append(character);
        }
        System.out.println("Received ASCII Data: " + resultBuilder.toString());
    }
    public void processBinaryData(DataInputStream dataInputStream) throws IOException {

        ByteBuffer buffer = ByteBuffer.wrap(dataInputStream.readAllBytes()); // 바이너리 데이터를 처리하는 BinaryDataProcessor 서비스 클래스

        // 바이너리 데이터 파싱 및 가공
        double sensorValue = buffer.getDouble();

    }
    public void processJSONData(DataInputStream dataInputStream) throws IOException {
        try {
            // JSON 데이터 파싱
            JSONObject json = new JSONObject(dataInputStream);

            // 필요한 정보 추출
            String sensorType = json.getString("sensorType");
            double sensorValue = json.getDouble("sensorValue");

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }
    private enum DataType {
        IMAGE,
        AUDIO,
        ASCII,
        BINARY,
        JSON
    }
}