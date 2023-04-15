<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="container">
	<!-- 글쓰기 영역 -->
	<br>
	<div class="border">
		<div class="d-flex justify-content-center">
			<textarea rows="5" cols="70" placeholder="내용을 입력해주세요"></textarea>
		</div>
		<div class="d-flex justify-content-between">
			<button type="button" class="btn btn-secondary">사진</button>
			<button type="button" class="btn btn-info">게시</button>
		</div>
	</div>
	
	<div class="mt-4">
		<!-- card -->
		<div class="border">
			<div class="d-flex align-items-center justify-content-between">
				<span class="m-2"><b>marobiana</b></span>
				<div><img alt="더보기" src="/static/img/more-icon.png" width="30" class="mr-2"></div>
			</div>
			<div>
				<div class="w-100">
					<img alt="post 이미지" src="/static/img/cat.jpg" class="w-100">
				</div>
				<div class="d-flex align-items-center mt-2 ml-2">
					<img alt="하트" src='/static/img/heart-icon.png' width="20" height="20">
					<div class="ml-1">좋아요 11개</div>
				</div>
				<div class="ml-2 d-flex">
					<div><b>marobiana</b></div>
					<div class="ml-1">비지도 학습을 해본 결과 입니다. cluster 알고리즘을 사용해봤어요</div>
				</div>
				<span>댓글</span>
			</div>
			<div class="border">
				<div class="d-flex align-items-center">
					<span><b>hagulu</b> : 분류가 잘 되었군요~</span>
					<img alt="댓글삭제" src="/static/img/x-icon.png" width="10" class="ml-1">
				</div>
			</div>
			<div class="border d-flex">
				<input type="text" id="comment" name="comment"  placeholder="댓글 달기" class="comment col-10">
				<button type="button" class="btn btn-light col-2">게시</button>
			</div>
		</div>
	</div>
</div>