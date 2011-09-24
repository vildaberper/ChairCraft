package com.vildaberper.ChairCraft;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class ChairCraft extends JavaPlugin{
	private final ChairCraftPlayerListener playerListener = new ChairCraftPlayerListener();
	private final ChairCraftBlockListener blockListener = new ChairCraftBlockListener();
	private final ChairCraftEntityListener entityListener = new ChairCraftEntityListener();
	public static Plugin p = null;
	public static boolean sign_check = false;
	public static int max_width = 10, arrow_respawn = 55, arrow_teleport_ticks = 10;
	public static double addition = 0.1;

	@Override
	public void onDisable(){
		for(Seat seat : ChairCraftPlayerListener.seats){
			if(seat != null && seat.getArrow() != null){
				if(seat.getArrow().getPassenger() != null){
					seat.getArrow().getPassenger().teleport(seat.getArrow().getPassenger());
				}
				seat.getArrow().remove();
			}
		}
		ChairCraftPlayerListener.seats.clear();
		getServer().getScheduler().cancelTasks(this);
		System.out.println(getDescription().getName() + " " + getDescription().getVersion() + " is disabled.");
	}

	@Override
	public void onEnable(){
		List<Integer> i = new LinkedList<Integer>();

		i.add(0);
		i.add(6);
		i.add(8);
		i.add(9);
		i.add(10);
		i.add(11);
		i.add(37);
		i.add(38);
		i.add(39);
		i.add(40);
		i.add(50);
		i.add(51);
		i.add(55);
		i.add(59);
		i.add(63);
		i.add(64);
		i.add(65);
		i.add(66);
		i.add(68);
		i.add(69);
		i.add(70);
		i.add(71);
		i.add(72);
		i.add(75);
		i.add(76);
		i.add(77);
		i.add(78);
		ChairCraftPlayerListener.t = i;
		getDataFolder().mkdir();
		if(!new File(getDataFolder(), "ChairCraft.yml").exists()){
			try{
				new File(getDataFolder(), "ChairCraft.yml").createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
		}

		Configuration c = new Configuration(new File(getDataFolder(), "ChairCraft.yml"));

		c.load();
		sign_check = c.getBoolean("Sign_check", sign_check);
		max_width = c.getInt("Max_width", max_width);
		arrow_respawn = c.getInt("Arrow_respawn_time", arrow_respawn);
		arrow_teleport_ticks = c.getInt("Arrow_teleport_ticks", arrow_teleport_ticks);
		c.setProperty("Sign_check", sign_check);
		c.setProperty("Max_width", max_width);
		c.setProperty("Arrow_respawn_time", arrow_respawn);
		c.setProperty("Arrow_teleport_ticks", arrow_teleport_ticks);
		c.save();
		p = this;
		getServer().getPluginManager().registerEvent(Type.ENTITY_EXPLODE, entityListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.BLOCK_BREAK, blockListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);
		System.out.println(getDescription().getName() + " " + getDescription().getVersion() + " is enabled.");
	}
}