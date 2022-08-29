package com.jsaperr.bacapMod.util;

import com.jsaperr.bacapMod.commands.LinkCommand;
import com.jsaperr.bacapMod.commands.UpdateCommand;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ModRegistries {

    public static void registerStuff(){
        registerCommands();
    }

    private static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(UpdateCommand::register);
        CommandRegistrationCallback.EVENT.register(LinkCommand::register);
    }

}
