MYSQL-REDIS Application
== 
**구현내용**
- 어플리케이션을 실행할 때 Mysql 의 데이터가 Redis 로 이동한다
- 사용자는 Redis 에서 데이터에 접근한다
- Redis 에 수정된 내용이 있을 경우 해당 데이터의 Primary Key 값을 저장한다.
- 어플리케이션을 종료할 때 수정된 내용이 있는 데이터들만 Mysql 로 다시 저장한다.


==
- download jedis jar and install at java project <br>
   - http://search.maven.org/remotecontent?filepath=redis/clients/jedis/2.4.2/jedis-2.4.2.jar <br>
<br>
- Redis
   - https://github.com/xetorthio/jedis<br>
