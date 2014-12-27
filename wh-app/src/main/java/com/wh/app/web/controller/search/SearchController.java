package com.wh.app.web.controller.search;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.app.web.model.search.AccountSearchVo;
import com.wh.app.web.model.search.EassaySearchVo;
import com.wh.app.web.model.search.Pager;
import com.wh.app.web.searh.IndexService;
import com.wh.app.web.searh.IndexService.IndexType;
import com.wh.app.web.searh.SearchService;

@Controller
@RequestMapping("admin/search")
public class SearchController {
	
	@Autowired IndexService indexService;
	@Autowired SearchService searchService;
	
	@RequestMapping(value="index")
	public String index(){
		return "search/index";
	}
	
	@RequestMapping(value="search")
	public String search(){
		return "search/search";
	}
	
	@RequestMapping(value="indexaccount",produces={"application/json;charset=utf-8"})
	public @ResponseBody Map<String,Object> indexAccount(){
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			indexService.indexAccount(null, IndexType.ALL);
			result.put("success", true);
			result.put("message","执行成功");
		}catch(Exception ex){
			result.put("success", false);
			result.put("message",ex.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping(value="indexEassay",produces={"application/json;charset=utf-8"})
	public @ResponseBody Map<String,Object> indexEassay(){
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			indexService.indexEassay(null, IndexType.ALL);
			result.put("success", true);
			result.put("message","执行成功");
		}catch(Exception ex){
			result.put("success", false);
			result.put("message",ex.getMessage());
		}
		return result;
	}
	
	
	
	@RequestMapping(value="a",produces={"application/json;charset=utf-8"})
	public @ResponseBody Pager<AccountSearchVo> searchAccount(String q,int page,int pageSize){
		Pager<AccountSearchVo> pager = new Pager<AccountSearchVo>();
		try {
			pager = searchService.searchAccount(q, page, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pager;
	}
	

	@RequestMapping(value="e",produces={"application/json;charset=utf-8"})
	public @ResponseBody Pager<EassaySearchVo> searchEassay(String q,int page,int pageSize){
		Pager<EassaySearchVo> pager = new Pager<EassaySearchVo>();
		try {
			pager = searchService.searchEassay(q, page, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pager;
	}
	
}
