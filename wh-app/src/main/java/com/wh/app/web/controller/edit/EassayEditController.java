package com.wh.app.web.controller.edit;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.mybatis.support.Page;
import com.wh.app.web.extractor.SogouWeixinExtractor;
import com.wh.app.web.model.edit.EassayEdit;
import com.wh.app.web.model.query.EassayEditQuery;
import com.wh.app.web.service.edit.EassayEditService;

@Controller
@RequestMapping("/admin/eassay")
public class EassayEditController {

	@Autowired SogouWeixinExtractor sogouWeixinExtractor;
	@Autowired EassayEditService eassayEditService;
	@RequestMapping(value="crawler")
	public void crawler(){
		sogouWeixinExtractor.execute();
	}
	
	@RequestMapping(value="tolist")
	public ModelAndView tolist(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("eassay/list");
		return mv;
	}
	
	@RequestMapping(value="list",produces ={"application/json;charset=UTF-8"})
	public @ResponseBody Map<String,Object> list(EassayEditQuery query,@RequestParam("page")int page,@RequestParam("rows")int rows){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		 Page<EassayEdit> pager = new Page<EassayEdit>(rows,page);
		 eassayEditService.getPageList(pager, query, null);
		 resultMap.put("total", pager.getTotal());
		 resultMap.put("rows", pager.getResult());
		 return resultMap;
	}
}
