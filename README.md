# mop-android
> MoP(Meeting of People) 동호회 애플리케이션

## About
기존 동호회 관련 애플리케이션들을 분석한 결과 동호회원들간의 소통과 게시판 기능에 집중되어 있었습니다. 저는 자전거 동호회, 산악 동호회와 같이 안정성과 편의성을 이유로 회원들간의 위치 파악이 필요한 동호회 애플리케이션이 있으면 좋겠다고 생각했지만 기존 동호회 애플리케이션들은 이에 관한 기능들이 부족합니다. 
‘MoP(Meeting of People)’은 기본적인 동호회 기능에 더해 **일정에 참여하는 회원들간 실시간성 위치정보 공유를 주 기능으로 하는 애플리케이션입니다.**

## Fuction
1) 일정 관리  

<img src = "https://user-images.githubusercontent.com/25604495/83977013-bc432080-a938-11ea-9357-c1d834b8504f.png" width="800" height="500" />  

***

2) 단체 지도방

<img src = "https://user-images.githubusercontent.com/25604495/83977034-de3ca300-a938-11ea-92ae-125684f14782.png" width="800" height="500" /> 

<br>  

<img src = "https://user-images.githubusercontent.com/25604495/83977056-f44a6380-a938-11ea-8b48-b89bf73fd9e1.png" width="800" height="500" /> 

***

3) 동호회 통계(1~2), My 동호회(3)  

<img src = "https://user-images.githubusercontent.com/25604495/83977091-fa404480-a938-11ea-952e-71b4fcb378e8.png" width="800" height="500" />

## Using
* Firebase Realtime Database
* Google Map API
* TMap API(보행자 경로)
* Retrofit(HTTP)
* Glide(Image Loader)
* Java
* Etc..

## Uniqueness
* 디자인 패턴은 [Google MVP Architecture](https://github.com/googlesamples/android-architecture/tree/todo-mvp/) 참고, 변형  
* 코드 규칙은 [Android Style Guide](https://github.com/PRNDcompany/android-style-guide)를 따름
