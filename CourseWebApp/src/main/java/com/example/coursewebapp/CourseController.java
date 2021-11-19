package com.example.coursewebapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {

	/* 1)
	@RequestMapping("/courses")
	public String courses(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String cname = req.getParameter("cname");
		session.setAttribute("cname", cname);
		return "courses";
	}
	
	 2)
	@RequestMapping("/courses")
	public String courses(String cname, HttpSession session) {
		session.setAttribute("cname", cname);
		return "courses";
	}
	
	3)
	@RequestMapping("/courses")
	public String courses(@RequestParam("cname")String courseName, HttpSession session) {
		session.setAttribute("cname", courseName);
		return "courses";
	}
	*/
	@RequestMapping("/courses")
	public ModelAndView courses(String cname) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("cname", cname);
		mv.setViewName("courses");
		return mv;
	}
}
