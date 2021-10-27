package net.moonlar.fly.commands;

import net.moonlar.fly.MoonlarFly;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
      sender.sendMessage(ChatColor.RED + "Você não tem permissão para voar.");
      return true;
    }

    if(args.length == 0 && sender instanceof Player) {
      plugin.toggleFly((Player) sender);
      return true;
    }

    if(args.length == 1) {
      if(args[0].equalsIgnoreCase("reload")) {
        if(!sender.hasPermission("moonlar.fly.admin")) {
          sender.sendMessage(ChatColor.RED + "Você não está autorizado.");
          return true;
        }

        plugin.reload();
        sender.sendMessage(ChatColor.RED + "Configurações recarregadas!");
        return true;
      }

      if(!plugin.hasPermission(sender, "moonlar.fly.other")) {
        sender.sendMessage(ChatColor.RED + "você não pode alterar o fly de outro jogador!");
        return true;
      }

      Player player = Bukkit.getPlayer(args[0]);

      if(player == null) {
        sender.sendMessage(ChatColor.RED + "Jogador não encontrado!");
        return true;
      }

      if(!plugin.toggleFly(player, sender)) {
        sender.sendMessage(ChatColor.RED + "O jogador não pode voar onde ele se encontra!");
        return true;
      }

      if(player.getAllowFlight()) {
        sender.sendMessage(ChatColor.GREEN + "Modo fly ativado para " + player.getName() + "!");
        return true;
      }

      sender.sendMessage(ChatColor.RED + "Modo fly desativado para " + player.getName() + "!");
      return true;
    }

    sender.sendMessage(ChatColor.RED + "Utilize: /fly <nickname>");
    return true;
  }
}
