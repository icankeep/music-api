-- auto-generated definition
create table discuss_event
(
    id          bigint auto_increment comment '动态id'
        primary key,
    user_id     varchar(30)  null comment '网易云用户id',
    title       varchar(300) null comment '动态标题',
    content     varchar(500) null comment '动态内容',
    create_time datetime     null comment '动态创建时间'
)
    comment '评论区动态';

-- auto-generated definition
create table discuss_comment
(
    id          bigint auto_increment comment '动态的id'
        primary key,
    user_id     varchar(30)  null comment '网易云用户id',
    content     varchar(500) null comment '评论内容',
    create_time datetime     null comment '评论创建时间',
    event_id    bigint       null comment '评论所属动态'
)
    comment '讨论区动态下面的评论';

