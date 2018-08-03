package com.wow.restspringapp.Controller;

import com.sun.xml.internal.ws.handler.HandlerException;
import com.wow.restspringapp.Exception.ExceptionHandler;
import com.wow.restspringapp.Model.Character;
import com.wow.restspringapp.Repository.CharacterRepository;
import com.wow.restspringapp.Repository.GuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private GuildRepository guildRepository;

    @GetMapping("/guilds/{guildId}/characters")
    public List<Character> getCharactersByGuild(@PathVariable Long guildId){
        return characterRepository.findByGuildId(guildId);
    }


    @PostMapping("/invite/{guildId}/character")
    public Character addCharacter (@PathVariable Long guildId,
                                   @Valid @RequestBody Character character){
        return guildRepository.findById(guildId)
                .map(guild -> {
                    character.setGuild(guild);
                    return characterRepository.save(character);
                }).orElseThrow(()->new ExceptionHandler("Cannot invited this character"  + character.getName()));
    }

    @PutMapping("/guilds/{guildId}/characters/{characterId}")
    public Character updateCharacter(@PathVariable Long guildId,
                                     @PathVariable Long characterId,
                                     @Valid @RequestBody Character characterRequest){

        if(!characterRepository.existsById(guildId)){
            throw new ExceptionHandler("Cannot update");
        }
        return characterRepository.findById(characterId)
                .map(character -> {
                    character.setName(characterRequest.getName());
                    character.setRace(characterRequest.getRace());
                    character.setFaction(characterRequest.getFaction());
                    character.setKlass(characterRequest.getKlass());
                    character.setLevel(characterRequest.getLevel());
                    character.setGuild(characterRequest.getGuild());
                return characterRepository.save(character);
                }).orElseThrow(()->new HandlerException(" Cannot update the character " + characterRequest.getName()));
    }

    @DeleteMapping("/guild/{guildId}/character/characterId")
    public ResponseEntity<?> deleteCharterFromGuild(@PathVariable Long guildId,
                                                    @PathVariable Long characterId){

        if(!characterRepository.existsById(guildId)){
            throw new HandlerException(" Cannot remove the player ");
        }

        return characterRepository.findById(characterId)
                .map(character -> {
                    characterRepository.delete(character);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new HandlerException(" Something wrong "));
    }

 }
