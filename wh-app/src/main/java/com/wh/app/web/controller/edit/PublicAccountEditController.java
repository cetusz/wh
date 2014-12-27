package com.wh.app.web.controller.edit;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.my.common.util.PropertiesFileUtil;
import com.my.mybatis.support.Page;
import com.wh.app.web.extractor.WeixinAccountExtractor;
import com.wh.app.web.model.edit.CategoryEdit;
import com.wh.app.web.model.edit.PublicAccountEdit;
import com.wh.app.web.model.query.PublicAccountEditQuery;
import com.wh.app.web.service.edit.CategoryEditService;
import com.wh.app.web.service.edit.PublicAccountEditService;

@Controller
@RequestMapping("/admin/publicaccountedit")
public class PublicAccountEditController {
	Logger logger = Logger.getLogger(PublicAccountEditController.class);
	@Autowired PublicAccountEditService publicAccountEditService;
	@Autowired WeixinAccountExtractor accountExtractor;
	@Autowired CategoryEditService categoryEditService;

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
	
	
	private static Map<String,Long> cateMap = new HashMap<String,Long>();
	@RequestMapping(value="importData",method = RequestMethod.POST)
	public @ResponseBody String upload(@RequestParam MultipartFile[] myfiles) throws JSONException{
		 for(MultipartFile myfile : myfiles){
			 if(myfile.isEmpty()){
			   return  "{\"success\":false,\"message\":'上传为空'"+"}";
			 }else{
				 //初始化分类
				 initCategories();
				 String originalFilename = myfile.getOriginalFilename();
				 String contentType = myfile.getContentType().toLowerCase();
                 System.out.println("文件原名: " + originalFilename);
                 System.out.println("文件名称: " + myfile.getName());
                 System.out.println("文件长度: " + myfile.getSize());
                 System.out.println("文件类型: " + contentType);
                 System.out.println("========================================");
                 if(contentType.indexOf("sheet")>-1||contentType.indexOf("ms-excel")>-1||contentType.indexOf("kset")>-1){
                	 try {
                		 File file = new File(PropertiesFileUtil.getStringValue("config.properties","file-temp-location"),"accountExcel");
                         FileUtils.copyInputStreamToFile(myfile.getInputStream(), file);
                         XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
                		 XSSFSheet sheet = wb.getSheetAt(0);   
                         int totalRows = sheet.getLastRowNum();   
                         for (int i = 1; i <= totalRows; i++) {
                        	   XSSFRow row = sheet.getRow(i);
                               String accountName = row.getCell(0).toString();
                               String category = row.getCell(1).toString().trim();
                               PublicAccountEdit saveAccount = accountExtractor.extractAccount(accountName);
                               if(saveAccount!=null){
	                               saveAccount.setCateIds(cateMap.get(category)==null?"":cateMap.get(category).toString());
	                               saveAccount.setCateNames(category);
	                               PublicAccountEditQuery query = new PublicAccountEditQuery();
	                               query.setAccountId(saveAccount.getAccountId());
	                               if(publicAccountEditService.selectList(query, null).size()==0){
	                            	   publicAccountEditService.saveOrUpdate(saveAccount);
	                               }
                               }
                         }
					} catch (Exception e) {
						 logger.error(e);
						 return "{\"success\":true,\"message\":'解析失败'"+"}";
					}
                 }
                //更新版本号
	         }
          }
		 return "{\"success\":true,\"message\":'导入成功'"+"}";
	}
	
	private void initCategories(){
		List<CategoryEdit> cates = categoryEditService.selectList(null, null);
		for(CategoryEdit cate:cates){
			cateMap.put(cate.getCateName(), cate.getId());
		}
	}
	
	@RequestMapping(value="settype",produces={"application/json;chartset=utf-8"})
	public @ResponseBody Map<String,Object> setType(String accountIds,String typeId,String typeName){
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			String[] idsArray = accountIds.split(",");
			List<Long> ids = new ArrayList<Long>();
			for(String id:idsArray){
				ids.add(Long.valueOf(id));
			}
			publicAccountEditService.setTypeMutil(ids, typeId,typeName);
			result.put("success", true);
		}catch(Exception ex){
			result.put("success", false);
			result.put("message", "数据库操作异常");
		}
		return result;
	}
}
