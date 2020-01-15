##写SpringBoot

##部署
##依赖
- Git
- JDK（编译）
- Maven（构建项目）
- Mysql 

##步骤
学Linux
- yum update 更新数据源  y
- yum install git （安装git）y
- 去git 复制https地址   创文件夹  mkdir App
- cd App/  进入这个目录
- git clone (https地址)
- ls 获取当前目录
- mvn compile (如果没有mvn要先安装maven yum install maven ; java -version 看java版本 mvn -v看版本)
- pwd 看当前路径
- mvn clean compile package
- mvn compile package
修改服务器端
- cp 复制一个文件
     cp src/main/resources/application.properties  src/main/resources/application-production.properties
- vim src/main/resources/application-production.properties 编辑文件
   a 编辑文件 esc  :x 保存修改并退出
java -jar -DSpring.profiles.active=production target/community-0.0.1-SNAPSHOT.jar
##资料
[GitHub 文档](https://github.com/Cassiezys/Spbbt)  
[Spring Web](https://spring.io/guides/gs/serving-web-content/)  
[Bootstrap 整体框架](https://v3.bootcss.com/components)  
[GitHub OAuth 用GitHub账号登陆](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)  
[OkHttp Document ](https://square.github.io/okhttp/)  
[H2 Database Document 轻量级数据库](http://www.h2database.com/)  
[mybatis-spring-boot 整合](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)      
[mybatis-spring](http://mybatis.org/spring/)    
[Spring Document database](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-embedded-database-support)    
[菜鸟教程Mysql](https://www.runoob.com/mysql/mysql-tutorial.html)  
[thymeleaf vs jsp](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)   
[Spring MVC Filter](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-cors-processing)     
[Mybatis generator(MBG) CRUD](http://mybatis.org/generator/)  
[pagehelper en->zh](https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md)  
[JQUERY API](https://api.jquery.com/)    
[markdown github](https://github.com/pandao/editor.md)
[blog](https://cassiezys.github.io/)


##工具
[Git](https://github.com/)  
[OAuth test](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)  
[flyway 一起更新数据库](https://flywaydb.org/getstarted/firststeps/maven)  
[lombok 可以自动加载gettersetter](https://projectlombok.org/setup/maven)  
[springboot](https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-devtools)    
[Spring Interception拦截器](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-handlermapping-interceptor)  
[pageHelper分页](https://github.com/pagehelper/Mybatis-PageHelper)   
[Tabbed postman 测试接口](chrome-extension://coohjcphdfgbiolnekdpbcijmhambjff/index.html)  
[Sprint lang3 工具包]  
[markdown ](https://pandao.github.io/editor.md/)

##脚本

```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
mvn clean install -DskipTests
安装
mvn install:install-file -Dfile=E:\Tools\pacc\bags\aliyun-java-vod-upload-1.3.1.jar -DgroupId=com.aliyun -DartifactId=aliyun-java-vod-upload -Dversion=1.3.1 -Dpackaging=jar

```
