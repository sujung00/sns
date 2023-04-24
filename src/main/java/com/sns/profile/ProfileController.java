package com.sns.profile;

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
import com.sns.profile.bo.ProfileBO;
import com.sns.profile.model.ProfileView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@RequestMapping("/profile")
@Controller
public class ProfileController {

	@Autowired
	private ProfileBO profileBO;
	
	@Autowired
	private UserBO userBO;
	
	/**
	 * 프로필 화면
	 * @param userId
	 * @param model
	 * @return
	 */
	// http://localhost:8080/profile/profile_view/sujung
	@GetMapping("/profile_view/{loginId}")
	public String profile(
			@PathVariable String loginId,
			Model model,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		// profileView
		ProfileView profileView = profileBO.generateProfileView(userId, loginId);
		
		model.addAttribute("view", "profile/profile");
		model.addAttribute("profile", profileView);
		
		return "template/layout";
	}
	
	/**
	 * 프로필 편집
	 * @param userId
	 * @param model
	 * @return
	 */
	// http://localhost:8080/profile/profile_edit_view
	@RequestMapping("/profile_edit_view")
	public String profileFix(
			HttpSession session,
			Model model) {
		
		int userId = (int)session.getAttribute("userId");
		User user = userBO.getUserByUserId(userId);
		
		model.addAttribute("view", "profile/profileEdit");
		model.addAttribute("user", user);
		
		return "template/layout";
	}
}
