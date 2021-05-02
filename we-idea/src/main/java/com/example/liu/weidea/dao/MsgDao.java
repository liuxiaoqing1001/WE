package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Msg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MsgDao {

    /**
     *
     * @return
     */
    @Select("select * from msg where cid=#{cid} order by date asc")
    List<Msg> getAllByCid(Integer cid);

    /**
     * 发送消息
     * @param msgObject
     * @return
     */
    @Insert("insert into msg(cid,userId,content,date) values(#{cid},#{userId},#{content},#{date})")
    Integer addMsg(Msg msgObject);
}
