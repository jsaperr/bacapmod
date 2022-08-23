package com.jsaperr.bacapMod;

import com.jsaperr.bacapMod.util.ModRegistries;
import net.fabricmc.api.ModInitializer;
import org.apache.commons.logging.Log;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class bacapMod implements ModInitializer {
    public static final String MOD_ID = "bacapMod";
    public static final Logger LOGGER = LogManager.getLogManager().getLogger(MOD_ID);


    @Override
    public void onInitialize() {

        ModRegistries.registerStuff();

    }
}
