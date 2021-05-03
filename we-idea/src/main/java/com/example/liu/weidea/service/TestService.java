package com.example.liu.weidea.service;

import com.example.liu.weidea.entity.Question;
import com.example.liu.weidea.entity.Test;

import java.util.List;

public interface TestService {

    /**
     * 随机获取10题
     * @return
     */
    List<Question> getRand();

    /**
     *检测是否曾做过基础测试
     * @param uid
     * @return
     */
    Test getByUid(Integer uid);

    /**
     * 匹配咨询师
     * @param uid
     * @return
     */
    Integer addConsultants(Integer uid);

    /**
     * 保存测试
     * @param test
     * @return
     */
    Integer addTest(Test test);

    /**
     * 根据id获取测试情况
     * @param id
     * @return
     */
    List<Question> getById(Integer id);

    /**
     * 获得题详情
     * @param qid
     * @return
     */
    Question getQuestionByQid(Integer qid);

    /**
     * 删除题
     * @param qid
     * @return
     */
    int deleteQuestion(Integer qid);

    /**
     * 添加题
     * @param question
     * @return
     */
    Integer addQuestion(Question question);

    /**
     * 获得所有题
     * @return
     */
    List<Question> getAllQuestion();
}
