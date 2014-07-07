package Test;

import redis.clients.jedis.Jedis;

public class JedisConnection {
	
	Jedis jedis;
	
	public JedisConnection() {
		try {
			Jedis j = new Jedis("localhost");
			setJedis(j);
		} catch(Exception e){
			System.out.println("redis-server를 열고 다시 실행하세요");
			e.printStackTrace();
			System.exit(999);
		}
	}
	
	public void ConnectionTest(){
		getJedis().set("key", "hello~");
		System.out.println(getJedis().get("key"));
	}

	public Jedis getJedis() {
		return this.jedis;
	}

	public void setJedis(Jedis jedis) {
		jedis.del("editedData");
		this.jedis = jedis;
	}
	
	public void closeRedis(){
		getJedis().close();
	}
}
