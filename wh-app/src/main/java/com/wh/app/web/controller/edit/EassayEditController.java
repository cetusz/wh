package com.wh.app.web.controller.edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.mybatis.support.Page;
import com.my.mybatis.support.Sort;
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
		 Sort sort = new Sort();
		 sort.setTableSort("pubDate",true);
		 eassayEditService.getPageList(pager, query, sort);
		 resultMap.put("total", pager.getTotal());
		 resultMap.put("rows", pager.getResult());
		 return resultMap;
	}
	
	@RequestMapping(value="del",produces={"application/json;charset=UTF-8"})
	public @ResponseBody Map<String,Object> del(String ids){
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			String[] array = ids.split(",");
			List<Long> idlist = new ArrayList<Long>();
			for(String id:array){
				idlist.add(Long.valueOf(id));
			}
			eassayEditService.deleteMulti(idlist);
			result.put("success", true);
		}catch(Exception ex){
			result.put("success", false);
		}
		return result;
	}
}
