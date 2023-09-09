#include <Wire.h>
#include <LoRa.h>
#include <TinyGPS++.h>
#include <SSD1306Wire.h>

SSD1306Wire display(0x3c, 5, 4);
TinyGPSPlus gps;

void setup() {
  Serial.begin(115200);
  display.init();
  display.flipScreenVertically();
  display.setFont(ArialMT_Plain_10);
  display.setTextAlignment(TEXT_ALIGN_LEFT);
  display.drawString(0, 0, "LoRa GNSS Example");
  display.display();
  
  if (!LoRa.begin(915E6)) {
    Serial.println("LoRa init failed. Check your connections.");
    while (1);
  }
}

void loop() {
  while (Serial.available() > 0) {
    gps.encode(Serial.read());
  }

  if (gps.location.isValid()) {
    float latitude = gps.location.lat();
    float longitude = gps.location.lng();
    
    String locationInfo = "Lat: " + String(latitude, 6) + "  Long: " + String(longitude, 6);
    
    Serial.println(locationInfo);
    display.clear();
    display.drawString(0, 0, "LoRa GNSS Example");
    display.drawString(0, 15, "Location: ");
    display.drawString(0, 30, locationInfo);
    display.display();
    
    // Send data via LoRa
    LoRa.beginPacket();
    LoRa.print(locationInfo);
    LoRa.endPacket();
  }
  
  delay(10000); // Update location every 10 seconds
}