# WeDataLabProject
위데이터랩 첫번째 과제입니다. (24.06.24 ~ 24.06.27)

---
## entity 관계
![KakaoTalk_20240626_180959341](https://github.com/jun23314/WeDataLabProject/assets/116951160/22a32b71-6f28-451e-aa9b-c849e24e730e)

### 1. User-Board
A. 좋아요   
- 좋아요 기능은 ManyToMany를 사용하여 다대다 relation으로 설정.
- user가 board에 좋아요를 누르면 어떤 유저가 보드에 좋아요를 눌렀는지 확인해야 하기 때문에 매핑 설정
- user는 좋아요를 누른 보드를 확인하지 않기 때문에 매핑 사용 x  

B. 작성자
- 작성자는 일대다 relation으로 설정
- User는 작성한 Board가 필요하기 때문에 매핑 설정
- Board는 어떤 user가 작성했는지 필요하기 때문에 매핑 설정   
