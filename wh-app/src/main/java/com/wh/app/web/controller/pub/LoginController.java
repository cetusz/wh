package com.wh.app.web.controller.pub;

<<<<<<< HEAD
=======
import java.util.HashMap;
import java.util.Map;

>>>>>>> develop
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
>>>>>>> develop
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
	
<<<<<<< HEAD
=======
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
	
>>>>>>> develop
	@RequestMapping("/tologin")
	public ModelAndView tologin(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}

<<<<<<< HEAD
=======
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request){
		request.getSession().removeAttribute("currentUser");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
>>>>>>> develop
}
