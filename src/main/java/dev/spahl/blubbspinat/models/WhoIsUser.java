package dev.spahl.blubbspinat.models;

import javax.persistence.*;

@Entity(name = "WhoIsUser")
public class WhoIsUser {

    @Id
    @SequenceGenerator(
            name = "who_is_user_seq",
            sequenceName = "who_is_user_seq",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "who_is_user_seq"
    )
    @Column(updatable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private Long discordId;
    private String username;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private Long guildId;

    public WhoIsUser() {
    }

    public WhoIsUser(Long id, Long discordId, String username, String description) {
       this.id = id;
       this.discordId = discordId;
       this.username = username;
       this.description = description;
    }

    public Long getId() {
        return id;
    }
    public Long getDiscordId() {
        return discordId;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDiscordId(Long discordId) {
        this.discordId = discordId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "WhoIsUser{" +
                "id=" + id +
                ", discordId=" + discordId +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", guildId='" + guildId + '\'' +
                '}';
    }

    public Long getGuildId() {
        return guildId;
    }

    public void setGuildId(Long guildId) {
        this.guildId = guildId;
    }
}
