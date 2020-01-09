package com.pazz.java.thinking.datasource;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.CommonDataSource;

/**
 * 
 * <p>
 * Copyright： Copyright (c) 2017
 * </p>
 * <p>
 * Company： 熠道大数据
 * </p>
 * 
 * @ClassName: AbstractCommonDataSource
 * @Description: TODO(日志资源管理器)
 * @author liuhonbin
 * @date 2018年4月24日
 */
public abstract class AbstractCommonDataSource implements CommonDataSource {

	protected PrintWriter logWriter = new PrintWriter(System.out);

	public PrintWriter getLogWriter() throws SQLException {
		return logWriter;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		this.logWriter = out;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new SQLFeatureNotSupportedException();
	}

}