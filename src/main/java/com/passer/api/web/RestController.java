package com.passer.api.web;

import com.alibaba.fastjson.JSON;
import com.passer.api.dao.DiscussCommentDao;
import com.passer.api.dao.DiscussEventDao;
import com.passer.api.dao.RecordDao;
import com.passer.api.dao.UserDao;
import com.passer.api.model.*;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.*;

/**
* @Description: <p>
* @author: passer<p>
* @version：2019年5月18日 下午6:08:42<p>
*/
@Controller
public class RestController {

	private static final String ip = "http://47.100.45.234";

	@Autowired
	private SqlSession sqlSession;

//	@RequestMapping(value = "/{path}", produces = "text/plain;charset=utf-8")
//	public @ResponseBody String hello(@PathVariable String path) {
//		return  path;
//	}

	@RequestMapping(value = "/login/cellphone", produces = "application/json;charset=utf-8")
	public @ResponseBody User loginByPhone(@RequestParam String phone, @RequestParam String password) {
		try {
			URL url = new URL(ip + "/login/cellphone?phone=" + phone + "&password=" + password);
			String data = IOUtils.toString(url, "utf8");

//			// 判断是否出错，密码错误等
//			Page error = JSON.parseObject(data, Page.class);
//			if (error != null && !"200".equals(error.getCode())) {
//				return error.toString();
//			}

			User user = JSON.parseObject(data, User.class);
			url = new URL(ip + "/user/detail?uid=" + user.getProfile().getUserId());
			data = IOUtils.toString(url, "utf8");

//			error = JSON.parseObject(data, Page.class);
//			if (error != null && !"200".equals(error.getCode())) {
//				return error.toString();
//			}

			User user1 = JSON.parseObject(data, User.class);
			user1.setPhone(phone);
			user1.setPassword(password);
			UserDao userDao = sqlSession.getMapper(UserDao.class);
			User user2 = userDao.selectById(user1.getProfile().getUserId());
			if (user2 == null) {
				userDao.insert(user1);
			} else {
				userDao.update(user1);
			}

			String uid = user1.getProfile().getUserId();
			RecordDao recordDao = sqlSession.getMapper(RecordDao.class);
			recordDao.deleteByUserId(uid);

			url = new URL(ip + "/user/record?uid=" + uid + "&type=" + 0);
			data = IOUtils.toString(url, "utf8");

//			error = JSON.parseObject(data, Page.class);
//			if (error != null && !"200".equals(error.getCode())) {
//				return error.toString();
//			}

			RecordData recordData = JSON.parseObject(data, RecordData.class);
			List<Record> recordList = recordData.getAllData();
			recordDao.deleteByUserId(uid);
			for (Record record : recordList) {
				record.setUserId(uid);
				recordDao.insert(record);
			}
			sqlSession.commit();
			return user1;
		} catch (Exception e){
			sqlSession.rollback();
			e.printStackTrace();
		}
		return null;
	}

//	@RequestMapping(value = "/login", produces = "text/plain;charset=utf-8")
//	public @ResponseBody String loginByEmail(@RequestParam String email, @RequestParam String password) {
//		try {
//			URL url = new URL(ip + "/login?email=" + email + "&password=" + password);
//			String data = IOUtils.toString(url, "utf8");
//			return data;
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//		return null;
//	}

	@RequestMapping(value = "/user/detail", produces = "application/json;charset=utf-8")
	public @ResponseBody User userDetail(@RequestParam String uid) {
		try {
			URL url = new URL(ip + "/user/detail?uid=" + uid);
			String data = IOUtils.toString(url, "utf8");
			User user1 = JSON.parseObject(data, User.class);
			UserDao userDao = sqlSession.getMapper(UserDao.class);
			User user2 = userDao.selectById(user1.getProfile().getUserId());
			if (user2 == null) {
				userDao.insert(user1);
			} else {
				user1.setPhone(user2.getPhone());
				user1.setPassword(user2.getPassword());
				userDao.update(user1);
			}
			sqlSession.commit();
			return user1;
		} catch (Exception e){
			sqlSession.rollback();
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/user/match", produces = "application/json;charset=utf-8")
	public @ResponseBody MatchResult userMatch(@RequestParam String uid) {
		try {
			RecordDao recordDao = sqlSession.getMapper(RecordDao.class);
			UserDao userDao = sqlSession.getMapper(UserDao.class);
			List<Record> recordList1 = recordDao.selectByUserId(uid);

			List<String> userIdList = recordDao.selectAllUserIdExcludeOne(uid);

			PriorityQueue<Match> priorityQueue = new PriorityQueue<>();

			HashMap<String, Integer> map = new HashMap<>();
			for (Record record : recordList1) {
				map.put(record.getSong().getSong().getSongId(), record.getScore());
			}

			for (String userId : userIdList) {
				List<Record> recordList2 = recordDao.selectByUserId(userId);
				double weight = 0.0;
				for (Record record : recordList2) {
					String songId = record.getSong().getSong().getSongId();
					if (map.containsKey(songId)) {
						weight += map.get(songId) * record.getScore();
					}
				}
				priorityQueue.add(new Match(userId, weight));
			}
			List<Match> allMatchData = new ArrayList<>();
			while (!priorityQueue.isEmpty()) {
				Match match = priorityQueue.poll();
				User user = userDao.selectById(match.getUserId());
				match.setUser(user);
				allMatchData.add(match);
			}
			return new MatchResult(allMatchData);
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/user/record", produces = "application/json;charset=utf-8")
	public @ResponseBody List<Record> userRecord(@RequestParam String uid, @RequestParam String type) {
		try {
			URL url = new URL(ip + "/user/record?uid=" + uid + "&type=" + type);
			String data = IOUtils.toString(url, "utf8");
			RecordData recordData = JSON.parseObject(data, RecordData.class);
			List<Record> recordList = null;
			if ("0".equals(type)) {
				recordList = recordData.getAllData();
			} else if ("1".equals(type)){
				recordList = recordData.getWeekData();
			}
			for (Record record : recordList) {
				record.setUserId(uid);
			}
			return recordList;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/discuss/all", produces = "application/json;charset=utf-8")
	public @ResponseBody List<DiscussEvent> discussEvents() {
		try {
			DiscussEventDao discussEventDao = sqlSession.getMapper(DiscussEventDao.class);
			UserDao userDao = sqlSession.getMapper(UserDao.class);
			List<DiscussEvent> discussEventList = discussEventDao.selectAll();
			for (DiscussEvent discussEvent : discussEventList) {
				User user = userDao.selectById(discussEvent.getUserId());
				discussEvent.setUser(user);
			}
			return discussEventList;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/discuss/event/new", produces = "application/json;charset=utf-8")
	public @ResponseBody List<DiscussEvent> discussNewEvent(@RequestParam Integer limit) {
		try {
			limit = limit == null ? 20 : limit;
			DiscussEventDao discussEventDao = sqlSession.getMapper(DiscussEventDao.class);
			UserDao userDao = sqlSession.getMapper(UserDao.class);
			List<DiscussEvent> discussEventList = discussEventDao.selectLimitNewEvents(limit);
			for (DiscussEvent discussEvent : discussEventList) {
				User user = userDao.selectById(discussEvent.getUserId());
				discussEvent.setUser(user);
			}
			return discussEventList;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/discuss/event/detail", produces = "application/json;charset=utf-8")
	public @ResponseBody DiscussEvent discussEventDetail(@RequestParam Long id) {
		try {
			DiscussEventDao discussEventDao = sqlSession.getMapper(DiscussEventDao.class);
			DiscussCommentDao discussCommentDao = sqlSession.getMapper(DiscussCommentDao.class);
			UserDao userDao = sqlSession.getMapper(UserDao.class);
			DiscussEvent discussEvent = discussEventDao.selectById(id);
			discussEvent.setUser(userDao.selectById(discussEvent.getUserId()));

			List<DiscussComment> discussEventList =discussCommentDao.selectByDiscussEventId(discussEvent.getId());
			for (DiscussComment discussComment : discussEventList) {
				User user = userDao.selectById(discussComment.getUserId());
				discussComment.setUser(user);
			}
			discussEvent.setComments(discussEventList);
			return discussEvent;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/discuss/event/delete", produces = "application/json;charset=utf-8")
	public @ResponseBody Page deleteEvent(@RequestParam Long id) {
		try {
			DiscussEventDao discussEventDao = sqlSession.getMapper(DiscussEventDao.class);
			int result = discussEventDao.deleteById(id);
			Page page = new Page();
			if (result == 1) {
				page.setCode("200");
				page.setMsg("删除成功");
			} else {
				page.setCode("400");
				page.setMsg("删除失败,动态不存在或发生错误");
			}
			sqlSession.commit();
			return page;
		} catch (Exception e){
			sqlSession.rollback();
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/discuss/event/insert", produces = "application/json;charset=utf-8",method = RequestMethod.POST)
	public @ResponseBody Page insertEvent(@RequestParam String userId, @RequestParam String title, @RequestParam String content) {
		try {
			DiscussEventDao discussEventDao = sqlSession.getMapper(DiscussEventDao.class);
			DiscussEvent discussEvent = new DiscussEvent();
			discussEvent.setUserId(userId);
			discussEvent.setTitle(new String(title.getBytes("ISO-8859-1"), "UTF-8"));
			discussEvent.setContent(new String(content.getBytes("ISO-8859-1"), "UTF-8"));
			discussEvent.setCreateTime(new Date());
			int result = discussEventDao.insert(discussEvent);
			Page page = new Page();
			if (result == 1) {
				page.setCode("200");
				page.setMsg("发布成功");
			} else {
				page.setCode("400");
				page.setMsg("发布失败,发生错误");
			}
			sqlSession.commit();
			return page;
		} catch (Exception e){
			sqlSession.rollback();
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/discuss/comment/insert", produces = "application/json;charset=utf-8")
	public @ResponseBody Page insertComment(@RequestParam String userId, @RequestParam String content, @RequestParam Long eventId) {
		try {
			DiscussCommentDao discussCommentDao = sqlSession.getMapper(DiscussCommentDao.class);
			DiscussComment discussComment = new DiscussComment();
			discussComment.setEventId(eventId);
			discussComment.setContent(new String(content.getBytes("ISO-8859-1"), "UTF-8"));
			discussComment.setUserId(userId);
			discussComment.setCreateTime(new Date());
			int result = discussCommentDao.insert(discussComment);
			Page page = new Page();
			if (result == 1) {
				page.setCode("200");
				page.setMsg("发布成功");
			} else {
				page.setCode("400");
				page.setMsg("发布失败,发生错误");
			}
			sqlSession.commit();
			return page;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/discuss/comment/delete", produces = "application/json;charset=utf-8")
	public @ResponseBody Page deleteComment(@RequestParam Long id) {
		try {
			DiscussCommentDao discussCommentDao = sqlSession.getMapper(DiscussCommentDao.class);
			int result = discussCommentDao.deleteById(id);
			Page page = new Page();
			if (result == 1) {
				page.setCode("200");
				page.setMsg("删除成功");
			} else {
				page.setCode("400");
				page.setMsg("删除失败,评论不存在或发生错误");
			}
			sqlSession.commit();
			return page;
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return null;
	}
}
