create table notification
(
	id bigint auto_increment,
	notifier bigint null comment '通知的人',
	receiver bigint null comment '接受通知的人',
	outerid bigint null comment '评论还是点赞的问题或者评论的id（我回复的是谁）',
	type int null comment '评论还是回复',
	gmt_create bigint null,
	status int default 0 null comment '状态0未读',
	notifier_name varchar(100) null comment '操作的人名',
    outer_title varchar(256) null comment '标题',
	constraint notification_pk
		primary key (id)
);

