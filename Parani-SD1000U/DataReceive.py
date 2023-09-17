import bluetooth

# 블루투스 주소 및 포트 설정
server_address = '00:00:00:00:00:00'  # Parani-SD1000U의 블루투스 주소로 변경하세요.
port = 1  # 사용할 블루투스 포트 번호

# 블루투스 서버로부터 연결을 대기
sock = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
sock.bind(("", port))
sock.listen(1)

client_sock, client_info = sock.accept()
print("Accepted connection from", client_info)

try:
    while True:
        received_data = client_sock.recv(1024)  # 데이터를 받습니다.
        print("Received data:", received_data.decode('utf-8'))

except KeyboardInterrupt:
    # 사용자가 Ctrl+C를 누르면 종료합니다.
    print("Data reception stopped.")
    client_sock.close()
    sock.close()