package com.jsaperr.bacapMod.commands;

import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;
import com.github.simplenet.Client;
import com.github.simplenet.Server;
import com.github.simplenet.packet.Packet;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class LinkCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment)  {
        dispatcher.register(CommandManager.literal("bacap").then(CommandManager.literal("link"))
                .executes(UpdateCommand::run));
    }
    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        var client = new Client();
        String playerName = MinecraftClient.getInstance().player.getName().getString();
        client.onConnect(() -> {
            System.out.println(client + " has connected to the server!");

            client.postDisconnect(() -> System.out.println(client + " successfully disconnected from the server!"));
        });

        String randomWord = RandomWordGenerator.getRandomWord();
        client.connect("2601:644:0:a010:ceb:3d5:8627:e3ce",43594);
        Packet.builder().putByte(10).putString(playerName).queueAndFlush();
        Packet.builder().putByte(11).putString(randomWord).queueAndFlush();
        System.out.println(randomWord);


        return 1;
    }

}
