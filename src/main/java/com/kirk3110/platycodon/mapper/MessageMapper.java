package com.kirk3110.platycodon.mapper;

import com.kirk3110.platycodon.model.Message;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MessageMapper {
    @Select("SELECT * FROM message WHERE room_id = #{roomId} ORDER BY sent_at DESC LIMIT #{limit}")
    List<Message> findBy(Integer roomId, Integer limit);

    @Insert("INSERT INTO message (name, statement, sent_at, color, room_id) VALUES (#{message.name}, #{message.statement}, #{message.sentAt}, #{message.color}, #{roomId})")
    void save(Message message, Integer roomId);
}
