# POPO-Server
only for 한여름.. 한태현.. 

# 고치기
0. JAVA, Spring 구조에 대해 다시 공부하기
  - 현재 기본 개념이 매우 부족
  - JVM 구조, Static 메소드가 어느 메모리 영역에 저장되는지, 인터페이스에 대해, 람다 등등
  - Spring Bean, Containter, @Component, JPA Entity 등등
1. @Transactional 메소드 단위로 적용하기 : 현재 클래스 단위
  - 남발하지 말것
2. Repository 사용 시 DTO로 변환해서 받지 말고 무조건 Entity로 바꾼 뒤 Entity에 DTO로 바꾸는 메소드 작성해서 바꾸기
3. 용어 재정립
  - DAO -> Repository
  - API -> Contoller (Interface의 역할, 의미 대해 공부)
4. Autowired로 필드 주입 하는 것들을 생성자 주입으로 바꾸기
  - 이유도 공부하고, Component, Service의 의미에 대해서 공부하기
5. new로 생성하던 것들 바꾸기
  - 객체 생성 -> build
  - Util 새성 -> 생성자 주입 후 스프링 빈으로 관리
    - 싱글톤에 대해 공부
6. 코드만 읽어도 이해가 되게 네이밍 잘 생각해서 바꾸기.
  - 리팩토링이 필요할 경우 미리미리 해놓기
7. 프로젝트 구조를 도메인 주도로 할거면 도메인에 대해 정확히 이해하기
  - 현재 잘못 나누었음, Boundary Context, Aggregate 등등
  - 안될거면 차라리 레이어 아키텍처로 다 바꾸기
8. 이미지를 올릴 경우 이미지 올리는게 너무 오래 걸리니까, 이미지 올리는 것과 실제 로직을 분리
  - 로직에서는 url만 String으로 받아 빠르게 처리
9. DB 구조에 대해서 생각
  - 현재 DB구조가 이상함
  - Option에 대해 Json으로 Popo에서 관리해도 됨.
  - 마찬가지로 Content도 Day에서 Json으로 관리해도 됨.
  - 현재 앱에서 DB자체를 RDBMS를 사용한 것이 이해 안됨 --> NoSQL로 하는게 좋음
    - DB 사용에 대해 미리 고려하기



# 고치고 싶은 점
1. Response를 내가 만든 Response 객체가 아니라 ResponseEntity를 사용하기
2. Service에서 데이터만 넘겨주고 Controller에서 Response 생성하기
    - 현재 Service에서 Response를 생성해 넘겨줌
3. Service 내의 몇 로직들을 분리하기 --> 분리 후 AOP적용해서 권한체크 같은 것들 하기
    - 분리하면 가독성도 높아질 것 같음. 현재 더러워보임
4. 네이밍 컨벤션 제대로 하나 정하기
    - ex) 약어 : DTO or Dto
