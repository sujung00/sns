package com.sns.post.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostMapper;
import com.sns.post.model.Post;

@Service
public class PostBO {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private PostMapper postMapper;

	public int addPost(int userId, String loginId, String content, MultipartFile file) {
		
		String imagePath = null;
		if(file != null) {
			// 서버에 이미지 업로드 후 imagPath 받아옴
			imagePath = fileManager.saveFile(loginId, file);
		}
		
		return postMapper.insertPost(userId, content, imagePath);
	}
	
	public List<Post> getPostList(){
		return postMapper.selectPostList();
	}
	
	public List<Post> getPostListByUserId(int userId) {
		return postMapper.selectPostListByUserId(userId);
	}
	
	public int getPostCountByUserId(int userId) {
		return postMapper.selectPostCountByUserId(userId);
	}
	
	public Post getPostByPostId(int postId) {
		return postMapper.selectPostByPostId(postId);
	}
	
	public int deletePostByPostIdUserId(int postId, int userId) {
		
		// 좋아요 삭제
		likeBO.deleteLikeByPostId(postId);
		
		// 댓글 삭제
		commentBO.deleteCommentByPostId(postId);
		
		// 사진 삭제
		Post post = getPostByPostId(postId);
		if (post == null) {
			logger.error("[delete post] post is null. postId:{}, userId:{}", postId, userId);
			return 0;
		}
		if(post.getImagePath() != null) {
			fileManager.deleteFile(post.getImagePath());
		}
		
		return postMapper.deletePostByPostId(postId);
	}
	
}
