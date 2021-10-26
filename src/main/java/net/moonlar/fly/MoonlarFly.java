package net.moonlar.fly;

import net.moonlar.fly.commands.FlyCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class MoonlarFly extends JavaPlugin {

  @Override
  public void onEnable() {
    getCommand("fly").setExecutor(new FlyCommand(this));
  }

  @Override
  public void onDisable() {

  }

  public void toggleFly(Player player) {
    if(player.getAllowFlight()) {
      player.setAllowFlight(false);
      player.sendMessage("&cModo fly desativado!");
    }
    else {
      player.setAllowFlight(true);
      player.sendMessage("&aModo fly ativado!");
    }
  }

  public boolean hasPermission(CommandSender sender, String permission) {
    return sender.hasPermission("moonlar.fly.admin") || sender.hasPermission(permission);
  }
}
