package net.moonlar.fly;

import net.moonlar.fly.commands.FlyCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class MoonlarFly extends JavaPlugin {

  private List<String> enabledWorlds;

  @Override
  public void onEnable() {
    getCommand("fly").setExecutor(new FlyCommand(this));
    reload();
  }

  @Override
  public void onDisable() {

  }

  public boolean checkLocation(Player player, CommandSender cause) {
    return enabledWorlds.contains(player.getWorld().getName())
      || hasPermission(player, "moonlar.fly.anywhere")
      || cause.hasPermission("moonlar.fly.admin");
  }

  public boolean toggleFly(Player player) {
    return toggleFly(player, player);
  }

  public boolean toggleFly(Player player, CommandSender cause) {
    if(!checkLocation(player, cause)) return false;

    if(player.getAllowFlight()) {
      player.setAllowFlight(false);
      player.sendMessage("&cModo fly desativado!");
    }
    else {
      player.setAllowFlight(true);
      player.sendMessage("&aModo fly ativado!");
    }

    return true;
  }

  public boolean hasPermission(CommandSender sender, String permission) {
    return sender.hasPermission("moonlar.fly.admin") || sender.hasPermission(permission);
  }

  public void reload() {
    saveDefaultConfig();
    reloadConfig();
    enabledWorlds = getConfig().getStringList("EnabledWorlds");
  }
}
