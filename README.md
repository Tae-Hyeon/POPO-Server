# POPO-Server
only for 한여름.. 한태현.. 

# 고치고 싶은 점
1. Response를 내가 만든 Response 객체가 아니라 ResponseEntity를 사용하기
2. Service에서 데이터만 넘겨주고 Controller에서 Response 생성하기
    - 현재 Service에서 Response를 생성해 넘겨줌
3. Service 내의 몇 로직들을 분리하기 --> 분리 후 AOP적용해서 권한체크 같은 것들 하기
    - 분리하면 가독성도 높아질 것 같음. 현재 더러워보임
4. 네이밍 컨벤션 제대로 하나 정하기
    - ex) 약어 : DTO or Dto
