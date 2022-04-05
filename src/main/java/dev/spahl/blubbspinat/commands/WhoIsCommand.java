package dev.spahl.blubbspinat.commands;

import dev.spahl.blubbspinat.models.WhoIsUser;
import dev.spahl.blubbspinat.service.WhoIsUserService;
import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.entity.PartialMember;
import discord4j.core.object.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class WhoIsCommand implements SlashCommand{
    private final Logger LOG = LoggerFactory.getLogger(WhoIsCommand.class);

    @Autowired
    private WhoIsUserService whoIsUserService;

    @Override
    public String getName() {
        return "whois";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        LOG.info("Request in %s: ".formatted(event.getInteraction().getUserLocale()));
        event.getOptions().forEach(applicationCommandInteractionOption -> LOG.info(applicationCommandInteractionOption.getName()));
        try {
            event.getInteraction().getGuild();
            Snowflake guildSnowflake = event
                    .getInteraction()
                    .getGuildId()
                    .orElse(null);
            if (guildSnowflake == null) {
                throw new Exception("This function has to be used in a Guild");
            }
            User user = event.getOption("user")
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asUser)
                    .orElseThrow(RuntimeException::new)
                    .block();
            if (user == null) {
                LOG.error("Something went wrong while trying to get the users");
                throw new Exception("Internal Server Error");
            }
            WhoIsUser whoIsUser = whoIsUserService.getUserByDiscordIdAndGuildId(
                    user.getId().asLong(),
                    guildSnowflake.asLong()
            );
            if (whoIsUser == null) {
                LOG.info("Some one from %d tried to get information about %s:%d".formatted(guildSnowflake.asLong(), user.getUsername(), user.getId().asLong()));
                throw new Exception("%s not found in database".formatted(user.getMention()));
            }
            return event.reply("%s: %s\n\t%s".formatted(user.getMention(), whoIsUser.getUsername(), whoIsUser.getDescription()));
        }
        catch (Exception exception)
        {
            LOG.error("Encountered error: {}", exception.getMessage());
            return event.reply().withEphemeral(true).withContent(exception.getLocalizedMessage());
        }
    }
}
