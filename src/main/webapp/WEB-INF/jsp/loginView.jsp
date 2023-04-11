<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<!-- AJAX를 사용하려면 jquery 원본 필요 -->
<script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h2>로그인</h2>
		<form method="post" action="/user/sign_in">
			<div class="input-group mb-2 col-3">
			  <span class="input-group-text" id="loginId">아이디</span>
			  <input type="text" class="form-control" placeholder="Username" id="loginId" name="loginId">
			</div>
			<div class="input-group mb-2 col-3">
			  <span class="input-group-text" id="password">비밀번호</span>
			  <input type="password" class="form-control" placeholder="****" id="password" name="password">
			</div>
			<div class="col-3 d-flex justify-content-around">
				<button type="button" class="btn btn-primary mr-2">로그인</button>
				<button type="button" class="btn btn-secondary">회원가입</button>
			</div>
		</form>
	</div>
</body>
</html>