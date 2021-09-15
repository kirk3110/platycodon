package com.kirk3110.platycodon.mapper;

import com.kirk3110.platycodon.model.Room;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RoomMapper {
    @Select("SELECT * FROM room WHERE is_archived = false ORDER BY last_entered_at DESC LIMIT #{limit}")
    List<Room> findAll(Integer limit);

    @Select("SELECT * FROM room WHERE room_id = #{roomID}")
    Room findById(Integer roomID);

    @Update("UPDATE room SET last_entered_at = #{lastEnteredAt} WHERE room_id = #{roomId}")
    void updateLastEnteredAt(Integer roomId, Date lastEnteredAt);
}
