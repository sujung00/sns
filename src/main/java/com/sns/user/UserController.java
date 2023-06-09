package com.sns.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.profile.model.ProfileView;
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
}
