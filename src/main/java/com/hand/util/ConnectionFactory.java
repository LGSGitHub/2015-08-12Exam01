package com.hand.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static String driver;
	private static String dburl;
	private static String user;
	private static String password;

	private static final ConnectionFactory factory = new ConnectionFactory();

	private Connection conn = null;

	// 使用static声明代码块，静态代码块用于初始化类，可以为类的属性赋值，当加载类的时候会被加载，只执行一次
	static {
		// properties类用来保存（处理）属性文件中的键值对
		Properties prop = new Properties();
		try {
			// 将属性文件中的键值对读取到输入流中
			InputStream in = ConnectionFactory.class.getClassLoader().getResourceAsStream("dbconfig.properties");

			prop.load(in);

		} catch (IOException e) {
			e.printStackTrace();
		}

		driver = prop.getProperty("driver");

		dburl = prop.getProperty("dburl");
		user = prop.getProperty("user");
		password = prop.getProperty("password");
	}

	private ConnectionFactory() {
	}

	public static ConnectionFactory getInstance() {
		return factory;
	}

	public Connection makeConnection() {
		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(dburl, user, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
