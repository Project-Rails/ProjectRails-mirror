package org.projectrails;

import java.util.Arrays;
import java.util.jar.Attributes;

import PluginReference.MC_Player;

public class CmdRails extends RailCommand {
    @Override
    public void handleCommand(MC_Player p, String[] args) {
        if (args.length == 0) {
            Attributes a = Rail_Updater.getManifest(Rail_Updater.class).getMainAttributes();
            String hash = a.getValue("GitCommitHash");
            if (hash.endsWith("-dirty")) hash = hash.replace("-dirty", "");

            sendMessage(p, "ProjectRainbow version b" + Rails.getRainbowVersion());
            sendMessage(p, "ProjectRails version git-Rails-" + hash);
        }
 
        if (args[0].equalsIgnoreCase("updatecheck")) {
            int i = Rail_Updater.check();
            if (i == 0) {
                sendMessage(p, "You are running the latest version.");
                return;
            } else {
                sendMessage(p, "You are running " + i + " versions behind.");
                return;
            }
        }

        if (args[0].equalsIgnoreCase("upstream")) {
            sendMessage(p, "ProjectRainbow b" + Rails.getRainbowVersion());
            int i = Rail_Updater.rainbowCheck();
            if (i == 0) {
                sendMessage(p, "Running the latest upstream build.");
                return;
            } else {
                sendMessage(p, "Running " + i + " builds behind the latest upstream build.");
                return;
            }
        }
    }

    @Override
    public CommandInfo getInfo() {
        CommandInfo info = new CommandInfo();
        info.name = "rails";
        info.aliases = Arrays.asList("projectrails");
        info.usage = "Rails main command.";
        return info;
    }
}