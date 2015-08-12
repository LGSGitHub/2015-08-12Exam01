package com.hand._08_12Exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			// 注册mysql JDBC驱动程序
			Class.forName("com.mysql.jdbc.Driver");

			// 获取mysql数据库的连接，参数分别是URL，用户名和密码
			// localhost 是当前主机，3306是端口，jsp_db是当前使用的数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

	public static void getCities(int id) {
		Connection conn = getConnection();
		// ConnectionFactory cf = ConnectionFactory.getInstance();
		Statement st = null;
		ResultSet rs = null;

		try {
			String country = "select country from country where country_id =" +id;
			st = conn.createStatement();
			rs = st.executeQuery(country);
			while(rs.next())
			{
				System.out.println(rs.getString("country") + "的城市》》》");
			}
			System.out.println("城市ID | 城市名称");

			String sql = "select city_id,city from city where country_id =" + id;
			ResultSet city = st.executeQuery(sql);

			while (city.next()) {
				System.out.print(city.getInt("city_id") + " " + "|" +" ");
				System.out.println(city.getString("city"));

			}
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入CountryID：");
		int countryId = sc.nextInt();
		sc.close();
		getCities(countryId);

	}

}
