<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="/yorijori/common/css/reset.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<script type="text/javascript">
const signInBtn = document.querySelector('#toggleSignIn');
const signUpBtn = document.querySelector('#toggleSignUp');

const signUpForm = document.querySelector('#sign-up-container');
const signInForm = document.querySelector('#sign-in-container');

// Change form when clicking on button
const changeForm = (form1, form2) => {
  form1.classList.toggle('hide');
  form2.classList.toggle('hide');
}

// Show the Sign In form
signInBtn.addEventListener('click', () => {
  changeForm(signUpForm, signInForm);
});

// Show the Sign Up form
signUpBtn.addEventListener('click', () => {
  changeForm(signUpForm, signInForm);
});
</script>
<link rel="stylesheet" href="/yorijori/common/css/member/register.css" />
</head>
<body>
<div id="form-container">
      <div id="form-inner-container">
        <!-- Sign up form -->
        <div id="sign-up-container">
          <h3 style="color: orange">sign up</h3>
          <form>
            <label for="name">Name</label>
            <input type="text" name="name" id="name" placeholder="2자 이상 입력">
            
            
			<label for="Nickname">Nickname</label>
            <input type="text" name="Nickname" id="Nickname" placeholder="Nickname">
         
            <label for="email">Email</label>
            <input type="email" name="email" id="email" placeholder="id@domain.com">

  			<label for="name">id</label>
            <input type="text" name="id" id="id" placeholder="4자 이상 입력">
            <!-- keyup 추가 예정  -->
           
            <label for="password">Password</label>
            <input type="password" name="password" id="password" placeholder="8~16자의 영문 대소문자와 숫자로만 입력">
            
			<label for="password">Password확인</label>
            <input type="password" name="password" id="password" placeholder="비밀번호 확인">
            
			<label for="ssn">주민등록번호</label>
            <input type="text" name="ssn" id="ssn" placeholder="주민등록번호">
            
            <label for="prefer_food">가입목적</label>
            <input type="text" name="prefer_food" id="password">
            
           	<label for="prefer_food">선호식품</label>
            <input type="text" name="prefer_food" id="password">
		
			<label for="allergy">알레르기</label>
            <input type="text" name="allergy" id="allergy">
		
            <div id="form-controls">
              <button type="submit">Sign Up</button>
              <button type="button" id="toggleSignIn">Sign In</button>
            </div>

            <input type="checkbox" name="terms" id="terms">
            <label for="terms">I agree to the <a href="#" class="termsLink">Terms of service</a> and <a href="#" class="termsLink">Privacy Policy</a>.</label>
          </form>
        </div>

        <!-- Sign in form -->
        <div id="sign-in-container" class="hide">
          <h3>Welcome Back</h3>
          <form>
            <label for="username">Username</label>
            <input type="text" name="username" id="username" placeholder="user@example.com">

            <label for="password">Password</label>
            <input type="password" name="password" id="password" placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;">

            <div id="form-controls">
              <button type="submit" ">Sign In</button>
              <button type="button" id="toggleSignUp">Sign Up</button>
            </div>

            <input type="checkbox" name="terms" id="terms">
            <label for="terms">I agree to the <a href="#" class="termsLink">Terms of service</a> and <a href="#" class="termsLink">Privacy Policy</a>.</label>
          </form>
        </div>


      </div>
  </div>

  <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
  <script type="text/JavaScript" src="./my-script.js"></script>
</body>
</html>