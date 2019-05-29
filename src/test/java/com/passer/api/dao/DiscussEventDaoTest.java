package com.passer.api.dao;

import com.passer.api.model.DiscussEvent;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @Author: passer
 * @Date: 19-5-28 下午9:39
 * @Version 1.0
 */
public class DiscussEventDaoTest extends BaseDaoTest {

    @Test
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();
        try {
            DiscussEventDao discussEventDao = sqlSession.getMapper(DiscussEventDao.class);
            DiscussEvent discussEvent = new DiscussEvent();
            discussEvent.setUserId("12121112");
            discussEvent.setContent("no");
            discussEvent.setTitle("go");
            discussEvent.setCreateTime(new Date());

            int result = discussEventDao.insert(discussEvent);
            Assert.assertTrue(result == 1);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = getSqlSession();
        try {
            DiscussEventDao discussEventDao = sqlSession.getMapper(DiscussEventDao.class);
            DiscussEvent discussEvent = discussEventDao.selectById(2L);
            Assert.assertNotNull(discussEvent);
            Assert.assertTrue(discussEvent.getId() != null);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            DiscussEventDao discussEventDao = sqlSession.getMapper(DiscussEventDao.class);
            List<DiscussEvent> discussEventList = discussEventDao.selectAll();
            Assert.assertNotNull(discussEventList);
            Assert.assertTrue(discussEventList.size() == 2);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = getSqlSession();
        try {
            DiscussEventDao discussEventDao = sqlSession.getMapper(DiscussEventDao.class);
            int result = discussEventDao.deleteById(3L);
            result += discussEventDao.deleteById(4L);
            Assert.assertTrue(result == 2);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

}
