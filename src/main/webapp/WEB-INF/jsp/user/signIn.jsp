<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container d-flex justify-content-center">

	<!-- Nested Row within Card Body -->
	<div class="p-5 col-6">
		<div class="text-center">
			<h1 class="h4 text-gray-900 mb-4">로그인</h1>
		</div>
		<form id="loginForm" method="post" action="/user/sign_in">
			<!-- ************************************* -->
			<div class="input-group">
				<i class='bx bxs-user'></i> <input type="text" id="loginId"
					name="loginId" placeholder="Username">
			</div>
			<div class="input-group">
				<i class='bx bxs-lock-alt'></i> <input type="password" id="password"
					name="password" placeholder="Password">
			</div>
			<!-- ************************************* -->

			<button type="submit" class="btn btn-user btn-block">로그인</button>
			<a href="/user/sign_up_view"
				class="btn btn-secondary btn-user btn-block">회원가입</a>
		</form>
		<button type="button" id="googleLoginBtn" class="btn btn-user btn-block mt-2">google로 로그인</button>
	</div>
</div>


<script>
function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
}

	$(document).ready(function() {
		$("#loginForm").on("submit", function(e) {
			e.preventDefault();

			let loginId = $("#loginId").val().trim();
			let password = $("#password").val().trim();

			if (!loginId) {
				alert("아이디를 입력해주세요");
				return false;
			}
			if (!password) {
				alert("비밀번호를 입력해주세요");
				return false;
			}

			let url = $(this).attr("action");
			let params = $(this).serialize();

			$.post(url, params).done(function(data) {
				if (data.code == 1) {
					location.href = "/timeline/timeline_view";
				} else {
					alert(data.errorMessage);
				}
			})
		})

	});
</script>