document.addEventListener('DOMContentLoaded', function() {
  let elInputUsername = document.querySelector('#userId');
  let elButtonIdCheck = document.querySelector('#btn-id-check');
  let elInputNickname = document.querySelector('#nickName');
  let elButtonNicknameCheck = document.querySelector('#btn-nickname-check');
  let elInputName = document.querySelector('#requiredname');
  let elInputEmail = document.querySelector("#e-mail");
  let elInputPassword = document.querySelector('#password');
  let elInputPasswordCheck = document.querySelector('#passwordCheck');
  let elInputSsn1 = document.querySelector('#ssn1');
  let elInputSsn2 = document.querySelector('#ssn2');

  // 처음에 버튼 비활성화
  elButtonIdCheck.disabled = true;
  elButtonNicknameCheck.disabled = true;
  document.getElementById('select').addEventListener('change', validateForm);

  
  elInputUsername.onkeyup = function () {
    const elSuccessMessage = document.querySelector('.success-messageid');
    const elFailureMessageLength = document.querySelector('.failure-message-idlength');
    const elFailureMessageEnglish = document.querySelector('.failure-message-idenglish');
    let isValidEnglishNumbers = isEnglishAlphabetAndNumbers(elInputUsername.value);
    let isValidLengthRange = isLengthInRange(elInputUsername.value, 4, 16);

    if (isValidEnglishNumbers) {
      elFailureMessageEnglish.classList.add('hide');

      if (isValidLengthRange) {
        elSuccessMessage.classList.remove('hide');
        elFailureMessageLength.classList.add('hide');
      } else {
        elSuccessMessage.classList.add('hide');
        elFailureMessageLength.classList.remove('hide');
      }
    } else {
      elSuccessMessage.classList.add('hide');
      elFailureMessageLength.classList.add('hide');
      elFailureMessageEnglish.classList.remove('hide');
    }

    // 유효성 검사가 모두 맞으면 버튼 활성화, 아니면 비활성화
    if (isValidEnglishNumbers && isValidLengthRange) {
      elButtonIdCheck.disabled = false;
      elButtonIdCheck.style.backgroundColor = '#FD5D5D';
    } else {
      elButtonIdCheck.disabled = true;
      elButtonIdCheck.style.backgroundColor = ''; // 초기 상태로 되돌리기
    }
  }

  elInputNickname.onkeyup = function () {
    const elSuccessMessage = document.querySelector('.success-message');
    const elFailureMessageSpecialCharacters = document.querySelector('.failure-message-special-characters');
    const elFailureMessageLength = document.querySelector('.failure-message-length');

    let noSpecialCharacters = isNoSpecialCharacters(elInputNickname.value);
    let isValidLengthRange = isLengthInRange(elInputNickname.value, 3, 10);

    if (noSpecialCharacters) {
      elFailureMessageSpecialCharacters.classList.add('hide');
    } else {
      elFailureMessageSpecialCharacters.classList.remove('hide');
    }

    if (isValidLengthRange) {
      elFailureMessageLength.classList.add('hide');
    } else {
      elFailureMessageLength.classList.remove('hide');
    }

    if (noSpecialCharacters && isValidLengthRange) {
      elSuccessMessage.classList.remove('hide');
    } else {
      elSuccessMessage.classList.add('hide');
    }

    // 조건이 모두 통과하면 버튼 활성화, 아니면 비활성화
    elButtonNicknameCheck.disabled = !(noSpecialCharacters && isValidLengthRange);
  }

  function isLengthValid(input, minLength) {
	  return input.length >= minLength;
	}
  
  elInputName.onkeyup = function () {
	  const elFailureMessageNameSpecialCharacters = document.querySelector('.failure-message-name-special-characters');
	  const elFailureMessageNameSpecialCharacters2 = document.querySelector('.failure-message-name-special-characters2'); // 추가

	  let noSpecialCharacters = isNoSpecialCharacters(elInputName.value);
	  let isValidLength = isLengthValid(elInputName.value, 3);

	  if (noSpecialCharacters) {
	    elFailureMessageNameSpecialCharacters.classList.add('hide');
	  } else {
	    elFailureMessageNameSpecialCharacters.classList.remove('hide');
	  }

	  if (isValidLength) { // 추가
	    elFailureMessageNameSpecialCharacters2.classList.add('hide');
	  } else {
	    elFailureMessageNameSpecialCharacters2.classList.remove('hide');
	  }
	};

  const specialCharactersPattern = /[^a-zA-Z0-9\s@.]/;
  const elFailureMessageSpecialCharactersEmail = document.querySelector('.failure-message-special-charactersemail');


  function validateWithoutSpecialCharacters() {
	  let isValidLength = isLengthValid(elInputEmail.value, 4); // 입력 길이 검사 추가

	  if (specialCharactersPattern.test(elInputEmail.value) || !isValidLength) { // 길이 조건 추가
	    elFailureMessageSpecialCharactersEmail.classList.remove("hide");
	  } else {
	    elFailureMessageSpecialCharactersEmail.classList.add("hide");
	  }
	}

  elInputEmail.oninput = validateWithoutSpecialCharacters;

  const minLength = 6;
  const maxLength = 16;
  const passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[a-zA-Z\d!@#$%^&*]{6,16}$/;

  const ssn1Pattern = /^\d{0,6}$/;
  const ssn2Pattern = /^\d{0,7}$/;
  

  
  
  elInputEmail.addEventListener('input', validateForm);


  // 주민등록번호 입력 변경 시 유효성 검사
  elInputSsn1.addEventListener('input', validateSsn1);
  elInputSsn2.addEventListener('input', validateSsn2);
  
  elInputSsn1.addEventListener('input', validateSsn1);
  elInputSsn2.addEventListener('input', validateSsn2);

  function validatePassword() {
    const elSuccessMessage = document.querySelector('.success-messagepass');
    const elFailureMessagePasslength = document.querySelector('.failure-message-passlength');
    const elFailureMessagePassenglish = document.querySelector('.failure-message-passenglish');
    const isValid = passwordPattern.test(elInputPassword.value);

    if (isValid) {
      elSuccessMessage.classList.remove('hide');
      elFailureMessagePasslength.classList.add('hide');
      elFailureMessagePassenglish.classList.add('hide');
    } else {
      elSuccessMessage.classList.add('hide');
      if (elInputPassword.value.length < minLength || elInputPassword.value.length > maxLength) {
        elFailureMessagePasslength.classList.remove('hide');
      } else {
        elFailureMessagePasslength.classList.add('hide');
      }
      if (/^[a-zA-Z0-9]+$/.test(elInputPassword.value) || !/^[a-zA-Z0-9!@#$%^&*]+$/.test(elInputPassword.value)) {
        elFailureMessagePassenglish.classList.remove('hide');
      } else {
        elFailureMessagePassenglish.classList.add('hide');
      }
    }

    validatePasswordMatch();
  }

  function validatePasswordMatch() {
    const elSuccessMessagePassonelength = document.querySelector('.failure-message-passonelength');
    const elFailureMessagePassonelengthfalse = document.querySelector('.failure-message-passonelengthfalse');
    const isMatched = elInputPassword.value === elInputPasswordCheck.value;

    if (isMatched) {
      elSuccessMessagePassonelength.classList.remove('hide');
      elFailureMessagePassonelengthfalse.classList.add('hide');
    } else {
      elSuccessMessagePassonelength.classList.add('hide');
      elFailureMessagePassonelengthfalse.classList.remove('hide');
    }
  }

  elInputSsn1.oninput = validateSsn1;
  elInputSsn2.oninput = validateSsn2;
  elInputPassword.onkeyup = validatePassword;
  elInputPasswordCheck.onkeyup = validatePasswordMatch;
  
  const elNum2 = document.querySelector('input[name="num2"]');
  const elNum3 = document.querySelector('input[name="num3"]');

  const MAX_DIGITS = 4; // 입력 가능한 최대 자릿수

  elNum2.addEventListener('input', limitInputDigits);
  elNum3.addEventListener('input', limitInputDigits);

  
  const elInputPhoneNums = document.getElementsByClassName('selectPhone');
  const elInputPhoneNum1 = elInputPhoneNums[0];
  const elInputPhoneNum2 = elInputPhoneNums[1];
  
  
  
  function limitInputDigits(event) {
	  const inputField = event.target;
	  const currentValue = inputField.value;
	  const numericValue = currentValue.replace(/\D/g, ''); // 숫자 이외의 문자 제거
	  const limitedValue = numericValue.slice(0, MAX_DIGITS); // 최대 자릿수까지 잘라냄

	  inputField.value = limitedValue;
	}

  function isPhoneNumberValid(value1, value2) {
	  const phoneNumberPattern = /^\d{4,}$/; // 4개 이상의 숫자를 확인하는 정규 표현식
	  const combinedValue = value1 + value2;
	  return phoneNumberPattern.test(combinedValue);
	}


	// 전화번호 필드에 'input' 이벤트 리스너 추가
	elInputPhoneNum1.addEventListener('input', validateForm);
	elInputPhoneNum2.addEventListener('input', validateForm);
  
  
  function getSelectedEmail() {
	  const selectEmail = document.getElementById('select');
	  return selectEmail.options[selectEmail.selectedIndex].value;
	}

  function isEnglishAlphabetAndNumbers(text) {
    return /^[a-zA-Z0-9]+$/.test(text);
  }

  function isLengthInRange(text, minLength, maxLength) {
    return text.length >= minLength && text.length <= maxLength;
  }

  function isNoSpecialCharacters(text) {
    return /^[a-zA-Z0-9가-힣\s]*$/.test(text);
  }
  function isNoSpecialCharacters(input) {
	  const pattern = /[`!@#$%^&*()_\-+={[}\]|:;"'<,>.?/~"]/;
	  return !pattern.test(input);
	}
  function validateSsn1() {
	  let ssn1Value = elInputSsn1.value;
	  ssn1Value = ssn1Value.replace(/\D/g, ''); // 숫자 이외의 문자 제거
	  ssn1Value = ssn1Value.slice(0, 6); // 최대 6자리로 제한
	  elInputSsn1.value = ssn1Value;

	  if (ssn1Value.length === 6) {
	    elInputSsn1.classList.remove('invalid'); // 유효한 입력 스타일 제거
	    validateForm(); // 주민등록번호 입력 변경 시 유효성 검사 실행
	  } else {
	    elInputSsn1.classList.add('invalid'); // 유효하지 않은 입력 스타일 추가
	    disableJoinButton(); // 버튼 비활성화
	  }
	}

	function validateSsn2() {
	  let ssn2Value = elInputSsn2.value;
	  ssn2Value = ssn2Value.replace(/\D/g, ''); // 숫자 이외의 문자 제거
	  ssn2Value = ssn2Value.slice(0, 7); // 최대 7자리로 제한
	  elInputSsn2.value = ssn2Value;

	  if (ssn2Value.length === 7) {
	    elInputSsn2.classList.remove('invalid'); // 유효한 입력 스타일 제거
	    validateForm(); // 주민등록번호 입력 변경 시 유효성 검사 실행
	  } else {
	    elInputSsn2.classList.add('invalid'); // 유효하지 않은 입력 스타일 추가
	    disableJoinButton(); // 버튼 비활성화
	  }
	}


  
  //회원가입 버튼
  const elJoinButton = document.querySelector('#member-join-general-submit-btn2');

  // 중복 검사 결과 변수
  let isIdAvailable = false;
  let isNicknameAvailable = false;

  // 버튼 활성화 함수
  function enableJoinButton() {
    elJoinButton.disabled = false;
    elJoinButton.style.backgroundColor = '#FD5D5D'; // 원하는 색상으로 변경
  }

  // 버튼 비활성화 함수
  function disableJoinButton() {
    elJoinButton.disabled = true;
    elJoinButton.style.backgroundColor = ''; // 초기 상태로 되돌리기
  }
  
  

  function validateForm() {
	  const isValidUsername = isEnglishAlphabetAndNumbers(elInputUsername.value) && isLengthInRange(elInputUsername.value, 4, 16);
	  const isValidNickname = isNoSpecialCharacters(elInputNickname.value) && isLengthInRange(elInputNickname.value, 3, 10);
	  const isEmailValid = !specialCharactersPattern.test(elInputEmail.value) && isLengthValid(elInputEmail.value, 4); // 입력 길이 검사 추가
	  const isValidPassword = passwordPattern.test(elInputPassword.value);
	  const isMatchingPassword = elInputPassword.value === elInputPasswordCheck.value;
	  const isValidSsn1 = ssn1Pattern.test(elInputSsn1.value) && elInputSsn1.value.length === 6; // 주민등록번호 길이 검사 추가
	  const isValidSsn2 = ssn2Pattern.test(elInputSsn2.value) && elInputSsn2.value.length === 7; // 주민등록번호 길이 검사 추가
	  const isValidName = isNoSpecialCharacters(elInputName.value) && isLengthValid(elInputName.value, 3);
	  const isEmailSelected = getSelectedEmail() !== '';
	  const isValidPhoneNumber = isPhoneNumberValid(elInputPhoneNum1.value, elInputPhoneNum2.value);
	  const isPhoneNumberInputComplete = elInputPhoneNum1.value.length > 0 && elInputPhoneNum2.value.length > 0;

	  if (
			    isValidUsername &&
			    isValidNickname &&
			    isEmailValid &&
			    isValidPassword &&
			    isMatchingPassword &&
			    isValidSsn1 &&
			    isValidSsn2 &&
			    isIdAvailable &&
			    isNicknameAvailable &&
			    isValidName &&
			    isEmailSelected &&// 이메일 유효성 결과 추가
			    isValidPhoneNumber &&
			    isPhoneNumberInputComplete
	  ) {
	    enableJoinButton();
	  } else {
	    disableJoinButton();
	  }
	}

  

  // 폼 입력 변경 시 유효성 검사
  elInputUsername.addEventListener('input', validateForm);
  elInputNickname.addEventListener('input', validateForm);
  elInputEmail.addEventListener('input', validateForm);
  elInputPassword.addEventListener('input', validateForm);
  elInputPasswordCheck.addEventListener('input', validateForm);
  elInputSsn1.addEventListener('input', validateForm);
  elInputSsn2.addEventListener('input', validateForm);
  elInputName.addEventListener('input', validateForm); // 이름 입력 변경 시 유효성 검사 추가
  elInputPhoneNum1.addEventListener('input', validateForm);
  elInputPhoneNum2.addEventListener('input', validateForm);
  
  // 중복 ID 체크 버튼 클릭 시
  $("#btn-id-check").click(function() {
    var userId = $("#userId").val();
    $.ajax({
      url: "/yorijori/member/checkID?userId=" + userId,
      type: "GET",
      success: function(response) {
        if (response) {
          Swal.fire({
            icon: 'error',
            title: '중복된 ID입니다.',
          });
          isIdAvailable = false;
        } else {
          Swal.fire({
            icon: 'success',
            title: '사용 가능한 ID입니다.',
          });
          $("#btn-id-check").prop("disabled", true).css("background-color", "#4d4d4d");
          $("#userId").prop("readonly", true);
          isIdAvailable = true;
          validateForm(); // 유효성 검사 실행
        }
      },
      error: function() {
        const Toast = Swal.mixin({
          toast: true,
          position: 'center-center',
          showConfirmButton: false,
          timer: 3000,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        });

        Toast.fire({
          icon: 'error',
          title: '서버 오류'
        });
      }
    });
  });

  // 중복 닉네임 체크 버튼 클릭 시
  $("#btn-nickname-check").click(function() {
    var nickName = $("#nickName").val();
    $.ajax({
      url: "/yorijori/member/checkNickName?nickName=" + nickName,
      type: "GET",
      success: function(response) {
        if (response) {
          Swal.fire({
            icon: 'error',
            title: '중복된 닉네임 입니다.',
          });
          isNicknameAvailable = false;
        } else {
          Swal.fire({
            icon: 'success',
            title: '사용 가능한 닉네임입니다.',
          });
          $("#btn-nickname-check").prop("disabled", true).css("background-color", "#4d4d4d");
          $("#nickName").prop("readonly", true);
          isNicknameAvailable = true;
          validateForm(); // 유효성 검사 실행
        }
      },
      error: function() {
        const Toast = Swal.mixin({
          toast: true,
          position: 'center-center',
          showConfirmButton: false,
          timer: 3000,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        });

        Toast.fire({
          icon: 'error',
          title: '서버 오류'
        });
      }
    });
  });

  // 초기 상태에서 버튼 비활성화
  disableJoinButton();
});