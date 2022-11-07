package com.hkbank.pbcrs.controller;

import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

public class Beanfactory extends SqlSessionFactoryBean{
	@Override
	protected SqlSessionFactory buildSqlSessionFactory() throws Exception {
		try{
		return super.buildSqlSessionFactory();
		}catch(IOException e){
			e.printStackTrace();
			throw new IOException(e);
		}
		
	}

}
