package com.kirk3110.platycodon.mapper;

import com.kirk3110.platycodon.model.Message;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MessageMapper {
    @Select("SELECT * FROM message")
    List<Message> findAll();

    @Insert("INSERT INTO message (name, statement, created_at) VALUES (#{name}, #{statement}, current_timestamp)")
    void save(Message message);
}
