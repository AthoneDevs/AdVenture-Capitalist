package es.projectalpha.ac.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.projectalpha.ac.cmd.sub.NormalCommand;
import es.projectalpha.ac.cmd.sub.admin.DebugCommand;
import es.projectalpha.ac.cmd.sub.player.GUIsCommand;
import es.projectalpha.ac.cmd.sub.player.PlayCommand;

public class AVCCmd implements CommandExecutor {

	//Sub-Commands
	private DebugCommand debug = new DebugCommand();
	private GUIsCommand gui = new GUIsCommand();
	private PlayCommand play = new PlayCommand();
	private NormalCommand normal = new NormalCommand();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p;
		if(sender instanceof Player){
			p = (Player) sender;
		}else{
			sender.sendMessage("This command only can be executed by player");
			return true;
		}

		if(cmd.getName().equalsIgnoreCase("avc")){
			if(args.length == 0){
				normal.executeNormalCommand(p, args);
			}

			if(args.length == 1){
				if(args[0].equalsIgnoreCase("admin")){
					normal.executeAdminCommand(p, args);
				}
				if(args[0].equalsIgnoreCase("achievements")){
					gui.executeAchiCommand(p, args);
				}
				if(args[0].equalsIgnoreCase("reset")){
					gui.executeResetCommand(p, args);
				}
				if(args[0].equalsIgnoreCase("angels")){
					gui.executeAngelsCommand(p, args);
				}
				if(args[0].equalsIgnoreCase("managers")){
					gui.executeManagersCommand(p, args);
				}
				if(args[0].equalsIgnoreCase("debug")){
					debug.executeDebugCommand(p, args);
				}
				if(args[0].equalsIgnoreCase("play")){
					play.executePlayCommand(p, args);
				}

				if(args.length == 2){
					if(args[0].equalsIgnoreCase("shops")){
						gui.executeShopsCommand(p, args);
					}
				}
			}
		}
		return false;
	}
}
