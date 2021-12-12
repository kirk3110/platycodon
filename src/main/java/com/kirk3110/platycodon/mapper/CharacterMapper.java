package com.kirk3110.platycodon.mapper;

import com.kirk3110.platycodon.model.Character;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CharacterMapper {

    void insertCharacter(@Param("character") Character character,
        @Param("params") String characterParams, @Param("roomId") Integer roomId);
}
