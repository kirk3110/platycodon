package com.kirk3110.platycodon.service;

import com.kirk3110.platycodon.model.Room;
import java.util.List;

public interface RoomService {

    List<Room> fetchRooms();

    Room selectById(Integer roomId);

    void updateLastEnteredAt(Integer roomId);
}
