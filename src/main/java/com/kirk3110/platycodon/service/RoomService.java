package com.kirk3110.platycodon.service;

import com.kirk3110.platycodon.model.Room;
import java.util.List;

public interface RoomService {

    List<Room> fetchRooms();

    void updateLastEnteredAt(Integer roomId);
}
