package dev.spahl.blubbspinat.listener;

import dev.spahl.blubbspinat.commands.SlashCommand;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

@Service
public class SlashCommandListener  implements EventListener<ChatInputInteractionEvent> {
    private final Collection<SlashCommand> commands;

    public SlashCommandListener(List<SlashCommand> slashCommands) {
        this.commands = slashCommands;
    }

    @Override
    public Class<ChatInputInteractionEvent> getEventType() {
        return ChatInputInteractionEvent.class;
    }

    @Override
    public Mono<Void> execute(ChatInputInteractionEvent event) {
        return Flux.fromIterable(commands)
                .filter(commands -> commands.getName().equals(event.getCommandName()))
                .next()
                .flatMap(command -> command.handle(event));
    }
}
