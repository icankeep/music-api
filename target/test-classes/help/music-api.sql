-- auto-generated definition
create table user
(
    id                            varchar(30)             not null,
    phone                         varchar(11)             null,
    email                         varchar(30)             null,
    password                      varchar(30)             null,
    nickname                      varchar(30)             null,
    gender                        varchar(1)              null,
    birthday                      varchar(30)             null,
    city                          varchar(30)             null,
    province                      varchar(30)             null,
    background_url                varchar(100)            null,
    avatar_url                    varchar(100)            null,
    followeds                     varchar(30) default '0' null,
    follows                       varchar(30) default '0' null,
    event_count                   varchar(30) default '0' null,
    playlist_count                varchar(30) default '0' null,
    detail_description            varchar(300)            null,
    people_can_see_my_play_record tinyint(1)  default 1   null,
    level                         varchar(5)  default '0' null,
    create_time                   varchar(30)             null,
    listen_songs                  varchar(30) default '0' null,
    create_days                   varchar(30) default '0' null,
    default_avatar                tinyint(1)  default 0   null,
    followed                      varchar(30)             null,
    signature                     varchar(300)            null,
    constraint user_id_uindex
        unique (id)
);

alter table user
    add primary key (id);

-- auto-generated definition
create table record
(
    id          bigint auto_increment
        primary key,
    song_id     varchar(30)             null,
    song_name   varchar(100)            null,
    artist_id   varchar(30)             null,
    artist_name varchar(30)             null,
    play_count  varchar(30) default '0' null,
    score       int         default 0   null,
    user_id     varchar(30)             null
);

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

