package com.wh.app.web.controller.edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.mybatis.support.Page;
import com.my.mybatis.support.Sort;
import com.wh.app.web.model.edit.CategoryEdit;
import com.wh.app.web.model.query.CategoryEditQuery;
import com.wh.app.web.service.edit.CategoryEditService;

@Controller
@RequestMapping("/admin/category")
public class CategoryEditController {
	
	@Autowired CategoryEditService categoryEditService;
	
	@RequestMapping("toadd")
	public ModelAndView toadd(Long id){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("category/add");
		mv.addObject("id", id);
		return mv;
	}
	
	@RequestMapping(value="save",produces ={"application/json;charset=UTF-8"})
	public @ResponseBody Map<String,Object> save(CategoryEdit edit){
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			CategoryEditQuery query = new CategoryEditQuery();
			query.setCateName(edit.getCateName());
			List<CategoryEdit> list = categoryEditService.selectList(query, null);
			if(list.size()==0){
				categoryEditService.saveOrUpdate(edit);
				result.put("success", true);
			}else{
				result.put("success", false);
				result.put("message", "已经存在名称为:"+edit.getCateName()+"的分类");
			}
		}catch(Exception ex){
			result.put("success", false);
			result.put("message", "数据库操作异常");
		}
		return result;
	}
	
	
	@RequestMapping(value="get",produces={"application/json;charset=utf-8"})
	public @ResponseBody CategoryEdit get(Long id){
		return categoryEditService.findOne(id);
	}
	
	
	@RequestMapping("tolist")
	public ModelAndView tolist(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("category/list");
		return mv;
	}
	
	@RequestMapping(value="list",produces={"application/json;charset=utf-8"})
	public @ResponseBody Map<String,Object> list(CategoryEditQuery query,@RequestParam("page")int page,@RequestParam("rows")int rows){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		 Page<CategoryEdit> pager = new Page<CategoryEdit>(rows,page);
		 Sort sort = new Sort();
		 sort.setTableSort("orderNum",false);
		 categoryEditService.getPageList(pager, query, sort);
		 resultMap.put("total", pager.getTotal());
		 resultMap.put("rows", pager.getResult());
		 return resultMap;
	}
	
	@RequestMapping(value="check",produces={"application/json;charset=utf-8"})
	public @ResponseBody Map<String,Object> check(String cateName){
		CategoryEditQuery query = new CategoryEditQuery();
		query.setCateName(cateName);
		List<CategoryEdit> list = categoryEditService.selectList(query, null);
		Map<String,Object> result = new HashMap<String,Object>();
		if(list.size()>0){
			result.put("success", false);
		}else{
			result.put("success", true);
		}
		return result;
	}
	
	@RequestMapping(value="del",method=RequestMethod.POST,produces ={"application/json;charset=UTF-8"})
	public @ResponseBody Map<String,Object> del(String ids){
	    String[] idArray = ids.split(",");
	    List<Long> idls = new ArrayList<Long>();
	    for(String id:idArray){
	    	Long idl = Long.valueOf(id);
	    	idls.add(idl);
	    }
	    Map<String,Object> result = new HashMap<String,Object>();
	    try{
	    	categoryEditService.deleteMulti(idls);
	    	result.put("success", true);
	    }catch(Exception ex){
	    	result.put("success", false);
	    }
	    return result;
	}

	

}
