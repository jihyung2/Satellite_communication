import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
            StringBuilder jsonDataBuilder = new StringBuilder();
            String line;

            // Read lines from the input stream and append them to the jsonDataBuilder
            while ((line = dataInputStream.readUTF()) != null) {
                jsonDataBuilder.append(line);
            }

            String jsonData = jsonDataBuilder.toString();

            Map<String, Object> jsonMap = new HashMap<>();

            // Remove leading/trailing whitespace and curly braces
            if (jsonData.startsWith("{") && jsonData.endsWith("}")) {
                jsonData = jsonData.substring(1, jsonData.length() - 1);
            }

            // Split the data into key-value pairs
            String[] keyValuePairs = jsonData.split(",");

            // Process each key-value pair
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split(":");

                if (keyValue.length == 2) {
                    String key = keyValue[0].trim().replaceAll("\"", "");
                    String value = keyValue[1].trim();

                    jsonMap.put(key, value);
                }
            }

            // Print the processed data
            System.out.println(jsonMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
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