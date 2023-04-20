<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container mb-5">
	<div class="d-flex ml-5">
		<div>
			<c:choose>
				<c:when test="${not empty user.profileImagePath}">
					<img alt="프로필 이미지 사진" id="profileImage" src="${user.profileImagePath}" class="profile-edit-image" data-profile-image="${user.profileImagePath}">
				</c:when>
				<c:otherwise>
					<img alt="프로필 이미지 사진" id="profileImageNone" src="/static/img/profile.jpg" class="profile-edit-image" data-profile-image="">
				</c:otherwise>
			</c:choose>
		</div>
		<div class="ml-5 d-flex align-items-center w-100">
			<div class="w-100 d-flex align-items-center">
				<button id="profileImageBtn" type="button" class="btn btn-profile-fix">프로필 이미지 변경</button>

				<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
				<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">

				<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
				<div id="fileName" class="ml-2 d-flex align-items-center font-weight-bold"></div>
			</div>
		</div>
	</div>

	<div class="mt-5 ml-5">
		<div class="mb-3 ml-5">
			<h4 class="font-weight-bold">이름</h4>
			<input type="text" id="name" class="form-control profile-fix-input"
				value="${user.name}">
		</div>
		<div class="mb-3 ml-5">
			<h4 class="font-weight-bold">아이디</h4>
			<div class="d-flex align-items-center">
				<input type="text" id="loginId"
					class="form-control profile-fix-input" value="${user.loginId}"
					data-user-loginid="${user.loginId}" data-user-id="${user.id}">
				<button id="loginIdCheckBtn" class="btn btn-profile-fix ml-2">중복확인</button>
			</div>
			<%-- 아이디 체크 결과 --%>
			<%-- d-none 클래스: display none (보이지 않게) --%>
			<div id="idCheckLength" class="small text-danger d-none">ID를 4자
				이상 입력해주세요.</div>
			<div id="idCheckDuplicated" class="small text-danger d-none">이미
				사용중인 ID입니다.</div>
			<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID
				입니다.</div>
		</div>
		<div class="mb-3 ml-5">
			<h4 class="font-weight-bold">이메일</h4>
			<input type="text" id="email" class="form-control profile-fix-input"
				value="${user.email}">
		</div>
	</div>

	<div class="d-flex w-100">
		<button id="profileEditBtn" class="btn btn-fix ml-5 mt-5">수정완료</button>
	</div>
</div>

<script>
$(document).ready(function(){
	// 프로필 이미지 변경 버튼 클릭 => 숨겨져 있는 file 태그를 동작
	$("#profileImageBtn").on("click", function(){
		$("#file").click();
	});
	
	// 사용자가 이미지를 선택했을 때 유효성 확인 및 업로드 된 파일명 노출
	$("#file").on("change", function(e){
		let fileName = e.target.files[0].name;
		
		// 확장자 유효성 확인
		let ext = fileName.split(".").pop().toLowerCase();
		if(ext != "jpg" && ext != "png" && ext != "jpeg" && ext != "gif") {
			alert("이미지 파일만 업로드 할 수 있습니다.");
			// 파일 비우기
			$(this).val(""); // 파일 태그에 파일 제거
			$("#fileName").text(""); // 파일 이름 비우기
			return;
		}
		
		// 유효성 통과한 이미지는 상자에 업로드 된 파일 이름 노출
		$("#fileName").text(fileName);
	});
	
	//아이디 중복확인 버튼
	$("#loginIdCheckBtn").on('click', function() {
		$("#idCheckLength").addClass('d-none');
		$("#idCheckDuplicated").addClass('d-none');
		$("#idCheckOk").addClass('d-none');

		let loginId = $("#loginId").val().trim();
		let currentLoginId = $("#loginId").data("user-loginid");
					
		if (loginId.length < 4) {
			$("#idCheckLength").removeClass('d-none');
			return;
		}
		
		if(loginId == currentLoginId){
			$("#idCheckOk").removeClass('d-none');
			return;
		}

			$.ajax({
			type : "get",
			url : "/user/is_duplicated_id",
			data : {
				"loginId" : loginId
			}

			,
			success : function(data) {
				if (data.code == 1) {
					if (data.result) {
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
	
 	$("#profileEditBtn").on("click", function(){
		let name = $("#name").val().trim();
		let loginId = $("#loginId").val().trim();
		let email = $("#email").val().trim();
		let userId = $("#loginId").data("user-id");
		
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
		
		//AJAX
		let formData = new FormData();
		formData.append("userId", userId);
		formData.append("name", name);
		formData.append("loginId", loginId);
		formData.append("email", email);
		formData.append("file", $('#file')[0].files[0]);
		
		$.ajax({
			type:"PUT"
			, url:"/user/profile_edit"
			, data:formData
			, enctype:"multipart/form-data"
			, processData:false
			, contentType:false
			
			, success:function(data){
				if(data.code == 1){
					alert("프로필이 수정되었습니다.");
					location.href = "/user/profile_view?loginId=" + loginId;
				} else {
					alert(data.errorMessage);
				}
			}
			, error:function(request, status, error) {
				alert("프로필을 수정하는데 실패했습니다.");
			}
		});
		
	});
});

</script>