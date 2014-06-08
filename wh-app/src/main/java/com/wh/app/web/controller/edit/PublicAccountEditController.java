package com.wh.app.web.controller.edit;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/publicaccoutedit")
public class PublicAccountEditController {

	@RequestMapping(value="tolist")
	public ModelAndView tolist(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("account/list");
		return mv;
	}
	
	
	@RequestMapping(value="list",method=RequestMethod.POST,produces ={"application/json;charset=UTF-8"})
	public @ResponseBody Map<String,Object> list(){
		 Map<String,Object> result = new HashMap<String,Object>();
		 return result;
	}
}
