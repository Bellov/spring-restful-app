package com.wow.restspringapp.Repository;

import com.wow.restspringapp.Model.Guild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuildRepository extends JpaRepository<Guild, Long> {

}
