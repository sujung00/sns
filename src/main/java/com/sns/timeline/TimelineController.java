package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.CardView;
import com.sns.timeline.model.SearchView;
import com.sns.user.bo.UserBO;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	
	@Autowired
	private TimelineBO timelineBO;
	
	@Autowired
	private UserBO userBO;

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
	
	// http://localhost:8080/timeline/timeline_following_view
	@GetMapping("/timeline_following_view")
	public String timelineFollowingView(Model model, HttpSession session) {

		Integer userId = (Integer)session.getAttribute("userId");
		
		// card => view 용으로 가공
		List<CardView> cardList = timelineBO.generateCardList(userId);
		
		model.addAttribute("cardList", cardList);
		
		model.addAttribute("view", "timeline/timelineFollowing");
		
		return "template/layout";
	}
	
	// http://localhost:8080/timeline/search_result
	@RequestMapping("/search_result")
	public String searchResult(
			@RequestParam("search") String search,
			Model model, HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		// user list
		//List<User> userList = userBO.getUserListByLoginId(search);
		List<SearchView> searchList = timelineBO.generateSearchViewList(userId, search);
		
		model.addAttribute("searchList", searchList);
		model.addAttribute("view", "timeline/searchResult");
		
		return "template/layout";

	}

}
