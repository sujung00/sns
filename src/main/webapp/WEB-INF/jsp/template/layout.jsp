<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SNS</title>
<!-- bootstrap -->
<script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script></head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<!-- 내가 만든 스타일시트 -->
<link rel="stylesheet" type="text/css" href="/static/css/style.css">
<meta name ="google-signin-client_id" content="500639666960-m54si8gbe16fimpq8rvir5tue5oatp3a.apps.googleusercontent.com">
<!-- 구글 api 사용을 위한 스크립트 -->
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
</head>
<body>
	<div id="wrap" class="bg-dark">
		<header>
			<%-- 상대 경로 --%>
			<jsp:include page="../include/header.jsp" />
		</header>
		<section class="contents pt-2">
			<jsp:include page="../${view}.jsp" />
		</section>
	</div>
</body>
</html>