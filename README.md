# satellite_communication

## 방법
캔 위성 -> 고도 높임 -> 낙하산 -> 임무수행( 카메라, 영상, 여러 센서 등)

1. 드론으로 위성 확인하는 방법
2. 헬륨풍선 위성을 사용해서 헬륨 조절 해서 최대한 안터지게 유지하며 데이터 수집


## 맡은 역할
임무 수행시 들어오는 데이터 가공 및 처리

1. 위성에서 전송한 데이터 저장, 및 웹 출력
2. 들어오는 데이터들로 새로운 데이터 창출 ( ex)카메라의 경우 머신러닝 모델을 도입하여 분석 )
3. 지상국과 위성간의 통신 및 제어
   


# 7/18조사

위성 통신용 게이트웨이: 위성에서 수신된 데이터를 받아 로라통신 기반 보드로 전달하는 역할을 수행합니다. 게이트웨이는 위성과 통신하기 위한 장비로, 위성으로부터 전송된 데이터를 디코딩하고 로라통신 기반 보드로 전달합니다. 게이트웨이는 위성에서 수신된 데이터를 지상국으로 전달하는 역할을 담당하므로 필수적인 요소입니다.

로라통신 기반 보드: 게이트웨이로부터 전달된 데이터를 받아 지상국으로 전송하는 역할을 수행합니다. 로라통신 기반 보드(로라 모듈)는 저전력 장거리 통신을 위한 기술로, 로라 프로토콜을 사용하여 데이터를 전송합니다. 이 보드는 게이트웨이와 지상국 간의 데이터 흐름을 관리하고 통신을 담당합니다.

따라서, 위성에서 데이터를 받아 로라통신 기반 보드를 통해 지상국으로 전달하기 위해서는 위성 통신용 게이트웨이와 로라통신 기반 보드 두 가지 요소가 필요합니다. 

로라 게이트웨이는 로라통신 네트워크와 다른 네트워크 간의 중개 역할을 수행하는 장비입니다. 일반적으로 로라 단말(Endpoint)은 게이트웨이를 통해 다른 네트워크로 데이터를 전송하거나 다른 네트워크에서 받은 데이터를 수신합니다.

하지만 로라 단말 간의 직접 통신을 위해 게이트웨이 없이도 로라통신을 사용할 수 있습니다. 이를 로라WAN (LoRa Wide Area Network)이라고 합니다. 로라WAN은 로라 단말이 직접 통신하여 데이터를 교환할 수 있는 네트워크 구성을 의미합니다.

로라WAN을 사용하는 경우에는 로라 단말 간의 통신에만 집중하며, 게이트웨이와 다른 네트워크와의 연결은 필요하지 않습니다. 그러나 이는 로라 단말 간의 직접 통신을 제한하므로, 통신 범위가 제한적이고 단말 간의 통신 거리가 짧아질 수 있습니다.

따라서, 로라 게이트웨이 없이 로라 단말 간의 통신만을 고려하는 경우 로라WAN을 사용할 수 있습니다. 그러나 게이트웨이를 통해 다른 네트워크와의 연결과 네트워크 확장성을 고려하는 경우에는 로라 게이트웨이의 사용을 권장합니다.

로라 게이트웨이 없이 로라 단말 간에 1:1 통신을 수행하는 것은 가능합니다. 로라 단말 간의 직접적인 통신인 로라WAN을 사용하면 게이트웨이 없이도 1:1 통신이 가능합니다. 로라WAN은 로라 단말 간의 통신을 위한 간단한 네트워크 구성을 제공합니다.

그러나 로라 게이트웨이를 사용하면 네트워크 확장성과 다른 네트워크와의 연결을 갖출 수 있습니다. 게이트웨이를 통해 로라 단말의 데이터를 다른 네트워크로 전송하거나, 다른 네트워크에서 수신한 데이터를 로라 단말로 전달할 수 있습니다. 따라서 로라 게이트웨이는 로라 단말 간의 통신 범위를 확장하고, 다른 네트워크와의 연결을 제공하여 통신의 유연성을 높일 수 있습니다.

결론적으로, 로라 게이트웨이를 사용하지 않고도 로라 단말 간에 1:1 통신은 가능합니다. 하지만 네트워크 확장성과 다른 네트워크와의 연결을 고려한다면 로라 게이트웨이의 사용을 권장합니다.

로라 게이트웨이의 장점:

네트워크 확장성: 로라 게이트웨이를 사용하면 로라 단말 간의 통신 범위를 확장할 수 있습니다. 게이트웨이를 통해 여러 개의 로라 단말과 통신할 수 있으며, 단말 간의 통신 거리를 늘릴 수 있습니다.

다른 네트워크와의 연결: 로라 게이트웨이를 통해 로라 단말로부터 수집된 데이터를 다른 네트워크로 전송하거나, 다른 네트워크에서 수신한 데이터를 로라 단말로 전달할 수 있습니다. 이를 통해 다른 통신 시스템과의 연동이 가능해지며, 데이터를 중앙 집중적으로 처리하거나 다른 시스템과 통합하는 데 유용합니다.

네트워크 관리와 감시: 로라 게이트웨이는 로라 단말과의 통신을 관리하고 모니터링할 수 있는 기능을 제공합니다. 게이트웨이를 통해 통신 상태, 데이터 전송량, 단말 등록 관리 등을 모니터링하고 관리할 수 있어 네트워크 운영 및 유지보수에 도움을 줍니다.

로라 게이트웨이의 단점:

추가 비용과 복잡성: 로라 게이트웨이를 구축하려면 추가적인 비용과 복잡성이 발생할 수 있습니다. 게이트웨이 장비를 구매하고 설치해야 하며, 게이트웨이와 네트워크 간의 연결과 구성을 관리해야 합니다.

싱글 포인트 오브 실패(SPOF): 로라 게이트웨이는 로라 단말과 네트워크 간의 중개 역할을 수행하기 때문에 게이트웨이 장애가 발생하면 해당 게이트웨이와 연결된 단말들과의 통신에 문제가 발생할 수 있습니다. 이는 게이트웨이를 신뢰성 있게 운영하고 백업 계획을 수립해야 함을 의미합니다.

추가적인 관리 및 유지보수: 로라 게이트웨이는 추가적인 관리와 유지보수가 필요합니다. 게이트웨이와 관련된 네트워크 인프라의 유지보수, 업그레이드, 보안 강화 등을 수행해야 합니다.

로라 게이트웨이의 존재는 네트워크 확장성과 다른 네트워크와의 연결성을 제공하는 장점이 있지만, 추가 비용과 복잡성, 싱글 포인트 오브 실패(SPOF) 등의 단점을 고려해야 합니다. 따라서, 구체적인 요구사항과 환경을 고려하여 로라 게이트웨이의 사용 여부를 결정하는 것이 중요합니다.

위성 통신은 로라 통신과는 별개의 통신 기술입니다. 위성에서 데이터를 로라 게이트웨이로 전송할 때, 위성 통신 모듈을 사용하여 위성과의 통신을 수행하고, 로라 게이트웨이는 해당 통신 모듈과 연결하여 데이터를 수신하게 됩니다.

로라 게이트웨이는 로라 기반 보드로부터 전달받은 데이터를 로라 프로토콜을 사용하여 지상국의 서버로 전송할 수 있습니다. 이 경우에는 로라 통신을 사용하여 데이터를 전달합니다.

따라서, 위성에서 로라 게이트웨이로 데이터를 전송할 때, 위성 통신 모듈을 사용하고, 로라 게이트웨이는 해당 통신 모듈과 연동하여 데이터를 수신한 후, 로라 통신을 사용하여 로라 기반 보드로 데이터를 전송할 수 있습니다.

이러한 구성으로 위성과 로라 게이트웨이 간의 데이터 전송과 로라 기반 보드를 통한 지상국의 서버로의 데이터 전달이 가능합니다. 위성 통신과 로라 통신은 별개의 기술이므로, 로라 게이트웨이와 로라 기반 보드를 사용하여 로라 통신을 수행할 수 있습니다.

### 지상국에서 로라 게이트웨이와 로라 기반 보드를 사용하면, 위성에서 어떤 위성 통신 방식을 사용하던지 위성에서 지상국으로 데이터를 전송할 수 있습니다


### 9/4 지상국의 데이터 처리
1. 지상국과 위성국의 데이터는 시리얼 또는 유아트로 연결되었다고 가정
2. 자바 스프링으로 구현하고 라즈베리파이의 연결된 모듈에서 데이터가 수신되었다는 부분에서 시작

1. 라즈베리파이의 수신모듈에서 데이터가 들어올 수 있도록 시리얼 UART를 연결
    라즈베리파이에서 자바로 시리얼 통신하기위해 jSerialComm 사용

2. @Component는 Spring Bean으로 등록하는것
    Spring Bean -> @Autowired로 자동 주입을 통해 사용 가능

SerialReader 클래스
이 클래스는 jSerialComm 라이브러리를 사용하여 시리얼 포트에서 데이터를 읽어오는 역할을 합니다. readData() 메서드는 시리얼 포트에 연결된 장치로부터 데이터를 읽어와 문자열 형태로 반환합니다.

Application 클래스
Spring Boot 애플리케이션의 진입점입니다. 여기서 Spring Boot 애플리케이션을 시작하는데 필요한 설정들이 초기화됩니다.

DataController 클래스
웹 요청을 처리하는 컨트롤러입니다. /api/data/read 경로로 GET 요청이 들어오면, 이 요청을 처리하여 시리얼 포트에서 읽은 데이터를 클라이언트에게 반환합니다.

DataService 클래스
실제 비즈니스 로직을 수행하는 서비스 계층입니다. 이 서비스는 SerialReader 인스턴스를 사용하여 시리얼 포트에서 데이터를 읽어옵니다.

HTML + JavaScript 클라이언트 사이드 코드
웹 페이지에서 /api/data/read API 호출 결과를 가져와 화면에 표시합니다.

결국, 이 모든 코드들은 라즈베리파이의 시리얼 포트에서 데이터를 읽어와 웹 페이지에 표시하는 전체적인 작업 흐름을 구현한 것입니다.

### 데이터베이스 연결 ( Spring Boot ) 
Spring Boot에 JDBC를 통해 mysql or mariadb를 사용하기
Dependency와 application.properties에 간단한 설정만 하면 MyBatis 및 MariaDB 연결은 완료된다.

#### Dependency 추가
implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.2' // 버전은 스프링부트에 맞춰서 사용
implementation 'mysql:mysql-connector-java:8.0.33' // mysql일 경우
implementation 'org.mariadb.jdbc:mariadb-java-client:3.1.4' //mariadb의 경우

#### MyBatis란?
개발자가 지정한 SQL, Procedure, 기타 등등 매핑을 지원하는 프레임워크
JDBC로 처리하는 상당 부분의 코드, parameter settings, result등을 매핑하도록 도와준다.

#### MyBatis-Spring
MyBatis-Spring 라이브러리를 통해 MyBatis가 Spring Transaction에 쉽게 연동될 수 있다.
Mapper와 SqlSession을 다루고, 다른 Bean에 주입시켜주며, Mybatis Exception을 Spirng DataAccessException으로 변환시켜준다.

#### application.properties에 추가
* 주의 * 깃허브 등록시 이 파일은 ignore로 올리지 않아야함
   
spring.datasource.driverClassName=org.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=UTC
spring.datasource.username=스키마계정
spring.datasource.password=비밀번호

mariadb
#spring.datasource.driverClassName=org.mariadb.jdbc.Driver
#spring.datasource.url=jdbc:mariadb://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=UTC

oracle
#spring.datasource.driverClassName=net.sf.log4jdbc.sql.jdbcapi.DriverSpy
spring.datasource.url=jdbc:log4jdbc:oracle:thin:@localhost:1532/test

#### Mapper 추가
1) mapper.xml 템플릿 생성

https://goddaehee.tistory.com/205
