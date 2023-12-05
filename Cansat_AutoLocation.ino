#include <SoftwareSerial.h>
#include <TinyGPS.h>
#include <Stepper.h>
#define RXPIN 6
#define TXPIN 5
//Set this value equal to the baud rate of your GPS
#define GPSBAUD 9600
#include <math.h>
#include "stdlib.h"

#define QUADRANT_ONE 1
#define QUADRANT_TWO 2
#define QUADRANT_THREE 3
#define QUADRANT_FOUR 4
#define STOP 0
#define FORWARD 1
#define RIGHT 2
#define LEFT 3

int headingDegrees = 0;

typedef int CONTROLLER_SIGNAL;
typedef int _Quadrant;

CONTROLLER_SIGNAL moveSignal;
uint8_t rotate_flag=0;

TinyGPS gps;
// Initialize the NewSoftSerial library to the pins you defined above
SoftwareSerial uart_gps(3,2);
const int stepsPerRevolution = 20 48; 
// 모터 드라이브에 연결된 핀 IN4, IN2, IN3, IN1
Stepper leftStepper(stepsPerRevolution,11,9,10,8);   
Stepper rightStepper(stepsPerRevolution, 7,5,6,4);  

void getgps(TinyGPS &gps);

float lattarget = 36.349340;
float longtarget = 127.301993;
int distance_lat;
int distance_long;
int distance_c;
float latitude, longitude;
float prevLatitude, prevLongitude;
int diffAngle;

double angle;         
double degree_angle;   
double target_angle;

extern uint16_t Distance[3];
_Quadrant quadrant;

void setup()
{
  Serial.begin(9600);
  uart_gps.begin(GPSBAUD);
  
}

void loop()
{
  // leftStepper.step(stepsPerRevolution);
  // delay(500);

  // // 시계 방향으로 한바퀴 회전
  // rightStepper.step(stepsPerRevolution);
  // delay(500);
  
  while(uart_gps.available())
  {
    
    int c = uart_gps.read();
    if(gps.encode(c))
    {
      getgps(gps);
    }
  }

}

void getgps(TinyGPS &gps)
{
  float newLatitude, newLongitude;
  gps.f_get_position(&newLatitude, &newLongitude);

  if (prevLatitude != 0 && prevLongitude != 0) {
    headingDegrees = calculateBearing(prevLatitude, prevLongitude, newLatitude, newLongitude); // 방향 계산
  }
  Serial.print("위도1/경도2:");
  Serial.print(newLatitude, 5);
  Serial.print(", ");
  Serial.println(newLongitude, 5);
  SelfDriving();

  prevLatitude = newLatitude;
  prevLongitude = newLongitude;
  
   if (moveSignal == 0) {
        //모터 정지
      leftStepper.setSpeed(0);
      rightStepper.setSpeed(0);
     }
     else if (moveSignal == 1) {
        // 모터 두개다
      leftStepper.setSpeed(14);
      rightStepper.setSpeed(14);
      leftStepper.step(stepsPerRevolution);
      rightStepper.step(stepsPerRevolution);
    }
     else if (moveSignal == 2) {
        //모터 오른쪽 한개만
      leftStepper.setSpeed(14);
      rightStepper.setSpeed(0);
      leftStepper.step(stepsPerRevolution);
     }
     else if (moveSignal == 3) {
        // 모터 왼쪽한개만
      leftStepper.setSpeed(0);
      rightStepper.setSpeed(14);
      rightStepper.step(stepsPerRevolution);
     }

  delay(1000);
}
//두 개의 GPS 좌표(위도와 경도)를 입력 받아, 첫 번째 좌표에서 두 번째 좌표까지의 방향을 계산합니다.
double calculateBearing(double lat1, double lon1, double lat2, double lon2) {
  double dLon = (lon2 - lon1); // 두 위치의 경도 차이를 계산
  double y = sin(dLon) * cos(lat2); // y좌표를 계산하는데 필요한 중간값 계산
  double x = cos(lat1) * sin(lat2) - sin(lat1) * cos(lat2) * cos(dLon); // x 좌표를 계산하는데 필요한 중간값 계산
  double brng = atan2(y, x); // 아크 탄젠트 사용해서 방위각 계산
  
  brng = degrees(brng); // 방위각을 라디안 단위에서 도 단위로 변환
  brng = (brng + 360) - ((int)(brng / 360) * 360); // 방위각 0~360 범위로 맞추기
  return brng;
}
//이렇게 계산된 방위각은 첫 번째 좌표에서 두 번째 좌표까지의 방향을 나타내며, 북쪽을 기준으로 시계 방향으로 측정된 각도입니다. 예를 들어, 계산된 방위각이 0이면 북쪽, 90이면 동쪽, 180이면 남쪽, 270이면 서쪽을 가리킵니다.

void SelfDriving(){ // 현재 위치와 목표 위치의 거리를 계산, 목표 방향 계산 (4분면 계산을 통함)

     distance_long = (longtarget - longitude) * 1000000; // 현재 위치와 현재 목표 사이의 경도 위도차이 계산
     distance_lat = (lattarget - latitude) * 1000000; // 현재 위치와 현재 목표 사이의 경도 위도차이 계산
     distance_c = sqrt((distance_lat * distance_lat) + (distance_long * distance_long)); // 현재 위치와 목표 위치 사이의 직선 거리를 계산합니다. 이 거리는 피타고라스의 정리를 사용하여 계산됩니다.

    //목표 위치로의 각도를 계산합니다. 이 각도는 아크코사인 함수를 사용하여 계산되며, 라디안 단위에서 도 단위로 변환됩니다.
     angle = acos((double)distance_long / distance_c); 
     degree_angle = (double)angle * 180 / M_PI;
    //quadrant: 현재 위치와 목표 위치를 기반으로 4분면을 결정합니다. 이 4분면은 다음 목표 방향을 결정하는데 사용됩니다.
     if (distance_long > 0 && distance_lat > 0) {
        quadrant = QUADRANT_ONE;
     }
     else if (distance_long < 0 && distance_lat > 0) {
        quadrant = QUADRANT_TWO;
     }
     else if (distance_long < 0 && distance_lat < 0) {
        quadrant = QUADRANT_THREE;
     }
     else if (distance_long > 0 && distance_lat < 0) {
        quadrant = QUADRANT_FOUR;
     }
    //target_angle: 목표 방향을 계산합니다. 이 방향은 4분면과 degree_angle를 기반으로 계산됩니다.
     switch (quadrant) {
     case QUADRANT_ONE:
        target_angle = 90 - degree_angle;
        break;
     case QUADRANT_TWO:
        degree_angle = 180-degree_angle;
        target_angle = 270 + degree_angle;
        break;
     case QUADRANT_THREE:
        degree_angle = 180-degree_angle;
        target_angle = 270 - degree_angle;
        break;
     case QUADRANT_FOUR:
        target_angle = 90 + degree_angle;
        break;
     default:
        break;
     }
    // 목표 방향과 현재 방향의 차이를 계산, 이 값에 따라 움직임의 상태 전달
     diffAngle = ((int)target_angle - (int)headingDegrees + 360) % 360;

     if(distance_c < 1){
        moveSignal = STOP;

     }else {
        if ((diffAngle >= 345) || (diffAngle < 15)){
           moveSignal = FORWARD;

        }else {

           if ((diffAngle >= 180) && (diffAngle < 345)){
              moveSignal = LEFT;
           }else if ((diffAngle >= 15) && (diffAngle < 180)){
              moveSignal = RIGHT;
           }
        }
     }
    Serial.print(moveSignal);
}
