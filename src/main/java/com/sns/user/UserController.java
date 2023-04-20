package com.sns.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	private UserBO userBO;
	
	@Autowired
	private PostBO postBO;
	
	/**
	 * 회원가입 화면
	 * @param model
	 * @return
	 */
	// http://localhost:8080/user/sign_up_view
	@GetMapping("/sign_up_view")
	public String signUpView(Model model) {
		model.addAttribute("view", "user/signUp");
		
		return "template/layout";
	}
	
	/**
	 * 로그인
	 * @param model
	 * @return
	 */
	// http://localhost:8080/user/sign_in_view
	@GetMapping("/sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("view", "user/signIn");
		
		return "template/layout";
	}
	
	/**
	 * 로그아웃
	 * @param session
	 * @return
	 */
	@RequestMapping("/sign_out")
	public String signOut(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		session.removeAttribute("userLoginId");
		
		return "redirect:/timeline/timeline_view";
	}
	
	/**
	 * 프로필 화면
	 * @param userId
	 * @param model
	 * @return
	 */
	// http://localhost:8080/user/profile_view?loginId=sujung
	// http://localhost:8080/user/profile?loginId=sujung
	@GetMapping("/profile_view")
	public String profile(
			@RequestParam("loginId") String loginId,
			Model model) {
		
		User user = userBO.getUserByLoginId(loginId);
		List<Post> postList = postBO.getPostListByUserId(user.getId());
		
		model.addAttribute("view", "user/profile");
		model.addAttribute("user", user);
		model.addAttribute("postList", postList);
		
		return "template/layout";
	}
	
	/**
	 * 프로필 편집
	 * @param userId
	 * @param model
	 * @return
	 */
	// http://localhost:8080/user/profile_edit_view?userId=1
	@PostMapping("/profile_edit_view")
	public String profileFix(
			@RequestParam("userId") int userId,
			Model model) {
		
		User user = userBO.getUserByUserId(userId);
		List<Post> postList = postBO.getPostListByUserId(userId);
		
		model.addAttribute("view", "user/profileEdit");
		model.addAttribute("user", user);
		model.addAttribute("postList", postList);
		
		return "template/layout";
	}
}
