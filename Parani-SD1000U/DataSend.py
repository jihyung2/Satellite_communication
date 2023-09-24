import bluetooth

# 블루투스 주소 및 포트 설정
server_address = '00:00:00:00:00:00'  # Parani-SD1000U의 블루투스 주소로 변경하세요.
port = 1  # 사용할 블루투스 포트 번호

# 블루투스 서버에 연결
sock = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
sock.connect((server_address, port))

try:
    data_to_send = "Hello, Parani-SD1000U!"  # 전송할 데이터를 설정합니다.
    sock.send(data_to_send)

except KeyboardInterrupt:
    # 사용자가 Ctrl+C를 누르면 종료합니다.
    print("Data transmission stopped.")
    sock.close()