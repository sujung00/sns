<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="border mb-5 card">
		<div class="d-flex align-items-center justify-content-between">
			<div class="m-2">
				<a href="/profile/profile_view/${card.user.loginId}"> <span
					class="card-login-id">${card.user.loginId}</span>
				</a>
			</div>
			<c:if test="${userId eq card.user.id}">
				<a href="#" class="more-btn" data-toggle="modal"
					data-target="#modal" data-post-id="${card.post.id}"
					data-login-id="${card.user.loginId}"> <img alt="더보기"
					src="/static/img/more-icon.png" width="30" class="mr-2">
				</a>
			</c:if>
		</div>
		<div>
			<div class="w-100">
				<img alt="post 이미지" src="${card.post.imagePath}" class="w-100">
			</div>

			<!-- 좋아요 -->
			<div class="card-like ml-2 mt-2 d-flex align-items-center">
				<!-- 채워진 하트 -->
				<c:if test="${card.filledLike eq true}">
					<a href="#" class="like-btn mr-2 d-flex align-items-center"
						data-post-id="${card.post.id}"> <img
						src="/static/img/full-heart-icon.png" width="18px" height="18px"
						alt="filled heart">
					</a>
				</c:if>
				<!-- 빈 하트 -->
				<c:if test="${card.filledLike eq false}">
					<a href="#" class="like-btn mr-2 d-flex align-items-center"
						data-post-id="${card.post.id}"> <img
						src="/static/img/heart-icon.png" width="18px" height="18px"
						alt="filled heart">
					</a>
				</c:if>
				<span>좋아요 ${card.likeCount}개</span>
			</div>

			<div class="ml-2 mt-1 mb-3 d-flex">
				<div>
					<b>${card.user.loginId}</b>
				</div>
				<div class="ml-2">${card.post.content}</div>
			</div>
			<span class="ml-2"><b>댓글</b></span>
		</div>

		<!-- 댓글 -->
		<c:forEach items="${card.commentList}" var="comment">
			<div class="border">
				<div class="d-flex align-items-center p-2">
					<span><b>${comment.user.loginId}</b> :
						${comment.comment.content}</span>
					<!-- 댓글 삭제 버튼 -->
					<c:if test="${userId == comment.comment.userId}">
						<a href="#" class="commentDelBtn d-flex align-items-center"
							data-comment-id="${comment.comment.id}"> <img alt="댓글삭제"
							src="/static/img/x-icon.png" width="10" class="ml-2">
						</a>
					</c:if>
				</div>
			</div>
		</c:forEach>

		<!-- 댓글 쓰기 -->
		<c:if test="${not empty userId}">
			<div class="border d-flex">
				<input type="text" id="comment" name="comment" placeholder="댓글 달기"
					class="comment col-10">
				<button type="button" class="btn btn-light col-2 comment-btn"
					data-post-id="${card.post.id}">게시</button>
			</div>
		</c:if>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="modal">
		<%-- modal-dialog-centered: 모달 창을 수직 가운데 정렬 --%>
		<%-- modal-sm: samll 모달 --%>
		<div class="modal-dialog modal-dialog-centered modal-sm">
			<div class="modal-content text-center">
				<div class="py-3 border-bottom">
					<a href="#" id="deletePostBtn">삭제하기</a>
				</div>
				<div class="py-3">
					<%-- data-dismiss="modal" => 모달창 닫힘 --%>
					<a href="#" data-dismiss="modal">취소하기</a>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		// 댓글 게시 버튼 클릭
		$(".comment-btn").on("click", function() {
			let postId = $(this).data("post-id");
			// 댓글 내용 가져오기
			// 1)
			//let content = $(this).prev().val();

			// 2)
			let content = $(this).siblings('input').val();

			if (!content) {
				alert("댓글을 입력해주세요");
				return;
			}

			$.ajax({
				type : "POST",
				url : "/comment/create",
				data : {
					"postId" : postId,
					"content" : content
				}

				,
				success : function(data) {
					if (data.code == 1) {
						location.reload(true);
					} else {
						alert(data.errorMessage);
					}
				},
				error : function(request, status, error) {
					alert("댓글 게시에 실패했습니다.");
				}
			})
		});

		// 좋아요 버튼 클릭
		$(".like-btn").on("click", function(e) {
			e.preventDefault(); // 위로 올라가는 현상 방지
			let postId = $(this).data("post-id");

			$.ajax({
				url : "/like/" + postId

				,
				success : function(data) {
					if (data.code == 1) {
						location.reload();
					} else if (data.code == 300) {
						alert(data.errorMessage);

						location.href = "/user/sign_in_view";
					}
				},
				error : function(request, status, error) {
					alert("좋아요에 실패했습니다.");
				}

			})
		});

		// 댓글 삭제 버튼
		$(".commentDelBtn").on("click", function(e) {
			e.preventDefault(); // 위로 올라가는 현상 방지
			let commentId = $(this).data("comment-id");

			$.ajax({
				type : "POST",
				url : "/comment/delete",
				data : {
					"commentId" : commentId
				}

				,
				success : function(data) {
					if (data.code == 1) {
						location.reload();
					} else {
						alert(data.errorMessage);
					}
				},
				error : function(request, status, error) {
					alert("댓글 삭제에 실패했습니다.");
				}
			})
		});

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

	});

	// 더보기 버튼(... 버튼) 클릭 => 글 삭제 위해
	$(".more-btn").on("click", function(e) {
		e.preventDefault(); // 위로 올라가는 현상 방지

		let postId = $(this).data("post-id"); // getting
		let loginId = $(this).data("login-id");

		// (재횔용 되는)모달 태그에 data-post-id를 심어줌
		$("#modal").data("post-id", postId);
		$("#modal").data("login-id", loginId);
	});

	// 모달 안에 있는 삭제하기 버튼 클릭 => 진짜 삭제
	$("#modal #deletePostBtn").on("click", function(e) {
		e.preventDefault(); // 위로 올라가는 현상 방지

		let postId = $("#modal").data("post-id");
		let loginId = $("#modal").data("login-id");

		$.ajax({
			type : "DELETE",
			url : "/post/delete",
			data : {
				"postId" : postId
			}

			,
			success : function(data) {
				if (data.code == 1) {
					alert("게시글이 삭제되었습니다");
					location.href = "/profile/profile_view/" + loginId
				} else {
					alert(data.errorMessage);
				}
			},
			error : function(request, status, error) {
				alert("게시글 삭제에 실패했습니다.");
			}
		})
	});
</script>