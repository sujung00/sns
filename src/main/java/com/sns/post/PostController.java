package com.sns.post;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.CardView;

@RequestMapping("/post")
@Controller
public class PostController {
	
	@Autowired
	private TimelineBO timelineBO;

	// http://localhost:8080/post/card_detail
	@RequestMapping("/card_detail/{postId}")
	public String cardDetail(@PathVariable int postId, Model model, HttpSession session) {

		Integer userId = (Integer) session.getAttribute("userId");
		
		CardView card = timelineBO.generateCard(postId, userId);
		
		model.addAttribute("card", card);
		model.addAttribute("view", "post/cardDetail");

		return "template/layout";
	}
}
