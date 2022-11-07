package com.hkbank.pbcrs.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author yangbo
 * @category 默认数据访问对象接口展现
 * @version 0.0.1
 * @date 2012/12/26
 */
@Repository
public class BaseSessionDaoSupportImpl extends SqlSessionDaoSupport {

	@Override
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

}
