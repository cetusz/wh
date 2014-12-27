package com.wh.app.web.controller.pub;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@RequestMapping(value="finduser",method=RequestMethod.POST,produces ={"application/json;charset=UTF-8"})
	public @ResponseBody Map<String,Object> finduser(String userName,String password){
		UserEntity entity = userService.login(userName, password);
		Map<String,Object> map = new HashMap<String,Object>();
		if(entity!=null){
			map.put("success", true);
		}else{
			map.put("success",false);
		}
		return map;
			
	}
	
	@RequestMapping("/tologin")
	public ModelAndView tologin(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}

	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request){
		request.getSession().removeAttribute("currentUser");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
}
