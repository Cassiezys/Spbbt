##写SpringBoot


##资料
[GitHub 文档](https://github.com/Cassiezys/Spbbt)  
[Spring Web](https://spring.io/guides/gs/serving-web-content/)  
[Bootstrap 整体框架](https://v3.bootcss.com/components)  
[GitHub OAuth 用GitHub账号登陆](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)  
[OkHttp Document ](https://square.github.io/okhttp/)  
[H2 Database Document 轻量级数据库](http://www.h2database.com/)  
[mybatis-spring-boot 数据库语言](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)  
[Spring Document database](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-embedded-database-support)    
[菜鸟教程Mysql](https://www.runoob.com/mysql/mysql-tutorial.html)  
[thymeleaf vs jsp](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)   
[Spring MVC Filter](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-cors-processing)  



##工具
[Git](https://github.com/)  
[OAuth test](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)  
[flyway 一起更新数据库](https://flywaydb.org/getstarted/firststeps/maven)  
[lombok 可以自动加载gettersetter](https://projectlombok.org/setup/maven)  
[springboot](https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-devtools)    
[Spring Interception拦截器](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-handlermapping-interceptor)


##脚本
'''sql
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `token` char(36) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `bio` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
'''