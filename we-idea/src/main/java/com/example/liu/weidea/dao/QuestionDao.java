package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Question;
import com.example.liu.weidea.entity.Test;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionDao {

    /**
     * 随机获取10题
     * @return
     */
    @Select("select * from question order by RAND() limit 10")
    List<Question> getRand();

    /**
     * 获得题详情
     * @param qid
     * @return
     */
    @Select("select * from question where qid=#{qid}")
    Question getByQId(Integer qid);

    /**
     * 删除题
     * @param qid
     * @return
     */
    @Delete("delete from question where qid=#{qid}")
    int deleteQuestion(Integer qid);

    /**
     * 添加题
     * @param question
     * @return
     */
    @Insert("insert into question(title,item,date) values(#{title},#{item},now())")
    Integer addQuestion(Question question);

    /**
     * 获得所有题
     * @return
     */
    @Select("select * from question")
    List<Question> getAll();
}
