package com.wh.app.web.service.edit;

import java.util.List;

import com.wh.app.web.model.edit.PublicAccountEdit;
import com.wh.app.web.service.BaseService;

public interface PublicAccountEditService extends BaseService<PublicAccountEdit> {

	public void setTypeMutil(List<Long> ids,String typeId,String typeName);
}
