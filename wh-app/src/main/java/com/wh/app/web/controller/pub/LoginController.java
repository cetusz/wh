package com.wh.app.web.controller.pub;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wh.app.web.model.pub.UserEntity;
import com.wh.app.web.service.pub.UserService;

@Controller
@RequestMapping("/system")
public class LoginController {
	@Autowired UserService userService;
	
	@RequestMapping("/login")
	public ModelAndView login(String userName,String password,HttpServletRequest request){
		UserEntity entity = userService.login(userName, password);
		request.getSession().setAttribute("currentUser", entity);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/tologin")
	public ModelAndView tologin(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}

}
