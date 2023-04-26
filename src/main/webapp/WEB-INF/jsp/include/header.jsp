<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="h-100 d-flex justify-content-between align-items-center p-3">
	<%-- logo --%>
	<div>
		<button type="button" class="header-btn btn dropdown-toggle" data-toggle="dropdown" aria-expanded="false">SOPHIAGRAM</button>
		<div class="dropdown-menu">
	    	<a class="dropdown-item" href="/timeline/timeline_view">전체보기</a>
	    	<c:if test="${not empty userId}">
	    		<a class="dropdown-item" href="/timeline/timeline_following_view">팔로잉</a>
    		</c:if>
    	</div>
	</div>

	<%-- 로그인 정보 --%>
	<div>
		<c:choose>
			<c:when test="${not empty userId}">
				<span class="mr-3"><b>${userName}</b>님 안녕하세요!</span>
				<a href="/profile/profile_view/${userLoginId}" class="font-weight-bold mr-3">내 프로필</a>
				<a href="/user/sign_out" class="font-weight-bold">로그아웃</a>
			</c:when>
			<c:otherwise>
				<a href="/user/sign_in_view" class="font-weight-bold">로그인 / 회원가입</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>