package com.wh.app.web.controller.pub;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.my.mybatis.support.Page;
import com.wh.app.web.model.pub.MemberEntity;
import com.wh.app.web.model.query.MemberQuery;
import com.wh.app.web.service.pub.MemberService;


@Controller
@RequestMapping("/admin/member")
public class MemberController {
	
	@Autowired MemberService memberService;
	
	@RequestMapping(value="tolist")
	public ModelAndView tolist(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/list");
		return mv;
	}
	
	@RequestMapping(value="list",produces={"application/json;charset=utf-8"})
	public Map<String,Object> list(MemberQuery query,@RequestParam("page")int page,@RequestParam("rows")int rows){
		Page<MemberEntity> pager = new Page<MemberEntity>(rows,page);
		memberService.getPageList(pager, query, null);
		Map<String,Object> resultMap = new HashMap<String,Object>();
	    resultMap.put("total", pager.getTotal());
	    resultMap.put("rows", pager.getResult());
	    return resultMap;
	}
	

}
