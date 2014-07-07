package Test;

import java.sql.SQLException;
import java.sql.Statement;

import redis.clients.jedis.Jedis;

public class SaveInMysql {
	
	// redis 데이터를 mysql로 이동하기
	public void cacheToData(Statement statement, Jedis jedis) {
		String editedId;
		while(true){
			// 수정된 데이터의 id를 리스트에서 가져오기
			editedId = jedis.lpop("edited");
			if (editedId == null){
				break;
			}
			updateMysql(statement, jedis, editedId);
		}
	}
	
	private void updateMysql(Statement statement, Jedis jedis, String id){
		String email = jedis.hget(id, "email");
		String sql = "update user set email=\"" + email + "\" where id=" + id + ";";
		try {
			statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

