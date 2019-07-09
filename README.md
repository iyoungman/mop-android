# Product Name
> MoP(Meeting of People) 안드로이드

## About
기존 동호회 관련 어플리케이션은 다양한 동호회 분류와 채팅, 게시판 기능에 집중되어 있었다. 하지만 자전거 동호회, 산악 동호회 같이 안정성과 편의성을 이유로 
서로의 위치를 파악하는 것이 중요한 동호회를 대상으로 어플리케이션 내에서 동호회 기능은 물론 회원들간의 위치정보 공유를 주 기능으로 하여 지원한다면 좀 더 편리한 동호회 활동을 할 수 있다고 생각하였다. 
또한, 동호회장은 해당 동호회의 일일 통계를 확인 할 수 있다

## Fuction
* 단체 지도방
  + 동호회장은 동호회 일정이 있는 날 단체 지도방을 만들 수 있다
  + 단체 지도방에 초대된 동호회 멤버들간에는 위치정보를 공유할 수 있다
  + 주요 기능으로 다른 멤버들의 위치 파악, 자신의 이동 경로 확인, 새로운 멤버 초대, 단체 지도방 나가기
* 일일 통계 기능
  + 매일 자정이 지나면 배치 서버에서 동호회 일일 통계를 추출한 후 동호회장에게 알림을 보낸다(일반 회원 제외)
  + 동호회장은 일별 회원가입 인원 수, 회원가입 인원의 정보, 게시글 통계정보를 확인 할 수 있다
* 일정 관리
  + 기존 대부분의 동호회 어플리케이션에서 단순히 목록으로 구현되어있던 반면 일정 파악이 쉽게 달력 UI로 구현
  + 일정이 있는 날을 클릭할 경우 해당 날짜의 일정 정보를 표시
* 게시판
  + 게시판을 통해서 동호회 회원들 간의 자유로운 의사소통을 보장

## Design Pattern
* [Google MVP Architecture](https://github.com/googlesamples/android-architecture/tree/todo-mvp/) 참고, 변형
