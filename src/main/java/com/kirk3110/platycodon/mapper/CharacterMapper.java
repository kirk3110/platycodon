package com.kirk3110.platycodon.mapper;

import com.kirk3110.platycodon.model.Character;
import com.kirk3110.platycodon.repository.result.SelectCharacterResult;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CharacterMapper {

    void insertCharacter(@Param("character") Character character,
        @Param("params") String characterParams, @Param("roomId") Integer roomId);

    List<SelectCharacterResult> selectByRoomId(Integer roomId, Integer limit);
}
