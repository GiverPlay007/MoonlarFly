package net.moonlar.fly.commands;

import net.moonlar.fly.MoonlarFly;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

  private final MoonlarFly plugin;

  public FlyCommand(MoonlarFly plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if(!plugin.hasPermission(sender, "moonlar.fly")) {
      sender.sendMessage("&cVocê não tem permissão para voar.");
      return true;
    }

    if(args.length == 0 && sender instanceof Player) {
      plugin.toggleFly((Player) sender);
      return true;
    }

    if(args.length == 1) {
      if(!plugin.hasPermission(sender, "moonlar.fly.other")) {
        sender.sendMessage("&cvocê não pode alterar o fly de outro jogador!");
        return true;
      }

      Player player = Bukkit.getPlayer(args[0]);

      if(player == null) {
        sender.sendMessage("&cJogador não encontrado!");
        return true;
      }

      if(!plugin.toggleFly(player, sender)) {
        sender.sendMessage("&cO jogador não pode voar onde ele se encontra!");
        return true;
      }

      if(player.getAllowFlight()) {
        sender.sendMessage("&aModo fly ativado para " + player.getName() + "!");
        return true;
      }

      sender.sendMessage("&cModo fly desativado para " + player.getName() + "!");
      return true;
    }

    sender.sendMessage("&cUtilize: /fly <nickname>");
    return true;
  }
}
