package com.wh.app.web.service.edit;

import java.util.List;

import com.wh.app.web.model.edit.RecommandEdit;
import com.wh.app.web.service.BaseService;

public interface RecommandEditService extends BaseService<RecommandEdit>{

	public void setRecommandEdit(List<Long> ids);
}
