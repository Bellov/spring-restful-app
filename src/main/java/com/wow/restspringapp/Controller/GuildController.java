package com.wow.restspringapp.Controller;

import com.wow.restspringapp.Exception.ExceptionHandler;
import com.wow.restspringapp.Model.Guild;
import com.wow.restspringapp.Repository.GuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GuildController {

    @Autowired
    private GuildRepository guildRepository;

    @GetMapping("/")
    public Page<Guild> getAllGuilds (Pageable pageable){
        return guildRepository.findAll(pageable);
    }

    @PostMapping("/create")
    public Guild createGuild(@Valid @RequestBody Guild guild){
        return guildRepository.save(guild);
    }

    @PutMapping("/guild/{guildId}")
    public Guild updateGuild(@PathVariable Long guildId,
                             @Valid @RequestBody Guild guildRequest){
        return guildRepository.findById(guildId)
                .map(guild -> {
                    guild.setName(guildRequest.getName());
                    guild.setDescription(guildRequest.getDescription());
                    guild.setRealm(guildRequest.getRealm());
                    return guildRepository.save(guild);
                }).orElseThrow(()->new ExceptionHandler(" The guild " + guildRequest.getName() + " cannot updated "));
    }


    @DeleteMapping("/remove-guild/{guildId}")
    public ResponseEntity<?> destroyGuild(@PathVariable Long guildId){
        return guildRepository.findById(guildId)
                .map(guild -> {
                   guildRepository.delete(guild);
                   return ResponseEntity.ok().build();
                }).orElseThrow(()->new ExceptionHandler(" Guild cannot will be destroyed"));
    }
}
