import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;


public class StreamDataProcessorTest {
    public static void main(String[] args) throws IOException {
        StreamDataProcessor processor = new StreamDataProcessor();

        // 이미지 데이터 테스트
        byte[] imageData = generateImageData();
        processTestData(processor, DataType.IMAGE, imageData);

        // 오디오 데이터 테스트
        byte[] audioData = generateAudioData();
        processTestData(processor, DataType.AUDIO, audioData);

        // ASCII 문자열 데이터 테스트
        String asciiString = "65,66,67";
        processTestData(processor, DataType.ASCII, asciiString.getBytes());

        // 바이너리 데이터 테스트
        double binaryValue = 3.14;
        processTestData(processor, DataType.BINARY, toByteArray(binaryValue));

        // JSON 데이터 테스트
        String jsonData = "{\"sensorType\":\"temperature\",\"sensorValue\":25.5}";
        processTestData(processor, DataType.JSON,jsonData.getBytes());
    }

    private static void processTestData(StreamDataProcessor processor,
                                        DataType dataType,
                                        byte[] data) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(data)) {
            System.out.println("Processing " + dataType + " data...");
            processor.processData(inputStream);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] generateImageData() {
        // 이미지 데이터 생성 로직 구현
        return new byte[]{1, 2 ,3};  // 임의의 이미지 바이트 배열 반환 (실제로는 적절한 방식으로 생성)
    }

    private static byte[] generateAudioData() {
        // 오디오 데이터 생성 로직 구현
        return new byte[]{4 ,5 ,6};  // 임의의 오디오 바이트 배열 반환 (실제로는 적절한 방식으로 생성)
    }

    private static byte[] toByteArray(double value) throws IOException{
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream(Double.BYTES);
        byteArrayOutputStream.write(ByteBuffer.allocate(Double.BYTES).putDouble(value).array());
        return byteArrayOutputStream.toByteArray();
    }
    public enum DataType {
        IMAGE,
        AUDIO,
        ASCII,
        BINARY,
        JSON
    }
}
