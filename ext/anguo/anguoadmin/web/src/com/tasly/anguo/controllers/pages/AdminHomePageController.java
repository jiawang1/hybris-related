package com.tasly.anguo.controllers.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class AdminHomePageController extends AbstractController {
	
	protected static final Logger LOG = Logger.getLogger(AdminHomePageController.class);
	
	
	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String showAdminHome(){
		System.out.println("come to home controller");
		return "index.jsp";
	}
	

}
