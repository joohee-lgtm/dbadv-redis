package Test;

import java.sql.*;

import redis.clients.jedis.Jedis;

public class SaveInRedis {
	
	// mysql 에 있는 데이터를 redis로 저장하기
	public void dataToCache(Statement stmt, Jedis jedis){
		String sql = "select * from user";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int idInt = rs.getInt("id");
				String id = String.valueOf(idInt);
				String username = rs.getString("username");
				String passwd = rs.getString("passwd");
				String email = rs.getString("email");
 				setRedis(jedis, id, username, passwd, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void setRedis(Jedis jedis, String id, String username, String passwd, String email){
		jedis.hset(id, "username", username);
		jedis.hset(id, "passwd", passwd);
		jedis.hset(id, "email", email);
	}

	public void editCacheData(Jedis jedis, String id, String email) {
		jedis.hset(id, "email", email);
		// 수정된 데이터의 id를 리스트로 저장하기
		jedis.lpush("editedData", id);
		System.out.println("저장되었습니다.");
	}	
}