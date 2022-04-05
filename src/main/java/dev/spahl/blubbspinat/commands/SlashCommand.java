package dev.spahl.blubbspinat.commands;

import dev.spahl.blubbspinat.listener.EventListener;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public interface SlashCommand {
    String getName();
    Mono<Void> handle(ChatInputInteractionEvent event);
}
