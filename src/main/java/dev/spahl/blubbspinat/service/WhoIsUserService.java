package dev.spahl.blubbspinat.service;

import dev.spahl.blubbspinat.models.WhoIsUser;
import discord4j.core.object.entity.User;

public interface WhoIsUserService {
    WhoIsUser getUserByDiscordIdAndGuildId(long discordId, long guildId);
    WhoIsUser editUser(User user, long guildId, String text);
}
