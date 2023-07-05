	$(document).ready(function() {
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
		                } else {
		                    Swal.fire({
		                        icon: 'success',
		                        title: '사용 가능한 ID입니다.',
		                    });
		                    $("#btn-id-check").prop("disabled", true).css("background-color", "#4d4d4d");
		                    $("#userId").prop("readonly", true);
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
		            	    })
	
		            	    Toast.fire({
		            	      icon: 'error',
		            	      title: '서버 오류'
		            	    })
		            }
		        });
		        
		    });
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
		                } else {
		                	Swal.fire({
		                        icon: 'success',
		                        title: '사용 가능한 닉네임입니다.',
		                    });
		                    $("#btn-nickname-check").prop("disabled", true).css("background-color", "#4d4d4d");
		                    $("#nickName").prop("readonly", true);
		                    // 'btn-id-check' 버튼의 색상을 변경하고, 버튼 비활성화
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
		            	    })
	
		            	    Toast.fire({
		            	      icon: 'error',
		            	      title: '서버 오류'
		            	    })
		            }
		        });
		        
		    });
		});

