package com.passer.api.dao;

import com.passer.api.model.DiscussComment;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @Author: passer
 * @Date: 19-5-28 下午9:58
 * @Version 1.0
 */
public class DiscussCommentDaoTest extends BaseDaoTest {
    @Test
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();
        try {
            DiscussCommentDao DiscussCommentDao = sqlSession.getMapper(DiscussCommentDao.class);
            DiscussComment DiscussComment = new DiscussComment();
            DiscussComment.setUserId("12121112");
            DiscussComment.setEventId(3L);
            DiscussComment.setContent("I am also a test");
            DiscussComment.setCreateTime(new Date());

            int result = DiscussCommentDao.insert(DiscussComment);
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
            DiscussCommentDao DiscussCommentDao = sqlSession.getMapper(DiscussCommentDao.class);
            DiscussComment DiscussComment = DiscussCommentDao.selectById(2L);
            Assert.assertNotNull(DiscussComment);
            Assert.assertTrue(DiscussComment.getId() != null);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByDiscussEventId() {
        SqlSession sqlSession = getSqlSession();
        try {
            DiscussCommentDao DiscussCommentDao = sqlSession.getMapper(DiscussCommentDao.class);
            List<DiscussComment> DiscussCommentList = DiscussCommentDao.selectByDiscussEventId(3L);
            Assert.assertNotNull(DiscussCommentList);
            Assert.assertTrue(DiscussCommentList.size() == 2);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = getSqlSession();
        try {
            DiscussCommentDao DiscussCommentDao = sqlSession.getMapper(DiscussCommentDao.class);
            int result = DiscussCommentDao.deleteById(1L);
            result += DiscussCommentDao.deleteById(2L);
            Assert.assertTrue(result == 2);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

}

