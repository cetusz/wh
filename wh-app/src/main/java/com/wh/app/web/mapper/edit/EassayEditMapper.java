package com.wh.app.web.mapper.edit;

import java.util.List;

import com.my.mybatis.support.mapper.BaseMapperInterface;
import com.wh.app.web.model.edit.EassayEdit;
;
public interface EassayEditMapper extends BaseMapperInterface<EassayEdit> {
	public void setTypeMutil(List<Long> ids, String typeId,String typeName);
	public void setRecommend(List<Long> ids,boolean isRecmmend);
}
