## music-api

版本2.0

调用接口前缀：https://localhost:8443/music-api-2.0

### 登录接口(目前只支持手机号登录,邮箱登录未做支持)

/login/cellphone?phone=xxx&password=xxx

[返回数据格式](https://github.com/icankeep/music-api/blob/master/src/test/resources/user_login.json)

### 用户听歌记录接口(只返回记录前一百首)

type=0为总的记录,type=1为最近一周的记录

/user/record?uid=xxx&type=0

[返回数据格式](https://github.com/icankeep/music-api/blob/master/src/test/resources/user_record.json)

### 用户详细信息

/user/detail?uid=xxx

[返回数据格式](https://github.com/icankeep/music-api/blob/master/src/test/resources/user_detail.json)

### 用户匹配(两人听歌记录权重排列)

/user/match?uid=xxx

[返回数据格式](https://github.com/icankeep/music-api/blob/master/src/test/resources/user_match.json)

## 讨论区接口

### 首页所有动态（未做分页，显示所有动态）

/discuss/all

[返回数据格式](https://github.com/icankeep/music-api/blob/master/src/test/resources/discuss_all.json)

### 指定个数的最新动态

如果直接按照下面url调用,默认条数为20条

/discuss/event/new?limit=

[返回数据格式](https://github.com/icankeep/music-api/blob/master/src/test/resources/discuss_all.json)

### 单个动态详细信息

包括发布的用户信息，动态包含的所有评论信息

/discuss/event/detail?id=xxx

[返回数据格式](https://github.com/icankeep/music-api/blob/master/src/test/resources/event_detail.json)

### 添加动态

/discuss/event/insert

请求方式为post,请求参数：userId,title,content

返回数据：

添加失败
```json
{
  "msg": "添加成功",
  "code": "200"
}
```

添加失败
```json
{
  "msg": "添加失败,发生错误",
  "code": "400"
}
```

### 删除动态

/discuss/event/delete?id=xxx

返回数据：

删除成功
```json
{
  "msg": "删除成功",
  "code": "200"
}
```
删除失败
```json
{
  "msg": "删除失败,动态不存在或发生错误",
  "code": "400"
}
```

### 添加评论

/discuss/comment/insert

请求方式为post,请求参数为:userId,eventId,content

返回数据：

添加成功
```json
{
  "msg": "添加成功",
  "code": "200"
}
```
添加失败
```json
{
  "msg": "添加失败,发生错误",
  "code": "400"
}
```

### 删除评论

/discuss/comment/delete?id=

返回数据：

删除成功
```json
{
  "msg": "删除成功",
  "code": "200"
}
```
删除失败
```json
{
  "msg": "删除失败,评论不存在或发生错误",
  "code": "400"
}
```