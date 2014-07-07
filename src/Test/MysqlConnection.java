package Test;

import java.sql.*;

public class MysqlConnection {

	Connection conn;
	Statement stmt;

	public MysqlConnection() {

		String jdbcURL = "jdbc:mysql://localhost:3306/DBAdv_mysql";
		String jdbcUSER = "devtest";
		String jdbcPW = "0000";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			setConn(DriverManager.getConnection(jdbcURL, jdbcUSER, jdbcPW));
			setStmt(getConn().createStatement());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ConnectionTest() {

		System.out.println("sql connection success");
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}
	
	public void closeMysql(){
		try {
			getConn().close();
			getStmt().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
