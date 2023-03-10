# BoardProject
# ✨목표
- 여태 배웠던 기술을 총 집합하여 간단한 게시판 서비스를 제공할 수 있는 웹 서비스 만들기
- 포트폴리오로 쓸 수 있는 프로젝트로 만들기
- 점진적으로 계속 기능을 추가해 나갈 것

# 🗃기술 스택
- Spring boot : 2.7.7
- Spring Data JPA : 2.7.7
    - Hibernate : 5.6.14.Final
- Spring boot devtools : 2.7.7
- Build Tool : Gradle
- Database
    - 서비스 - PostgreSQL
    - 테스트 - H2 : 2.1.214
- Swagger : 3.0.0
- JUnit5/AssertJ : 5.8.2/3.22.0
- Lombok : 1.18.24
- ****[JavaMail API JAR](https://mvnrepository.com/artifact/javax.mail/javax.mail-api)****
    - [https://usang0810.tistory.com/27](https://usang0810.tistory.com/27)

# 🚀기능 요구사항
- [x] 로그인
- [ ] 회원가입
  - [ ] 이메일 중복 체크
  - [ ] 이메일 인증
  - [ ] 닉네임 중복 체크
- [ ] 게시판 CRUD
- [ ] 댓글 CRUD
- [x] 게시글 좋아요 CRUD
- [x] 댓글 좋아요 CRUD
- [ ] 마이페이지
  - [x] 사용자 정보 조회
  - [ ] 작성한 게시글 조회
  - [ ] 작성한 댓글 조회
  - [ ] 작성한 좋아요 조회
- [ ] 관리자 페이지
  - [ ] 게시글 관리 기능
  - [ ] 댓글 관리 기능
  - [ ] 좋아요 관리 기능

# API 문서
- ****http://localhost:8080/swagger-ui/index.html#/****