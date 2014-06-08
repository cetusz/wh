/**
 * 
 */
package com.my.common.domain;

import java.io.Serializable;


/**
 * 
 * @cms-common
 * @BaseEntity.java
 * @author 904032
 * @2014年3月21日-上午9:10:09
 */
public interface BaseEntity<S extends Serializable> extends Query {
	public  S getId() ;
	public  void setId(S id) ;
}
