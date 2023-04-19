<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="h-100 d-flex justify-content-between align-items-center p-3">
	<%-- logo --%>
	<div>
		<a href="/timeline/timeline_view">
			<h3 class="font-weight-bold">Marondalgram</h3>
		</a>
	</div>

	<%-- 로그인 정보 --%>
	<div>
		<c:choose>
			<c:when test="${not empty userId}">
				<span class="mr-3">${userName}님 안녕하세요!</span>
				<a href="/user/profile?userId=${userId}" class="font-weight-bold mr-3">내 프로필</a>
				<a href="/user/sign_out" class="font-weight-bold">로그아웃</a>
			</c:when>
			<c:otherwise>
				<a href="/user/sign_in_view" class="font-weight-bold">로그인</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>