import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;


public class StreamDataProcessorTest {
    public static void main(String[] args) throws IOException {
        StreamDataProcessor processor = new StreamDataProcessor();

        // ASCII 데이터 테스트
        String asciiTestData = "ASCII,65,66,67,END";
        InputStream asciiStream = new ByteArrayInputStream(asciiTestData.getBytes());
        processor.processData(asciiStream);


        // JSON 데이터 테스트
        String jsonTestData = "JSON,{\"sensorType\":\"temperature\",\"sensorValue\":25.5},END";
        InputStream jsonStream = new ByteArrayInputStream(jsonTestData.getBytes());
        processor.processData(jsonStream);
    }
}