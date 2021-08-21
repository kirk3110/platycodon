package com.kirk3110.platycodon.controller.helper;

import com.kirk3110.platycodon.controller.props.TopProps;
import com.kirk3110.platycodon.model.Room;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TopHelper {

    public TopProps makeTopProps(List<Room> roomList) {
        return TopProps.builder()
            .roomList(roomList)
            .build();
    }
}
