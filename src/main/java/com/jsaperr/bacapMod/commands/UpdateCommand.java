package com.jsaperr.bacapMod.commands;

import com.github.simplenet.Client;
import com.github.simplenet.packet.Packet;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class UpdateCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment)  {
        dispatcher.register(CommandManager.literal("update")
                .executes(UpdateCommand::run));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Scoreboard scoreboard = MinecraftClient.getInstance().getServer().getScoreboard();
        String playerName = MinecraftClient.getInstance().player.getName().getString();

        int scoreboardPlayerScore = scoreboard.getPlayerScore(playerName, scoreboard.getObjective("deaths")).getScore();


        var client = new Client();
        client.onConnect(() -> {
            System.out.println(client + " has connected to the server!");

            // Builds a packet and sends it to the server immediately.
            Packet.builder().putByte(1).putInt(scoreboardPlayerScore).queueAndFlush(client);
            Packet.builder().putByte(2).putString(playerName).queueAndFlush(client);

            // Register a pre-disconnection listener.
            client.preDisconnect(() -> System.out.println(client + " is about to disconnect from the server!"));

// Register a post-disconnection listener.
            client.postDisconnect(() -> System.out.println(client + " successfully disconnected from the server!"));



        });
        // Attempt to connect to a server AFTER registering listeners.
        client.connect("2601:644:0:a010:5876:5cd3:1401:c4cc", 43594);


        return 1;
    }
}
