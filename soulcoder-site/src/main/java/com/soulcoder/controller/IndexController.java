package com.soulcoder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * The type Index controller.
 */
@Controller
//@RequestMapping("sys")
public class IndexController extends AbstractController  {

	/**
	 * Handle request model and view.
	 *
	 * @param request  the request
	 * @param response the response
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping("/index")
	    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//	        ModelAndView mav = new ModelAndView("index");
//	        mav.addObject("message", "Hello Spring MVC");
//	        return mav;
		return "index";
	    }
	 
	   
}
