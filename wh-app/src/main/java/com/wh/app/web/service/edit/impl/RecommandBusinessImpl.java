package com.wh.app.web.service.edit.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wh.app.web.service.edit.EassayEditService;
import com.wh.app.web.service.edit.RecommandBusiness;
import com.wh.app.web.service.edit.RecommandEditService;

@Service
@Transactional
public class RecommandBusinessImpl implements RecommandBusiness {

	@Autowired EassayEditService eassayEditService;
	@Autowired RecommandEditService recommandEditService;
	@Override
	public void setRecommandEdit(List<Long> ids, boolean isRecommand) {
		eassayEditService.setRecommend(ids, isRecommand);
		if(isRecommand){
			recommandEditService.setRecommandEdit(ids);
		}else{
			recommandEditService.deleteMulti(ids);
		}
	}

}
