package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTemplate {
	// 싱글톤 패턴 적용
	private static JDBCTemplate instance;
	private static Connection conn;
	
	private JDBCTemplate() {}
		
	public static JDBCTemplate getInstance() {
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}
	
	public Connection createConnection() {	
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "MEMBERWEB";
		String password = "MEMBERWEB";
		try {
			// 닫았을 때도 다시 만들어 줘라
			if(conn == null || conn.isClosed()) {				
				Class.forName(driverName);
				conn = DriverManager.getConnection(url,user,password);
				conn.setAutoCommit(false); // 오토커밋 해제
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public void close(Connection conn) {
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void commit(Connection conn) {
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void rollback(Connection conn) {
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}


