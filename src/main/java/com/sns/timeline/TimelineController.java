package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.comment.bo.CommentBO;
import com.sns.post.bo.PostBO;
import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.CardView;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	
	@Autowired
	private TimelineBO timelineBO;

	/**
	 * 타임라인 화면
	 * @param model
	 * @return
	 */
	// http://localhost:8080/timeline/timeline_view
	@GetMapping("/timeline_view")
	public String timelineView(Model model, HttpSession session) {

		Integer userId = (Integer)session.getAttribute("userId");
		
		// card => view 용으로 가공
		List<CardView> cardList = timelineBO.generateCardList(userId);
		
		model.addAttribute("cardList", cardList);
		
		model.addAttribute("view", "timeline/timeline");
		
		return "template/layout";
	}
}
