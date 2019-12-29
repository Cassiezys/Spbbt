CREATE TABLE `comment` (
`id`  bigint NULL AUTO_INCREMENT ,
`parent_id`  bigint NOT NULL COMMENT '问题的id' ,
`type`  int NULL COMMENT '回复的回复一级或者二级的回复' ,
`commentator`  int NULL COMMENT '评论人id' ,
`gmt_create`  bigint NULL ,
`gmt_modified`  bigint NULL ,
`like_count`  bigint NULL DEFAULT 0 ,
PRIMARY KEY (`id`)
)
;