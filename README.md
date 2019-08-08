# mop-android
> MoP(Meeting of People) 동호회 어플리케이션

## About
기존 동호회 관련 어플리케이션들을 분석한 결과 동호회원들간의 소통과 게시판 기능에 집중되어 있었습니다. 저는 자전거 동호회, 산악 동호회와 같이 안정성과 편의성을 이유로 회원들간의 위치 파악이 필요한 동호회 어플리케이션이 있으면 좋겠다고 생각했지만 기존 동호회 어플리케이션들은 이에 관한 기능들이 부족합니다. 
‘MoP(Meeting of People)’은 기본적인 동호회 기능에 더해 일정에 참여하는 회원들간의 위치정보 공유를 주 기능으로 하는 애플리케이션입니다.

## Fuction
1) 일정 관리
2) 단체 지도방
3) 그 외
4) 일일 통계 기능(~ing)
  + 매일 자정이 지나면 배치 서버에서 동호회 일일 통계를 추출한 후 동호회장에게 알림을 보낸다(일반 회원 제외)
  + 동호회장은 일별 회원가입 인원 수, 회원가입 인원의 정보, 게시글 통계정보를 확인 할 수 있다

## Using
* Firebase Realtime Database
* Google Map
* Retrofit(HTTP)
* Glide(Image Loader)
* Java 8(Language)
* Xml(Language)
* Etc..

## Uniqueness
* 디자인 패턴은 [Google MVP Architecture](https://github.com/googlesamples/android-architecture/tree/todo-mvp/) 참고, 변형  
* 코드 규칙은 [Android Style Guide](https://github.com/PRNDcompany/android-style-guide)를 따름
