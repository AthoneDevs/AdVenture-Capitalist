package es.projectalpha.ac.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.projectalpha.ac.cmd.sub.NormalCommand;
import es.projectalpha.ac.cmd.sub.admin.AchiCommand;
import es.projectalpha.ac.cmd.sub.admin.AngelsCommand;
import es.projectalpha.ac.cmd.sub.admin.DebugCommand;
import es.projectalpha.ac.cmd.sub.admin.ManagersCommand;
import es.projectalpha.ac.cmd.sub.admin.ModifiersCommand;
import es.projectalpha.ac.cmd.sub.admin.MoneyCommand;
import es.projectalpha.ac.cmd.sub.admin.ShopsCommand;
import es.projectalpha.ac.cmd.sub.player.GUIsCommand;
import es.projectalpha.ac.cmd.sub.player.PlayCommand;
import es.projectalpha.ac.cmd.sub.player.TopCommand;

public class AVCCmd implements CommandExecutor {

	//Sub-Commands Info
	private NormalCommand normal = new NormalCommand();

	//Sub-Commands Normal
	private GUIsCommand gui = new GUIsCommand();
	private PlayCommand play = new PlayCommand();
	private TopCommand top = new TopCommand();

	//Sub-Commands Admin
	private DebugCommand debug = new DebugCommand();
	private ModifiersCommand mod = new ModifiersCommand();
	private MoneyCommand mc = new MoneyCommand();
	private AchiCommand ac = new AchiCommand();
	private ShopsCommand sc = new ShopsCommand();
	private ManagersCommand mac = new ManagersCommand();
	private AngelsCommand anc = new AngelsCommand();

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
				if(args[0].equalsIgnoreCase("top")){
					top.executeTopCommand(p, args);
				}

				if(args.length == 2){
					if(args[0].equalsIgnoreCase("shops")){
						gui.executeShopsCommand(p, args);
					}
				}

				if(args.length == 3){
					if(args[0].equalsIgnoreCase("modifiers")){
						mod.executeModifiersCommand(p, args);
					}
					if(args[0].equalsIgnoreCase("money") && args[1].equalsIgnoreCase("get")){
						mc.executeGetMoneyCommand(p, args);
					}
					if(args[0].equalsIgnoreCase("achievements") && args[1].equalsIgnoreCase("get")){
						ac.executeGetAchiCommand(p, args);
					}
					if(args[0].equalsIgnoreCase("shops") && args[1].equalsIgnoreCase("get")){
						sc.executeGetShopsCommand(p, args);
					}
					if(args[0].equalsIgnoreCase("managers") && args[1].equalsIgnoreCase("get")){
						mac.executeGetManagersCommand(p, args);
					}
					if(args[0].equalsIgnoreCase("angels") && args[1].equalsIgnoreCase("get")){
						anc.executeGetAngelsCommand(p, args);
					}
				}

				if(args.length == 4){
					//Money
					if(args[0].equalsIgnoreCase("money") && args[1].equalsIgnoreCase("add")){
						mc.executeAddMoneyCommand(p, args);
					}
					if(args[0].equalsIgnoreCase("money") && args[1].equalsIgnoreCase("remove")){
						mc.executeRemoveMoneyCommand(p, args);
					}
					//Achievements
					if(args[0].equalsIgnoreCase("achievements") && args[1].equalsIgnoreCase("add")){
						ac.executeAddAchiCommand(p, args);
					}
					if(args[0].equalsIgnoreCase("achievements") && args[1].equalsIgnoreCase("remove")){
						ac.executeAddAchiCommand(p, args);
					}
					//Shops
					if(args[0].equalsIgnoreCase("achievements") && args[1].equalsIgnoreCase("add")){
						sc.executeAddShopsCommand(p, args);
					}
					if(args[0].equalsIgnoreCase("achievements") && args[1].equalsIgnoreCase("remove")){
						sc.executeRemoveShopsCommand(p, args);
					}
					//Managers
					if(args[0].equalsIgnoreCase("managers") && args[1].equalsIgnoreCase("add")){
						mac.executeAddManagersCommand(p, args);
					}
					if(args[0].equalsIgnoreCase("managers") && args[1].equalsIgnoreCase("remove")){
						mac.executeRemoveManagersCommand(p, args);
					}
					//Angels
					if(args[0].equalsIgnoreCase("angels") && args[1].equalsIgnoreCase("add")){
						anc.executeAddAngelsCommand(p, args);
					}
					if(args[0].equalsIgnoreCase("angels") && args[1].equalsIgnoreCase("remove")){
						anc.executeRemoveAngelsCommand(p, args);
					}
				}
			}
		}
		return false;
	}
}
