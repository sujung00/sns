<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<form class="input-group mb-3" action="/timeline/search_result"
		method="post">
		<input type="text" class="form-control" id="search" name="search"
			placeholder="User Search">
		<div class="input-group-append">
			<button class="btn search-btn" type="submit" id="searchBtn">SEARCH</button>
		</div>
	</form>

	<c:forEach items="${searchList}" var="search">
		<!-- user card -->
		<a href="/profile/profile_view/${search.user.loginId}">
			<div class="d-flex justify-content-center my-4">
				<div class="user-card d-flex">
					<div>
						<c:choose>
							<c:when test="${not empty search.user.profileImagePath}">
								<img alt="userProfileImage" src="${search.user.profileImagePath}"
									class="card-user-image">
							</c:when>
							<c:otherwise>
								<img alt="userProfileImage" src="/static/img/profile.jpg"
									class="card-user-image">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="d-flex align-items-center mx-4">
						<div>
							<div class="card-loginid font-weight-bold">${search.user.loginId}</div>
							<div class="card-name">${search.user.name}</div>
						</div>
					</div>
					<c:if test="${not empty userId}">
							<c:if test="${userId != search.user.id}">
								<c:if test="${search.followed eq true}">
									<button type="button" class="follow-btn"
										data-follow-id="${search.user.id}">언팔로우</button>
								</c:if>
								<c:if test="${search.followed eq false}">
									<button type="button" class="follow-btn"
										data-follow-id="${search.user.id}">팔로우</button>
								</c:if>
							</c:if>
						</c:if>
				</div>

			</div>
		</a>
	</c:forEach>
</div>

<script>
// follow 버튼 클릭
$(".follow-btn").on("click", function(e) {
	e.preventDefault(); // 위로 올라가는 현상 방지
	let followId = $(this).data("follow-id");

	$.ajax({
		url : "/follow/" + followId

		,
		success : function(data) {
			if (data.code == 1) {
				location.reload();
			} else if (data.code == 300) {
				alert(data.errorMessage);

				location.href = "/user/sign_in_view"; // 로그인 화면으로 이동
			}
		},
		error : function(request, status, error) {
			alert("팔로우에 실패했습니다.");
		}

	})
});
</script>