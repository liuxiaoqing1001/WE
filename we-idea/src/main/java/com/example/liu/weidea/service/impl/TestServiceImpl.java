package com.example.liu.weidea.service.impl;

import com.example.liu.weidea.dao.ConsultantsDao;
import com.example.liu.weidea.dao.QuestionDao;
import com.example.liu.weidea.dao.TestDao;
import com.example.liu.weidea.dao.UserDao;
import com.example.liu.weidea.entity.Question;
import com.example.liu.weidea.entity.Test;
import com.example.liu.weidea.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestDao testDao;

    @Autowired
    QuestionDao questionDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ConsultantsDao consultantsDao;

    /**
     * 随机获取10题
     * @return
     */
    @Override
    public List<Question> getRand() {
        return questionDao.getRand();
    }

    /**
     * 检测是否曾做过基础测试
     * @param uid
     * @return
     */
    @Override
    public Test getByUid(Integer uid) {
        return testDao.getByUid(uid);
    }

    /**
     * 匹配咨询师
     * @param uid
     * @return
     */
    @Override
    public Integer addConsultants(Integer uid) {
        Integer vid =userDao.getRandV();
        consultantsDao.addConsultants(vid,uid);
        return consultantsDao.last_insert_id();
    }

    /**
     * 保存测试
     * @param test
     * @return
     */
    @Override
    public Integer addTest(Test test) {
        return testDao.addTest(test);
    }

    /**
     * 根据id获取测试情况
     * @param id
     * @return
     */
    @Override
    public List<Question> getById(Integer id) {
        Test test = testDao.getById(id);
        List<String> strList = Arrays.asList(test.getQIds().split(","));
        List<Question> questionList = new ArrayList<>();
        for (int i=0;i<strList.size();i++){
            Question question = questionDao.getByQId(Integer.parseInt(strList.get(i)));
            questionList.add(question);
        }
        return questionList;
    }

    /**
     * 获得题详情
     * @param qid
     * @return
     */
    @Override
    public Question getQuestionByQid(Integer qid) {
        return questionDao.getByQId(qid);
    }

    /**
     * 删除题
     * @param qid
     * @return
     */
    @Override
    public int deleteQuestion(Integer qid) {
        return questionDao.deleteQuestion(qid);
    }

    /**
     * 添加题
     * @param question
     * @return
     */
    @Override
    public Integer addQuestion(Question question) {
        return questionDao.addQuestion(question);
    }

    /**
     * 获得所有题
     * @return
     */
    @Override
    public List<Question> getAllQuestion() {
        return questionDao.getAll();
    }

}
