package org.projectrainbow.commands;
import PluginReference.ChatColor;import PluginReference.MC_Command;import PluginReference.MC_Player;import net.minecraft.command.CommandBase;import org.projectrainbow.ServerWrapper;import org.projectrainbow._DiwUtils;import org.projectrainbow._EconomyManager;import org.projectrainbow._UUIDMapper;import java.util.Iterator;import java.util.List;import java.util.UUID;public class _CmdEcon implements MC_Command{
public static String permKey = "rainbow.econ";
public String getCommandName(){
return "econ";
}@Overridepublic List<String> getAliases(){
return null;
}@Overridepublic String getHelpLine(MC_Player plr){
return ChatColor.LIGHT_PURPLE + "/econ" + ChatColor.WHITE
+ " --- Manage Economy";
}@Overridepublic void handleCommand(MC_Player plr, String[] args){
String exactName;
Double amt;if (args.length == 1 && args[0].equalsIgnoreCase("prices")){
_DiwUtils.reply(plr, "Sending to console...");
int var10 = 0;
_DiwUtils.ConsoleMsg("------------------------------------");
Iterator var11 = _EconomyManager.itemWorth.keySet().iterator();
while (var11.hasNext()){
exactName = (String) var11.next();
++var10;amt = (Double) _EconomyManager.itemWorth.get(exactName);
_DiwUtils.ConsoleMsg(
String.format("%8.2f %s", amt, exactName));
}_DiwUtils.ConsoleMsg("------------------------------------");
_DiwUtils.ConsoleMsg(
String.format("Listed %d items.",
Integer.valueOf(var10)));
}else{String tgtName;
String strAmt;
if (args.length == 3 && args[0].equalsIgnoreCase("set")){
tgtName = args[1];
exactName = ServerWrapper.getInstance().getPlayerExactName(tgtName);
if (exactName == null){
_DiwUtils.reply(plr,
ChatColor.RED + "No player found named: "
+ ChatColor.YELLOW + tgtName);
}else{strAmt = args[2];
amt = 0.0D;try{amt = Double.parseDouble(strAmt);
}catch (Exception var8){_DiwUtils.reply(plr,
ChatColor.RED + "Amount is invalid: "
+ ChatColor.WHITE + strAmt);
return;}UUID uuid = _UUIDMapper.getUUID(tgtName);
if (uuid == null){
_DiwUtils.reply(plr, ChatColor.RED + "Unknown player: " + ChatColor.WHITE + tgtName);
return;}_EconomyManager.SetBalance(uuid, amt);
_EconomyManager.ShowBalanceOf(plr, exactName);
}}else if (args.length == 3 && args[0].equalsIgnoreCase("add")){tgtName = args[1];
exactName = ServerWrapper.getInstance().getPlayerExactName(tgtName);
if (exactName == null){
_DiwUtils.reply(plr,
ChatColor.RED + "No player found named: "
+ ChatColor.YELLOW + tgtName);
}else{strAmt = args[2];
amt = 0.0D;try{amt = Double.parseDouble(strAmt);
}catch (Exception var9){_DiwUtils.reply(plr,
ChatColor.RED + "Amount is invalid: "
+ ChatColor.WHITE + strAmt);
return;}UUID uuid = _UUIDMapper.getUUID(tgtName);
if (uuid == null){
_DiwUtils.reply(plr, ChatColor.RED + "Unknown player: " + ChatColor.WHITE + tgtName);
return;}_EconomyManager.Deposit(uuid, amt);
_EconomyManager.ShowBalanceOf(plr, exactName);
}}else{_DiwUtils.reply(plr,
ChatColor.RED + "Usage: /econ set|add PlayerName Amount");
}}}@Overridepublic boolean hasPermissionToUse(MC_Player plr){
return plr == null || plr.hasPermission(permKey);
}@Overridepublic List<String> getTabCompletionList(MC_Player plr, String[] args){
return args.length >= 1
? CommandBase.getListOfStringsMatchingLastWord(args,
_DiwUtils.getMinecraftServer().getAllUsernames())
: null;}}