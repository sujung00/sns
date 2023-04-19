<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container mb-5">
	<div class="d-flex ml-5">
		<div>
			<c:choose>
				<c:when test="${not empty user.profileImagePath}">
					<img alt="프로필 이미지 사진" src="${user.profileImagePath}" width="100">
				</c:when>
				<c:otherwise>
					<img alt="프로필 이미지 사진" src="/static/img/profile.jpg" width="100">
				</c:otherwise>
			</c:choose>
		</div>
		<div class="ml-5 d-flex align-items-center w-100">
			<div class="w-100">
				<button id="profileImageBtn" type="button"
					class="btn mt-3 btn-profile-fix">프로필 이미지 변경</button>
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
			<h4 class="font-weight-bold">사용자 이름</h4>
			<div class="d-flex align-items-center">
				<input type="text" id="loginId" class="form-control profile-fix-input"
					value="${user.loginId}" data-user-loginid="${user.loginId}">
				<button id="loginIdCheckBtn" class="btn btn-profile-fix ml-2">중복확인</button>
			</div>
			<%-- 아이디 체크 결과 --%>
			<%-- d-none 클래스: display none (보이지 않게) --%>
			<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
			<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
			<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
		</div>
		<div class="mb-3 ml-5">
			<h4 class="font-weight-bold">이메일</h4>
			<input type="text" id="email" class="form-control profile-fix-input"
				value="${user.email}">
		</div>
	</div>

	<div class="d-flex mx-5 w-100">
		<button class="btn btn-fix ml-5 mt-5">수정완료</button>
	</div>
</div>

<script>
	$(document).ready(function() {
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
			}

/* 			$.ajax({
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
			}); */
		});
	});
</script>