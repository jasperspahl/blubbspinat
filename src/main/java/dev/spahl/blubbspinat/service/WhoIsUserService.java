package dev.spahl.blubbspinat.service;

import dev.spahl.blubbspinat.dao.WhoIsUserRepository;
import dev.spahl.blubbspinat.models.WhoIsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class WhoIsUserService {
    @Autowired
    WhoIsUserRepository repository;

    public WhoIsUser getUserByDiscordIdAndGuildId(long discordId, long guildId) {
        WhoIsUser user = new WhoIsUser();
        user.setDiscordId(discordId);
        user.setGuildId(guildId);
        Example<WhoIsUser> example = Example.of(user);
        return repository.findOne(example).orElse(null);
    }
}
