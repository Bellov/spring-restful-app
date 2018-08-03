package com.wow.restspringapp.Repository;

import com.wow.restspringapp.Model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByGuildId(Long guildId);
}
