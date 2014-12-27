package com.wh.app.web.service.edit;

import java.util.List;

import com.wh.app.web.model.edit.EassayEdit;
import com.wh.app.web.service.BaseService;

public interface EassayEditService extends BaseService<EassayEdit> {
	public void setTypeMutil(List<Long> ids,String typeId,String typeName);
	public void setRecommend(List<Long> ids,boolean isRecmmand);
}
