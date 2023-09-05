import java.io.InputStream;
import java.io.IOException;

public class ASCIITest {
    public static void processStreamData(InputStream inputStream) {
        try {
            StringBuilder resultBuilder = new StringBuilder();
            int data;
            while ((data = inputStream.read()) != -1) {
                char character = (char) data;
                if (character == ',') { // 쉼표를 만나면 문자열 출력
                    String receivedData = resultBuilder.toString().trim(); // 앞뒤 공백 제거
                    int asciiValue = Integer.parseInt(receivedData);
                    char asciiCharacter = (char) asciiValue;
                    System.out.println("Received ASCII Data: " + asciiCharacter);
                    resultBuilder.setLength(0); // 결과 문자열 초기화
                } else if (character != ' ') { // 공백은 무시
                    resultBuilder.append(character);
                }
            }

            // 마지막으로 저장된 결과 문자열 처리
            if (resultBuilder.length() > 0) {
                String receivedData = resultBuilder.toString().trim();
                int asciiValue = Integer.parseInt(receivedData);
                char character = (char) asciiValue;
                System.out.println("Received ASCII Data: " + character);
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