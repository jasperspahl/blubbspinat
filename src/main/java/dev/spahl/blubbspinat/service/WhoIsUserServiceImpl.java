package dev.spahl.blubbspinat.service;

import dev.spahl.blubbspinat.dao.WhoIsUserRepository;
import dev.spahl.blubbspinat.models.WhoIsUser;
import discord4j.core.object.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class WhoIsUserServiceImpl implements WhoIsUserService {
    @Autowired
    WhoIsUserRepository repository;

    @Override
    public WhoIsUser getUserByDiscordIdAndGuildId(long discordId, long guildId) {
        WhoIsUser user = new WhoIsUser();
        user.setDiscordId(discordId);
        user.setGuildId(guildId);
        Example<WhoIsUser> example = Example.of(user);
        return repository.findOne(example).orElse(null);
    }

    @Override
    public WhoIsUser editUser(User user, long guildId, String text) {
        WhoIsUser whoIsUser = repository.findByDiscordId(user.getId().asLong());
        if (whoIsUser == null) {
            whoIsUser = new WhoIsUser();
        }
        whoIsUser.setDiscordId(user.getId().asLong());
        whoIsUser.setUsername(user.getUsername());
        whoIsUser.setGuildId(guildId);
        whoIsUser.setDescription(text);
        return repository.save(whoIsUser);
    }

}
