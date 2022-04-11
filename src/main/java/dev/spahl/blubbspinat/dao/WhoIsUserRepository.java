package dev.spahl.blubbspinat.dao;

import dev.spahl.blubbspinat.models.WhoIsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WhoIsUserRepository extends JpaRepository<WhoIsUser, Long> {
    WhoIsUser findByDiscordId(Long discordId);
}