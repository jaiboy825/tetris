package net.gondr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
	public static Connection getConnection() {
		String connStr = "jdbc:mysql://gondr.asuscomm.com/yy_10221" 
	    		+ "?useUnicode=true"
	    		+ "&characterEncoding=utf8"
	    		+ "&useSSL=false"
	    		+ "&serverTimezone=Asia/Seoul";
	    String userId = "yy_10221";
	    String userPw = "1234";
	    
	    Connection con = null;
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(connStr, userId, userPw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return con;
	}
	
	public static void close(Connection value) {
		if(value != null) try { value.close(); } catch (Exception e) {}
	}
	
	public static void close(PreparedStatement value) {
		if(value != null) try { value.close(); } catch (Exception e) {}
	}
	
	public static void close(ResultSet value) {
		if(value != null) try { value.close(); } catch (Exception e) {}
	}
	
}








