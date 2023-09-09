import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Binary {
    public static void processStreamData(byte[] data) {

        ByteBuffer buffer = ByteBuffer.wrap(data);

        // 바이너리 데이터 파싱 및 가공
        double sensorValue = buffer.getShort();.

        System.out.println("Received Sensor Value: " + sensorValue);

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter binary value: ");
        String binaryString = scanner.nextLine();

        // 입력된 이진 문자열을 byte 배열로 변환
        byte[] binaryData = convertBinaryStringToByteArray(binaryString);

        // 스트림 데이터 처리 함수 호출
        processStreamData(binaryData);
    }

    private static byte[] convertBinaryStringToByteArray(String binaryString) {
        int length = binaryString.length() / 8;
        byte[] byteArray = new byte[length];

        for (int i = 0; i < length; i++) {
            String byteString = binaryString.substring(i * 8, (i + 1) * 8);
            byteArray[i] = (byte) Integer.parseInt(byteString, 2);
        }

        return byteArray;
    }
}