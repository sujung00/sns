<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container timeline">

	<form class="input-group mb-3" action="/timeline/search_result" method="post">
		<input type="text" class="form-control" id="search" name="search" placeholder="User Search">
		<div class="input-group-append">
			<button class="btn search-btn" type="submit" id="searchBtn">SEARCH</button>
		</div>
	</form>

	<!-- 글쓰기 영역 -->
	<c:if test="${not empty userId}">
		<br>
		<div class="border">
			<div class="d-flex justify-content-center">
				<textarea rows="5" placeholder="내용을 입력해주세요" class="w-100"
					id="content" name="content"></textarea>
			</div>
			<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
					<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
					<input type="file" id="file" class="d-none"
						accept=".gif, .jpg, .png, .jpeg">
					<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
					<a href="#" id="fileUploadBtn" class="ml-2"> <img width="35"
						src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png">
					</a>

					<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
					<div id="fileName" class="ml-2 d-flex align-items-center"></div>
				</div>
				<button id="writeBtn" class="btn btn-upload">게시</button>
			</div>
		</div>
	</c:if>

	<div class="pt-4">

		<!-- card -->
		<c:forEach items="${cardList}" var="card">
			<div class="border mb-5 card">
				<div class="d-flex align-items-center justify-content-between">
					<div class="m-2">
						<a href="/profile/profile_view/${card.user.loginId}"><span
							class="card-login-id">${card.user.loginId}</span></a>
						<c:if test="${not empty userId}">
							<c:if test="${userId != card.post.userId}">
								<c:if test="${card.followed eq true}">
									<button type="button" class="follow-btn"
										data-follow-id="${card.post.userId}">언팔로우</button>
								</c:if>
								<c:if test="${card.followed eq false}">
									<button type="button" class="follow-btn"
										data-follow-id="${card.post.userId}">팔로우</button>
								</c:if>
							</c:if>
						</c:if>
					</div>
					<c:if test="${userId eq card.user.id}">
						<a href="#" class="more-btn" data-toggle="modal" data-target="#modal" data-post-id="${card.post.id}"> <img
							alt="더보기" src="/static/img/more-icon.png" width="30" class="mr-2">
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
		</c:forEach>
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
		// 파일 업로드 이미지 클릭 = > 숨겨져있는 file 태그를 동작시킨다.
		$("#fileUploadBtn").on('click', function(e) {
			e.preventDefault(); // a 태그의 스크롤이 올라가는 현상 방지

			// input file을 클릭한 것과 같은 효과
			$("#file").click();
		});

		// 사용자가 이미지를 선택했을 때 유효성 확인 및 업로드 된 파일명 노출
		$("#file").on('change', function(e) {
			let fileName = e.target.files[0].name; // ballon.jpg
			console.log(fileName);

			// 확장자 유효성 확인
			let ext = fileName.split(".").pop().toLowerCase();
			if (ext != "jpg" && ext != "png" && ext != "jpeg" && ext != "gif") {
				alert("이미지 파일만 업로드 할 수 있습니다.");
				// 파일 비우기
				$(this).val(""); // 파일 태그에 파일 제거
				$("#fileName").text(""); // 파일 이름 비우기
				return;
			}

			// 유효성 통과한 이미지는 상자에 업로드 된 파일 이름 노출
			$("#fileName").text(fileName);
		});

		// 글쓰기(게시) 버튼
		$("#writeBtn").on("click", function() {
			// validation
			// 글 내용, 이미지, 확장자 체크
			let content = $("#content").val();
			let file = $("#file").val();

			if (!file) {
				alert("이미지를 선택해주세요");
				return;
			}

			let ext = file.split(".").pop().toLowerCase();
			if ($.inArray(ext, [ 'jpg', 'jpeg', 'png', 'gif' ]) == -1) {
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$("#file").val("");
				return;
			}

			//AJAX
			let formData = new FormData();
			formData.append("content", content);
			formData.append("file", $('#file')[0].files[0]);

			$.ajax({
				type : "POST",
				url : "/post/create",
				data : formData,
				enctype : "multipart/form-data",
				processData : false,
				contentType : false

				,
				success : function(data) {
					if (data.code == 1) {
						alert("게시글이 업로드되었습니다.");
						location.href = "/timeline/timeline_view";
					} else {
						alert(data.errorMessage);
					}
				},
				error : function(request, status, error) {
					alert("글을 업로드하는데 실패했습니다.");
				}
			})
		});

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
		$(".commentDelBtn ").on("click", function(e) {
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
		
		// 더보기 버튼(... 버튼) 클릭 => 글 삭제 위해
		$(".more-btn").on("click", function(e) {
			e.preventDefault(); // 위로 올라가는 현상 방지

			let postId = $(this).data("post-id"); // getting

			// (재횔용 되는)모달 태그에 data-post-id를 심어줌
			$("#modal").data("post-id", postId);
		});

		// 모달 안에 있는 삭제하기 버튼 클릭 => 진짜 삭제
		$("#modal #deletePostBtn").on("click", function(e) {
			e.preventDefault(); // 위로 올라가는 현상 방지

			let postId = $("#modal").data("post-id");

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
						location.reload();
					} else {
						alert(data.errorMessage);
					}
				},
				error : function(request, status, error) {
					alert("게시글 삭제에 실패했습니다.");
				}
			})
		});
		
		$("#searchBtn").on("click", function(){
			let search = $("#search").val().trim();
			
			if(!search) {
				alert("검색어를 입력하세요");
				return;
			}
		});
		
	});
</script>