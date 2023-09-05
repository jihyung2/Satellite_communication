import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StreamDataProcessor {
    public void processData(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        while (true) {

                // 데이터 유형 판별
                String dataTypeString = dataInputStream.readUTF();
                DataType dataType = DataType.valueOf(dataTypeString);
                System.out.println(dataType);

                // 데이터 유형에 따른 처리 로직 수행
                if (dataType == DataType.END) {
                    break; // 데이터의 끝을 나타내는 경우 반복문 종료
                }

                switch (dataType) {
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
    }

    public void processAsciiData(DataInputStream dataInputStream) throws IOException {
        String rawData = dataInputStream.readUTF(); // ASCII 데이터 읽기
        // ASCII 문자열 데이터 파싱 및 가공
        String[] parts = rawData.split(",");
        StringBuilder resultBuilder = new StringBuilder();
        for (String part : parts) {
            int asciiValue = Integer.parseInt(part);
            char character = (char) asciiValue;
            resultBuilder.append(character);
        }
        System.out.println("Received ASCII Data: " + resultBuilder.toString());
    }

    public void processBinaryData(DataInputStream dataInputStream) throws IOException {
        double sensorValue = dataInputStream.readDouble(); // 바이너리 데이터 읽기
        System.out.println("Received Binary Data: " + sensorValue);
    }

    public void processJSONData(DataInputStream dataInputStream) throws IOException {
        String jsonData = dataInputStream.readUTF(); // JSON 데이터 읽기
        // JSON 데이터 파싱
        // 여기서 JSON 파서를 사용하여 파싱하는 코드를 추가해야 합니다.
        System.out.println("Received JSON Data: " + jsonData);
    }

    // 나머지 메서드(processImage, processAudio)도 유사하게 수정해야 할 수 있습니다.

    private enum DataType {
        IMAGE,
        AUDIO,
        ASCII,
        BINARY,
        JSON,
        END // 데이터의 끝을 나타내는 값 추가
    }
}