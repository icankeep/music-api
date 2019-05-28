package com.passer.api.dao;

import com.passer.api.model.Profile;
import com.passer.api.model.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


/**
 * @Author: passer
 * @Date: 19-5-27 下午3:51
 * @Version 1.0
 */
public class UserDaoTest extends BaseDaoTest{
//    @Test
//    public void testUserInsert() {
//        SqlSession sqlSession = getSqlSession();
//        try {
//            UserDao userDao = sqlSession.getMapper(UserDao.class);
//            User user = new User();
//            Profile profile = new Profile();
//            profile.setCity("12354");
//            profile.setUserId("11212");
//            profile.setBirthday("5155");
//            user.setPeopleCanSeeMyPlayRecord(false);
//            user.setLevel("8");
//            user.setProfile(profile);
//            int result = userDao.insert(user);
//            Assert.assertEquals(1, result);
//        } finally {
//            sqlSession.rollback();
//            sqlSession.close();
//        }
//    }
//
//    @Test
//    public void testUserUpdate() {
//        SqlSession sqlSession = getSqlSession();
//        try {
//            UserDao userDao = sqlSession.getMapper(UserDao.class);
//            User user = new User();
//            Profile profile = new Profile();
//            profile.setCity("1234");
//            profile.setUserId("12121212");
//            profile.setBirthday("54321");
//            user.setPeopleCanSeeMyPlayRecord(true);
//            user.setLevel("8");
//            user.setProfile(profile);
//            int result = userDao.update(user);
//            Assert.assertEquals(1, result);
//        } finally {
//            sqlSession.rollback();
//            sqlSession.close();
//        }
//    }
//
//    @Test
//    public void testSelectById() {
//        SqlSession sqlSession = getSqlSession();
//        try {
//            UserDao userDao = sqlSession.getMapper(UserDao.class);
//            User user = userDao.selectById("12121212");
//
//            Assert.assertNotNull(user);
//            Assert.assertEquals("12121212", user.getProfile().getUserId());
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    @Test
//    public void testSelectAll() {
//        SqlSession sqlSession = getSqlSession();
//        try {
//            UserDao userDao = sqlSession.getMapper(UserDao.class);
//            List<User> userList = userDao.selectAll();
//
//            Assert.assertNotNull(userList);
//            Assert.assertEquals(2, userList.size());
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    @Test
//    public void testDeleteById() {
//        SqlSession sqlSession = getSqlSession();
//        try {
//            UserDao userDao = sqlSession.getMapper(UserDao.class);
//            int result = userDao.deleteById("12121212");
//            Assert.assertEquals(1, result);
//
//            result = userDao.deleteById("11212");
//            Assert.assertEquals(1, result);
//        } finally {
//            sqlSession.commit();
//            sqlSession.close();
//        }
//    }
}
