##写SpringBoot


##资料
[GitHub 文档](https://github.com/Cassiezys/Spbbt)  
[Spring Web](https://spring.io/guides/gs/serving-web-content/)  
[Bootstrap](https://v3.bootcss.com/components)  
[GitHub OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)  
[OkHttp Document](https://square.github.io/okhttp/)  
[H2 Database Document](http://www.h2database.com/)  
[mybatis-spring-boot](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)  
[Spring Document database](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-embedded-database-support)

##工具
[Git](https://github.com/)  
[OAuth test](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)


##脚本
'''sql
CREATE CACHED TABLE "PUBLIC"."USER"(
    "ID" INT  NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_2619B086_F183_44CA_9EEF_A3A4B1DE7B42",
    "ACCOUNT_ID" VARCHAR(100),
    "NAME" VARCHAR(50),
    "TOKEN" CHAR(36) COMMENT STRINGDECODE('\u5f53\u524d\u8d26\u6237\u7684token\n'),
    "GMT_CREATE" BIGINT COMMENT STRINGDECODE('\u521b\u5efa\u7684\u65f6\u95f4'),
    "GMT_MODIFIED" BIGINT COMMENT STRINGDECODE('\u4fee\u6539\u4f4d')
)


'''