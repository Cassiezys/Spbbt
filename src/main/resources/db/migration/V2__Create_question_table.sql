CREATE TABLE 'question' (
`id`  BIGINT NULL AUTO_INCREMENT ,
`title`  varchar(50) NULL ,
`description`  text NULL ,
`gmt_create`  bigint NULL ,
`gmt_modified`  bigint NULL ,
`creator`  int NULL ,
`comment_count`  int NULL DEFAULT 0 ,
`view_count`  int NULL DEFAULT 0 ,
`like_count`  int NULL DEFAULT 0 ,
`tag`  varchar(256) NULL ,
PRIMARY KEY (`id`)
)
;
