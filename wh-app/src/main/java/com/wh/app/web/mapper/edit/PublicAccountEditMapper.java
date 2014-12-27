package com.wh.app.web.mapper.edit;

import java.util.List;

import com.my.mybatis.support.mapper.BaseMapperInterface;
import com.wh.app.web.model.edit.PublicAccountEdit;

public interface PublicAccountEditMapper extends BaseMapperInterface<PublicAccountEdit> {

	public void setAccountType(List<Long> ids,String typeId,String typeName);
}
