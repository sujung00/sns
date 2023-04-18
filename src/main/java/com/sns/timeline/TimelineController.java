package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	
	@Autowired
	private PostBO postBO;

	/**
	 * 타임라인 화면
	 * @param model
	 * @return
	 */
	// http://localhost:8080/timeline/timeline_view
	@GetMapping("/timeline_view")
	public String timelineView(Model model) {
		
		//posts
		List<Post> postList = postBO.getPostList();
		
		model.addAttribute("view", "timeline/timeline");
		model.addAttribute("postList", postList);
		
		return "template/layout";
	}
}
