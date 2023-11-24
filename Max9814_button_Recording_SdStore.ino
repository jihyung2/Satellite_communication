#include <SD.h>
#include <SPI.h>
#include <TMRpcm.h>

#define SD_ChipSelectPin 4
TMRpcm audio;

bool recording_now = false;
const int button_pin = 7;
const int mic_pin = A0;
const int sample_rate = 16000;

unsigned long button_pressed_time = 0;

void setup() {
  Serial.begin(9600);
  Serial.println("loading...");

  pinMode(mic_pin, INPUT);
  pinMode(button_pin, INPUT_PULLUP);

  SD.begin(SD_ChipSelectPin);
  audio.CSPin = SD_ChipSelectPin;

  audio.setVolume(7);  // 마이크 입력 레벨 조정
  audio.quality(1);   // 녹음 품질 설정 (0: 낮은 품질, 1: 보통 품질, 2: 높은 품질)

}

void loop() {
  // 버튼이 눌렸을 때
  if (digitalRead(button_pin) == HIGH && !recording_now) {
    button_pressed_time = millis();  // 버튼이 눌린 시간 기록
    recording_now = true;
    char file_name[] = "RecordingData.wav";
    audio.startRecording(file_name, sample_rate, mic_pin);
    Serial.println("녹음 중");
  }

  // 버튼이 눌린 후 3초 이상 경과하면 녹음 정지
  if (recording_now && millis() - button_pressed_time >= 10000) {
    recording_now = false;
    char file_name[] = "RecordingData.wav";
    audio.stopRecording(file_name);
    Serial.println("녹음이 저장되었습니다.");

    // SD 카드 확인
    if (SD.exists(file_name)) {
      Serial.println("녹음 파일이 SD 카드에 저장되었습니다.");
    } else {
      Serial.println("녹음 파일 저장 중 오류가 발생했습니다.");
    }
  }
}
