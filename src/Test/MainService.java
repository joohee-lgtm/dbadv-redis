package Test;

import java.util.Scanner;

import redis.clients.jedis.Jedis;

public class MainService {
	public static void main(String[] args) {
		
		JedisConnection jc = new JedisConnection();
		MysqlConnection mc = new MysqlConnection();
		SaveInRedis sir = new SaveInRedis();
		SaveInMysql sim = new SaveInMysql();
		
		sir.dataToCache(mc.getStmt(), jc.getJedis());
		scanData(sir, jc, mc);
		sim.cacheToData(mc.getStmt(), jc.getJedis());
		jc.closeRedis();
		mc.closeMysql();
		System.exit(999);
	}
	
	// 수정할 데이터 입력하기 
	public static void scanData(SaveInRedis sir, JedisConnection jc, MysqlConnection mc){
		System.out.println("====================================");
		System.out.println("id 번호를 입력하고, 수정할 email을 입력하세요");
		System.out.println("종료를 원하면 id와 email에 exit를 입력하세요");
		System.out.println("====================================");
		String exit = "exit";
		Jedis jedis = jc.getJedis();
		while(true){
			Scanner scanId = new Scanner(System.in);
			String id;
			do {
				System.out.printf("id 		: ");
				id = scanId.nextLine();
			} while(!isIntNumber(id));
			System.out.printf("email	: ");
			Scanner scanEmail = new Scanner(System.in);
			String email = scanEmail.nextLine();
			if(id.equals(exit) || email.equals(exit)){
				break;
			}
			sir.editCacheData(jedis, id, email);
		}
		System.out.println("종료 합니다.");
	}
	
	public static boolean isIntNumber(String str){
		try {
			double d = java.lang.Integer.parseInt(str);
			return true;
		} catch (NumberFormatException ex){
			System.out.println("정수형 id를 적어주세여");
			return false;
		}
	}
} 
