package com.vildaberper.ChairCraft;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.util.Vector;

public class ChairCraftPlayerListener extends PlayerListener{
	public static List<Seat> seats = new LinkedList<Seat>();
	public static List<Integer> hold = new LinkedList<Integer>(), t = new LinkedList<Integer>();

	@Override
	public void onPlayerInteract(PlayerInteractEvent event){
		if((ChairCraft.perm == null || ChairCraft.perm.has(event.getPlayer(), "chaircraft.sit")) && !hold.contains(event.getPlayer().getInventory().getItemInHand().getTypeId()) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (event.getClickedBlock().getTypeId() == 53 || event.getClickedBlock().getTypeId() == 67 || event.getClickedBlock().getTypeId() == 44) && t.contains(event.getClickedBlock().getFace(BlockFace.UP).getTypeId()) && !t.contains(event.getClickedBlock().getFace(BlockFace.DOWN).getTypeId())){
			boolean seated = false;

			for(Seat seat : seats){
				if(seat.getArrow().getPassenger() != null && seat.getArrow().getPassenger() instanceof Player && ((Player) seat.getArrow().getPassenger()).equals(event.getPlayer()) && seat.getBlock().equals(event.getClickedBlock())){
					seated = true;
				}
				if(seat.getBlock().equals(event.getClickedBlock()) && seat.getArrow().getPassenger() != null && !seat.getArrow().getPassenger().equals(event.getPlayer())){
					event.setCancelled(true);
					return;
				}
			}
			if(!seated){
				Block b = event.getClickedBlock();
				Location l = b.getLocation();
				boolean sit = true;

				l.setX(l.getX() + 0.5);
				l.setY(l.getY() + 0.5);
				l.setZ(l.getZ() + 0.5);
				if(event.getClickedBlock().getTypeId() != 44){
					if(Byte.toString(b.getData()).equals("0")){
						l.setX(l.getX() - ChairCraft.addition);
						if(ChairCraft.sign_check){
							for(int i = 0; i < ChairCraft.max_width; i++){
								if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId() != 53 && b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId() != 67 && b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId() != 68){
									sit = false;
								}
								if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId() == 68){
									i = ChairCraft.max_width;
								}
								if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId() != 68 && i == ChairCraft.max_width - 1){
									sit = false;
								}
							}
							for(int i = 0; i < ChairCraft.max_width; i++){
								if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId() != 53 && b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId() != 67 && b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId() != 68){
									sit = false;
								}
								if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId() == 68){
									i = ChairCraft.max_width;
								}
								if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId() != 68 && i == ChairCraft.max_width - 1){
									sit = false;
								}
							}
						}
					}else if(Byte.toString(b.getData()).equals("1")){
						l.setX(l.getX() + ChairCraft.addition);
						if(ChairCraft.sign_check){
							for(int i = 0; i < ChairCraft.max_width; i++){
								if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId() != 53 && b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId() != 67 && b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId() != 68){
									sit = false;
								}
								if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId() == 68){
									i = ChairCraft.max_width;
								}
								if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId() != 68 && i == ChairCraft.max_width - 1){
									sit = false;
								}
							}
							for(int i = 0; i < ChairCraft.max_width; i++){
								if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId() != 53 && b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId() != 67 && b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId() != 68){
									sit = false;
								}
								if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId() == 68){
									i = ChairCraft.max_width;
								}
								if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId() != 68 && i == ChairCraft.max_width - 1){
									sit = false;
								}
							}
						}
					}else if(Byte.toString(b.getData()).equals("2")){
						l.setZ(l.getZ() - ChairCraft.addition);
						if(ChairCraft.sign_check){
							for(int i = 0; i < ChairCraft.max_width; i++){
								if(b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId() != 53 && b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId() != 67 && b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId() != 68){
									sit = false;
								}
								if(b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId() == 68){
									i = ChairCraft.max_width;
								}
								if(b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId() != 68 && i == ChairCraft.max_width - 1){
									sit = false;
								}
							}
							for(int i = 0; i < ChairCraft.max_width; i++){
								if(b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId() != 53 && b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId() != 67 && b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId() != 68){
									sit = false;
								}
								if(b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId() == 68){
									i = ChairCraft.max_width;
								}
								if(b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId() != 68 && i == ChairCraft.max_width - 1){
									sit = false;
								}
							}
						}
					}else if(Byte.toString(b.getData()).equals("3")){
						l.setZ(l.getZ() + ChairCraft.addition);
						if(ChairCraft.sign_check){
							for(int i = 0; i < ChairCraft.max_width; i++){
								if(b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId() != 53 && b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId() != 67 && b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId() != 68){
									sit = false;
								}
								if(b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId() == 68){
									i = ChairCraft.max_width;
								}
								if(b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId() != 68 && i == ChairCraft.max_width - 1){
									sit = false;
								}
							}
							for(int i = 0; i < ChairCraft.max_width; i++){
								if(b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId() != 53 && b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId() != 67 && b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId() != 68){
									sit = false;
								}
								if(b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId() == 68){
									i = ChairCraft.max_width;
								}
								if(b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId() != 68 && i == ChairCraft.max_width - 1){
									sit = false;
								}
							}
						}
					}else{
						sit = false;
					}
				}else{
					if(ChairCraft.sign_check){
						boolean s1 = true, s2 = true;
						for(int i = 0; i < ChairCraft.max_width; i++){
							if(b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId() != 44 && b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId() != 68){
								s1 = false;
							}
							if(b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId() == 68){
								i = ChairCraft.max_width;
							}
							if(b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId() != 68 && i == ChairCraft.max_width - 1){
								s1 = false;
							}
						}
						for(int i = 0; i < ChairCraft.max_width; i++){
							if(b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId() != 44 && b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId() != 68){
								s1 = false;
							}
							if(b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId() == 68){
								i = ChairCraft.max_width;
							}
							if(b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId() != 68 && i == ChairCraft.max_width - 1){
								s1 = false;
							}
						}
						for(int i = 0; i < ChairCraft.max_width; i++){
							if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId() != 44 && b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId() != 68){
								s2 = false;
							}
							if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId() == 68){
								i = ChairCraft.max_width;
							}
							if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId() != 68 && i == ChairCraft.max_width - 1){
								s2 = false;
							}
						}
						for(int i = 0; i < ChairCraft.max_width; i++){
							if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId() != 44 && b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId() != 68){
								s2 = false;
							}
							if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId() == 68){
								i = ChairCraft.max_width;
							}
							if(b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId() != 68 && i == ChairCraft.max_width - 1){
								s2 = false;
							}
						}
						if(!s1 && !s2){
							sit = false;
						}
					}
				}
				if(sit){
					Arrow arrow = l.getWorld().spawnArrow(l, new Vector(0, -1, 0), 0, 0);

					for(int i = 0; i < seats.size(); i++){
						if(seats.get(i).getArrow().getPassenger() != null && seats.get(i).getArrow().getPassenger() instanceof Player && ((Player) seats.get(i).getArrow().getPassenger()).equals(event.getPlayer())){
							event.getPlayer().teleport(event.getPlayer().getLocation());
							seats.get(i).getArrow().remove();
							seats.remove(i);
							i--;
						}
					}
					seats.add(new Seat(arrow, event.getClickedBlock()));
					arrow.setPassenger(event.getPlayer());
				}else{
					return;
				}
			}else{
				for(int i = 0; i < seats.size(); i++){
					if(seats.get(i).getArrow().getPassenger() != null && seats.get(i).getArrow().getPassenger() instanceof Player && ((Player) seats.get(i).getArrow().getPassenger()).equals(event.getPlayer()) && seats.get(i).getBlock().equals(event.getClickedBlock())){
						event.getPlayer().teleport(event.getPlayer().getLocation());
						seats.get(i).getArrow().remove();
						seats.remove(i);
						i--;
					}
				}
			}
			event.setCancelled(true);
		}
	}
}