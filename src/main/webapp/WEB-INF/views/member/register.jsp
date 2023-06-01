<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<style type="text/css">
body {
    background-color: #F3F4F9;
    margin: 0;
    padding: 0;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
}

#form-container {
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

#form-inner-container {
    background-color: white;
    max-width: 70%;
    border-radius: 8px;
    box-shadow: 0 0 20px gainsboro;
}

#sign-up-container, #sign-in-container {
    padding: 60px 130px;
    width: 800px;
    display: inline-block;
}

form input:not(:last-of-type) {
    display: block;
    margin-bottom: 20px;
    border: 1px solid #E5E9F5;
    background-color: #F6F7FA;
    padding: 20px;
    margin-top: 10px;
    border-radius: 10px;
    width: 100%;
}

#form-controls {
    margin-bottom: 20px;
}


h3 {
    color: red;
    font-size: 150%;
    font-weight: 500;
}

label {
    color: #7369AB;
}

::placeholder {
    color: #C0C7DB;
    font-size: larger;
    letter-spacing: 1.2px;
}

#form-controls button {
    border: none;
    font-size: 120%;
}

#form-controls button:hover {
    cursor: pointer;
}

button[type="submit"] {
    padding: 16px 75px;
    background-color: orange;
    border-radius: 10px;
    color: white;
}

button[type="submit"]:hover {
    background-color: #ff6678;
}

button[type="button"] {
    padding: 16px 0 16px 35px;
    background-color: transparent;
    color: orange;
}

#terms {
    width: 30px;
    height: 30px;
    appearance: none;
    border: 2px solid #7369AB;
    border-radius: 4px;
    position: relative;
}

#terms:checked:after {
    content: '\2713';
    color: #7369AB;
    font-size: 24px;
    position: absolute;
    top: 0;
    left: 3px;
}

label[for="terms"] {
    display: inline-block;
    width: 80%;
    margin-left: 10px;
}

.termsLink {
    color: orange;
    text-decoration: none;
}

.hide {
    display: none!important;
}

#animation-container {
    display: inline-block;
}

/* responsive display */

@media(max-width:1438px) {
    lottie-player {
        width: 300px!important;
    }
}

@media(max-width:1124px) {
    #animation-container {
        display: none;
    }

    #form-inner-container{
        display: flex;
        justify-content: center;
    }
}

@media(max-width: 684px) {
    #form-controls {
        text-align: center;
        margin: 0;
        padding: 0;
    }

    button {
        width: 100%;
        background-color: orange;
    }

    form input:not(:last-of-type) {
        width: 85%;
    }

    #toggleSignIn, #toggleSignUp {
        padding: 16px 75px;
    }

    #terms {
        width: 20px;
        height: 20px;
    }
    
    label[for="terms"] {
        display: inline-block;
        font-size: smaller;
    }
}

</style>
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
</head>
<body>
<div id="form-container">
      <div id="form-inner-container">
        <!-- Sign up form -->
        <div id="sign-up-container">
          <h3 style="color: orange">sing up</h3>
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