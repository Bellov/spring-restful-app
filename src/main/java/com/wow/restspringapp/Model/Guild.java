package com.wow.restspringapp.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "guilds")
public class Guild  extends  AuditModel{

    @Id
    @GeneratedValue(generator = "guild_generator")
    @SequenceGenerator(
            name = "guild_generator",
            sequenceName = "guild_sequence",
            initialValue = 1
    )
    private Long id;

    @NotBlank
    @Size(min = 5, max = 15)
    @Column(columnDefinition = "text")
    private String name;

    @Size(max = 100)
    @Column(columnDefinition = "text")
    private String description;

    @NotBlank
    @Column(columnDefinition = "text")
    private String realm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }
}
