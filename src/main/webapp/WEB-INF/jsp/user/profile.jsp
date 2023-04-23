<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container mb-5">
	<div class="d-flex">
		<div>
			<c:choose>
				<c:when test="${not empty user.profileImagePath}">
					<img alt="프로필 이미지 사진" src="${user.profileImagePath}" width="150" class="profile-image">
				</c:when>
				<c:otherwise>
					<img alt="프로필 이미지 사진" src="/static/img/profile.jpg" width="150" class="profile-image">
				</c:otherwise>
			</c:choose>
		</div>
		<div class="ml-5 d-flex align-items-center w-100">
			<div class="w-100">
				<div class="d-flex align-items-center">
					<h2>${user.loginId}</h2>
					<c:if test="${not empty userId}">
					<c:if test="${userId != user.id}">
						<!-- un-follow 상태 일 때 -->
						
						<button class="btn profile-follow-btn ml-3" data-follow-id="${user.id}">팔로우</button>
						<!-- follow 상태 일 때 -->
						<button class="btn profile-follow-btn ml-3 d-none" data-follow-id="${user.id}">언팔로우</button>
					</c:if>
					</c:if>
				</div>
				<c:if test="${userId == user.id}">
				<form action="/user/profile_edit_view" method="post">
					<input id="userId" name="userId" value="${userId}" class="d-none">
					<button id="profileFixBtn" type="submit" class="btn btn-secondary mt-3 btn-profile">프로필 편집</button>
				</form>
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
		// follow 버튼 클릭
		$(".profile-follow-btn").on("click", function(e){
			e.preventDefault(); // 위로 올라가는 현상 방지
			let followId = $(this).data("follow-id");
			
			$.ajax({
				url:"/follow/" + followId
				
				, success:function(data) {
					if(data.code == 1){
						location.reload();
					} else if(data.code == 300) {
						alert(data.errorMessage);
						
						location.href = "/user/sign_in_view"; // 로그인 화면으로 이동
					}
				}
				, error:function(request, status, error) {
					alert("팔로우에 실패했습니다.");
				}
			
			})
		});
	});
</script>