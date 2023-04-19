<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container mb-5">
	<div class="d-flex">
		<div>
			<c:choose>
				<c:when test="${not empty user.profileImagePath}">
					<img alt="프로필 이미지 사진" src="${user.profileImagePath}" width="150">
				</c:when>
				<c:otherwise>
					<img alt="프로필 이미지 사진" src="/static/img/profile.jpg" width="150">
				</c:otherwise>
			</c:choose>
		</div>
		<div class="ml-5 d-flex align-items-center w-100">
			<div class="w-100">
				<h2>${user.loginId}</h2>
				<c:if test="${userId == user.id}">
				<button id="profileFixBtn" type="button" class="btn btn-secondary mt-3 btn-profile" data-user-id="${userId}">프로필 편집</button>
				</c:if>
			</div>
		</div>
	</div>

	<div class="mt-3">
		<h3 class="font-weight-bold">${user.name}</h3>
	</div>

	<div
		class="profile-table d-flex justify-content-around text-center mt-3">
		<div>
			<div>
				<b>게시물</b>
			</div>
			<div>3</div>
		</div>
		<div>
			<div>
				<b>팔로워</b>
			</div>
			<div>1200</div>
		</div>
		<div>
			<div>
				<b>팔로우</b>
			</div>
			<div>1000</div>
		</div>
	</div>

	<div class="d-flex justify-content-between w-100">
		
		<!-- post img -->
		<c:forEach items="${postList}" var="post">
		<div class="profile-post-image d-flex align-items-center">
			<img alt="post이미지" src="${post.imagePath}" class="w-100">
		</div>
		</c:forEach>
	</div>
</div>

<script>
$(document).ready(function(){
	$("#profileFixBtn").on("click", function(){
		let userId = $(this).data("user-id");
		
		/* .ajax({
			type:"POST"
			, url:"timeline/timeline_fix"
			, data{"userId":userId}
		
			, success:function(data){
				if(data.code == 1){
					
				}
			}
		}) */
	});
});
</script>