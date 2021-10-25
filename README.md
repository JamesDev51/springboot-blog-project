# Springboot-blog-project
기본적인 CRUD 구현을 위한 블로그 만들기

## Core packages
1. Spring-boot
2. Spring-security
3. Spring-jpa
4. Thymeleaf
5. My-sql

## Features
1. SignUp/SignIn
2. CRUD Post
3. CRUD Comment
4. Edit Profile
5. Remember-me
6. Search for title or content

## UI
### HOME
페이징 처리
auth에 따라서 헤더 변경
![홈화면](https://user-images.githubusercontent.com/66056101/138718758-cd493eac-171f-4af1-ba8a-6c2660c2cf3b.png)

### Search
제목 & 내용 키워드로 검색 가능
![검색기능](https://user-images.githubusercontent.com/66056101/138719520-7d83701e-ffd7-4d70-957b-47e993f70e6c.png)


### SignUp
프론트 & 백 유효성 검사
이메일 중복확인
![회원가입](https://user-images.githubusercontent.com/66056101/138718951-4d9f2f93-a98d-4e58-b804-a7f4989591f8.png)

이메일이 중복되는 경우
![이메일사용중](https://user-images.githubusercontent.com/66056101/138719224-18fb1ac1-c73d-4b8e-a19d-477752219b67.png)
이메일 사용이 가능한 경우
![이메일사용가능](https://user-images.githubusercontent.com/66056101/138719239-ba8e5769-78a0-4aba-bc50-6860c5a75ccb.png)


### SignIn
프론트 유효성 검사
리멤버미 기능(세선 유지 2주)
![로그인 리멤버미](https://user-images.githubusercontent.com/66056101/138719075-ac137ddd-56e0-4be9-b50a-f71e2ad4869d.png)

### Edit User Profile
비밀번호 유효성 검사
![유저수정](https://user-images.githubusercontent.com/66056101/138719372-b5471cd6-c90e-4123-8cc8-9431a3d0567c.png)

### Write Post
![글쓰기](https://user-images.githubusercontent.com/66056101/138719555-6b333a19-8ad5-4dd7-b868-e2d1ad283a64.png)

### Write Comment
![댓글](https://user-images.githubusercontent.com/66056101/138719588-fef8e840-d960-4d3b-8a9d-fffaedd31a85.png)

### Auth에 따른 글 권한 
글 쓴사람 == 현재 Authentication.principal
![글 AUTH일때](https://user-images.githubusercontent.com/66056101/138719719-c5b2446d-f870-4561-a61c-3628d79d248d.png)

글 쓴사람이 아닐 때
![글 auth아닐때](https://user-images.githubusercontent.com/66056101/138719762-70b410d3-fa46-4b2c-9310-1e3d050a3442.png)


