<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center p-2">
	<div class="sign-up-box">
		<h1 class="mt-3 mb-3">회원가입</h1>
		<form id="signUpForm" method="post" action="/user/sign_up">
			<div class="p-2">
				<div class="p-1">*아이디(4자 이상)</div>
				<div class="d-flex align-items-center">
					<input type="text" id="loginId" name="loginId" class="form-control col-9" placeholder="아이디를 입력하세요.">
					<button type="button" id="loginIdCheckBtn" class="btn btn-user-check ml-1">중복확인</button><br>
				</div>
				<%-- 아이디 체크 결과 --%>
				<%-- d-none 클래스: display none (보이지 않게) --%>
				<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
				<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
				<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
			</div>
			<div class="p-2 ">
				<div class="p-1">*비밀번호</div>
				<input type="password" id="password" name="password" class="form-control" placeholder="비밀번호를 입력하세요.">	
			</div>
			<div class="p-2">
				<div class="p-1">*비밀번호 확인</div>
				<input type="password" id="confirmPassword" class="form-control" placeholder="비밀번호를 입력하세요.">			</div>
			<div class="p-2">
				<div class="p-1">*이름</div>
				<input type="text" id="name" name="name" class="form-control" placeholder="이름을 입력하세요.">
			</div>
			<div class="p-2">
				<div class="p-1">*이메일</div>
				<input type="text" id="email" name="email" class="form-control" placeholder="이메일 주소를 입력하세요.">
			</div>
			<button type="submit" id="signUpBtn" class="btn btn-user float-right mt-3">회원가입</button>
		</form>
	</div>
</div>

<script>
$(document).ready(function(){
	$("#loginIdCheckBtn").on('click', function(){
		$("#idCheckLength").addClass('d-none');
		$("#idCheckDuplicated").addClass('d-none');
		$("#idCheckOk").addClass('d-none');
		
		let loginId = $("#loginId").val().trim();
		
		if(loginId.length < 4){
			$("#idCheckLength").removeClass('d-none');
			return;
		}
		
		$.ajax({
			type:"get"
			, url:"/user/is_duplicated_id"
			, data:{"loginId":loginId}
		
			, success:function(data){
				if(data.code == 1){
					if(data.result){
						// 중복
						$("#idCheckDuplicated").removeClass('d-none');
					} else {
						// 사용 가능
						$("#idCheckOk").removeClass('d-none');					
					}
				} else {
					alert("아이디 중복확인에 실패했습니다. 관리자에게 문의하세요.");
				}
				
			}
		});
	});
	
	$("#signUpForm").on('submit', function(e){
		e.preventDefault(); // 서브밋 기능 중단
		
		let loginId = $("#loginId").val().trim();
		let password = $("#password").val();
		let confirmPassword = $("#confirmPassword").val();
		let name = $("#name").val().trim();
		let email = $("#email").val().trim();
		
		if(!loginId){
			alert("아이디를 입력해주세요");
			return false;
		}
		if(!password || !confirmPassword){
			alert("비밀번호를 입력해주세요");
			return false;
		}
		if(password != confirmPassword){
			alert("비밀번호가 일치하지 않습니다");
		}
		if(!name) {
			alert("이름을 입력해주세요");
			return false;
		}
		if(!email){
			alert("이메일을 입력해주세요");
			return false;
		}
		if($("#idCheckOk").hasClass('d-none')){
			alert("아이디 중복확인을 해주세요");
			return false;
		}
		
		let url = $(this).attr('action');
		let params = $(this).serialize(); // name 속성 값들 가져오기
		
		$.post(url, params)
		.done(function(data){
			if(data.code == 1){
				alert("회원가입에 성공했습니다.");
				location.href = "/user/sign_in_view";
			} else {
				alert(data.errorMessage);
			}
		});
	});
});
</script>