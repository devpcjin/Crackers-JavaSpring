# Cracker-JavaSpring
## 1. 프로젝트 개요
- 개발기간 : 2022.06.24 ~ 2022.07.29
- 참여인원 : 5명
- 소개 : 사이드 프로젝트 기획 / 맛집 커뮤니티 서비스
- https://crackers.life

## 2. 사용기술
- Backend : `Java 11` `Spring Boot 2.6.9` `Spring Data JPA` `Gradle 7.4.1`
- Frontend : `JQuery` `Javascript` `Bulma` `HTML 5` `CSS 3`
- Database : `AWS RDS` `MySQL 8.0.28` `H2`
- Security : `Spring Security`
- Cloud : `AWS S3` `AWS EC2` `AWS CodeDeploy` `AWS Secret Manager`
- CI/CD : `Github Actions`

## 3. ERD 설계
![image](https://user-images.githubusercontent.com/103913683/184919972-765ff552-e644-4aa2-b074-2b787a185516.png)

## 4. 아키텍쳐
![cracker_architecture_2](https://user-images.githubusercontent.com/103913683/184920468-818bb09c-f3de-47fa-a84a-6c54f9bd1fbb.png)

## 5. 맡은 기능
대표기능 : 각 객체별 연관관계 생성 / 배포 자동화
- 프로젝트 팀장으로 전체 프로젝트 관리
  - Github 관리 - PR 관리 및 merge전 최종 리뷰
- 맛집 공유 및 저장 기능
  - Kakao Place API를 활용한 맛집 검색 기능
  - Naver Map API를 활용한 맛집 마커 표시
- 배포자동화
  - Github Action과 AWS Code Deploy를 활용한 배포 자동화
  - AWS CodeDeploy를 활용한 배포 자동화
  - nginx 기본 환경 설정
- 기타
  - 각 객체별 연관관계 생성
  - XSS 보안

## 6. 기억에 남는 기능
### 객체 사이의 연관관계 메서드 생성
- 프로젝트를 진행하면서 필수로 구현해야했던 코드이지만 잘 몰라서 본인을 가장 힘들게 했던 부분이 객체 사이의 연관관계 메서드를 구현하는 일이었습니다. 데이터 베이스 관점이 아닌 JPA 객체 관점에서 생각을 하면서 연관관계를 설정하여야해서 가장 힘들었습니다.
- 처음으로 연관관계를 설정하였던 부분이 바로 `User - Place`사이의 연관관계 메서드였습니다.
  - 한명의 멤버는 여러개의 맛집을 등록할 수 있고, 저장된 맛집을 통해 유저 목록을 찾아야 하므로 `OneToMany`, `ManyToOne` 양방향관계로 설정했습니다.
<details>
<summary>User와 Place 사이의 연관관계 메서드</summary>
| Place.java

[Place의 연관관계 메서드](https://github.com/devpcjin/Crackers-JavaSpring/blob/956e1a7b56a6d6654dada03bee2b2ce4a38b6622/src/main/java/com/cracker/place/entity/Place.java#L47)

| User.java

[User의 연관관계 메서드](https://github.com/devpcjin/Crackers-JavaSpring/blob/956e1a7b56a6d6654dada03bee2b2ce4a38b6622/src/main/java/com/cracker/user/entity/Users.java#L58)
</details>

## 7. 고객 피드백
<details>
<summary>피드백 내용</summary>
- 만족도
![image](https://user-images.githubusercontent.com/103913683/186093272-51bf6a77-ca8f-41d6-ac20-f7ce4a330b36.png)
- 불편했던 점
![image](https://user-images.githubusercontent.com/103913683/186093554-0b2c01b1-7d65-4b2a-b247-17d16b56d821.png)
- 좋았던 점
![image](https://user-images.githubusercontent.com/103913683/186093705-619ea50f-9175-4a29-b059-de22e073001a.png)
- 상세 피드백 요약
[상세 피드백 요약 링크](https://goofy-draw-ced.notion.site/cfdfc381933a46ff91ceed1cafbfd1ab)
</details>

## 8. 트러블 슈팅
### 8-1 [Database] Community 테이블 생성하기
- 초기상태
  - Community 테이블이 따로 존재하지 않고, Place 테이블로 해당 맛집의 커뮤니티 페이지 연결하도록 설정하였습니다.
- 문제
  - 서로 다른 유저가 동일한 맛집을 등록해도 저장된 맛집마다 다른 커뮤니티 페이지로 연결되는 문제가 발생했습니다.
- 해결
  - Community 테이블을 따로 생성하였습니다
  - 서로 다른 유저가 저장한 동일한 맛집이 Community 테이블에 연결되어 각각의 맛집이 동일한 커뮤니티로 묶어 위의 문제를 해결했습니다.
[관련 링크](https://goofy-draw-ced.notion.site/DB-n-m-62bcde9a15c84ad0935c8c5cb4562e5b)

### 8-2 XSS 보안 문제 해결
- XSS(Cross Site Script)란?
  - 웹 어플리케이션에서 사용자 입력 값에 대한 필터링이 제대로 이루어지지 않을 경우, 공격자가 입력이 가능한 폼에 악의 적인 스크립트를 삽입하여 해당 스크립트가 희생자 측에서 동작하도록하여 악의적인 행위를 수행하는 취약점을 말한다.


