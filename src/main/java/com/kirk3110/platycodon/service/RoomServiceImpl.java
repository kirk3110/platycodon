package com.kirk3110.platycodon.service;

import com.kirk3110.platycodon.mapper.RoomMapper;
import com.kirk3110.platycodon.model.Room;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Value("${platycodon.message.maxRoomNum}")
    private Integer MAX_ROOM_NUM;

    private RoomMapper roomMapper;

    public RoomServiceImpl(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    @Override
    public List<Room> fetchRooms() {
        return roomMapper.findAll(MAX_ROOM_NUM);
    }

    @Override
    public Room selectById(Integer roomId) {
        return roomMapper.findById(roomId);
    }

    @Override
    public void updateLastEnteredAt(Integer roomId) {
        roomMapper.updateLastEnteredAt(roomId, new Date());
    }

}
