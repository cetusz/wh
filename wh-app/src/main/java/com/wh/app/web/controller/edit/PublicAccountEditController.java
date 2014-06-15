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
import com.wh.app.web.extractor.WeixinAccountExtractor;
import com.wh.app.web.model.edit.PublicAccountEdit;
import com.wh.app.web.model.query.PublicAccountEditQuery;
import com.wh.app.web.service.edit.PublicAccountEditService;

@Controller
@RequestMapping("/admin/publicaccountedit")
public class PublicAccountEditController {
	@Autowired PublicAccountEditService publicAccountEditService;
	@Autowired WeixinAccountExtractor accountExtractor;

	@RequestMapping(value="tolist")
	public ModelAndView tolist(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("account/list");
		return mv;
	}
	
	@RequestMapping(value="toadd")
	public ModelAndView toadd(Long id){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("account/add");
		mv.addObject("id", id);
		return mv;
	}
	
	@RequestMapping(value="save",produces ={"application/json;charset=UTF-8"})
	public @ResponseBody Map<String,Object> save(PublicAccountEdit edit){
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			if(edit.getId()==null){
				edit = accountExtractor.extractAccount(edit.getChineseName());
			}
			publicAccountEditService.saveOrUpdate(edit);
			result.put("success", true);
		}catch(Exception ex){
			result.put("success", false);
		}
		return result;
	}
	
	@RequestMapping(value="get",produces ={"application/json;charset=UTF-8"})
	public @ResponseBody PublicAccountEdit get(Long id){
		PublicAccountEdit publicAccountEdit = publicAccountEditService.findOne(id);
		return publicAccountEdit;
	}
	
	
	@RequestMapping(value="list",produces ={"application/json;charset=UTF-8"})
	public @ResponseBody Map<String,Object> list(PublicAccountEditQuery query,@RequestParam("page")int page,@RequestParam("rows")int rows){
		 Map<String,Object> resultMap = new HashMap<String,Object>();
		 Page<PublicAccountEdit> pager = new Page<PublicAccountEdit>(rows,page);
		 publicAccountEditService.getPageList(pager, query, null);
		 resultMap.put("total", pager.getTotal());
		 resultMap.put("rows", pager.getResult());
		 return resultMap;
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
	    	publicAccountEditService.deleteMulti(idls);
	    	result.put("success", true);
	    }catch(Exception ex){
	    	result.put("success", false);
	    }
	    return result;
		
	}
}
