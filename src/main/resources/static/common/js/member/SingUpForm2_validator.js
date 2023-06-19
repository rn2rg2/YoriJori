let elInputUsername = document.querySelector('#user_id')
let elInputPassword = document.querySelector('#password')
let elInputPasswordretype = document.querySelector('#password-retype')
let elNumbertype = document.querySelector('#phonenumber')
let elJoinbutton = document.querySelector('#joinbutton')

let elFailuremessage = document.querySelector('.failure-message')
let elSuccessmessage = document.querySelector('.success-message')
let elMismatchmessage = document.querySelector('.mismatch-message')
let elMisnumbermessage = document.querySelector('.misnumber-message')

elJoinbutton.disabled = true;

//아이디 입력창에 글자를 키보드로 입력할 때, 성공메시지와 실패메시지를 보여주는 함수
elInputUsername.onkeyup = function () {
  if (isMoreThan4Length(elInputUsername.value)) {
    elSuccessmessage.classList.remove('hide')
    
    elFailuremessage.classList.add('hide')
  }
  else {
    elSuccessmessage.classList.add('hide')

    elFailuremessage.classList.remove('hide')
  }
}

//글자수가 4개이상이면
function isMoreThan4Length(value) {
  return value.length >= 4
}

//비밀번호 입력창에 값을 입력하면, 비밀번호 값과 비밀번호 확인값이 일치하지 않으면 불일치 메시지를 표시하는 함수
elInputPasswordretype.onkeyup = function () {
  if ( isMatch(elInputPassword.value, elInputPasswordretype.value) ) {
    elMismatchmessage.classList.add('hide')
  }
  else {
    elMismatchmessage.classList.remove('hide')
  }
}

//비밀번호 값과 비밀번호 확인값이 일치하는지 판별하는 함수
function isMatch (password1, password2) {
  if ( password1 === password2 ) {
    return true;
  }
  else {
    return false;
  }
}

//전화번호 값을 입력할때, 숫자가 아니면 불일치 메시지를 표시하는 함수
elNumbertype.onkeyup = function () {
  if (isNumbermatch(elNumbertype.value)) {
    elMisnumbermessage.classList.add('hide')
  }
  else {
    elMisnumbermessage.classList.remove('hide')
  }
}

//전화번호 값을 숫자값만 받는 함수
function isNumbermatch(value) {
  let phonenumber = /^[0-9]*$/
  if ( phonenumber.test(value) === true ) {
    return true;
  }
  else {
    return false;
  }
}

elInputUsername.addEventListener('keyup', button)
elInputPassword.addEventListener('keyup', button)
elInputPasswordretype.addEventListener('keyup', button)
elNumbertype.addEventListener('keyup', button)

//아이디, 비밀번호, 비밀번호 확인, 전화번호 값을 모두 입력하였을 때, 회원가입 버튼이 활성화 되는 함수
function button() {
  switch (!(elInputUsername.value && elInputPassword.value && elInputPasswordretype.value && elNumbertype.value)) {
    case true : elJoinbutton.disabled = true; break;
    case false : elJoinbutton.disabled = false; break
  }
}