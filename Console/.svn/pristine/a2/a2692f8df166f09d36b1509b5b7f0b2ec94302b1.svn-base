package com.ebeijia.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtil {
	
	private static ComboPooledDataSource dataSource;

	
	static {
		dataSource = new ComboPooledDataSource();
	
	}
	/**
	 * Get database connection 
	 * 
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}


	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.clearBatch();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	
	public static void attemptClose(ResultSet o) {
		try {
			if (o != null)
				o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void attemptClose(Statement o) {
		try {
			if (o != null)
				o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void attemptClose(Connection o) {
		try {
			if (o != null)
				o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM tbl_eeq_menu");
			while (rs.next())
				System.out.println(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			attemptClose(rs);
			attemptClose(stmt);
			attemptClose(con);
		}
		
		JDBCUtil util = new JDBCUtil();
		for(int i=0;i<50000;i++){
			util.pressureTest();
			System.out.println("====================================="+i);
		}
	}
	
	private void pressureTest(){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM tbl_eeq_menu");
			while (rs.next())
				System.out.println(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			attemptClose(rs);
			attemptClose(stmt);
			attemptClose(con);
		}
	}
}