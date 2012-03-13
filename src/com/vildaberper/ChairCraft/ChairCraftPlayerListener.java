package com.vildaberper.ChairCraft;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class ChairCraftPlayerListener implements Listener {
	public static List<Seat> seats = new LinkedList<Seat>();
	public static List<Integer> t = new LinkedList<Integer>();
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		int ClickedBlock = event.getClickedBlock().getTypeId();
		
		if ((player.isOp() || player.hasPermission("*") || player.hasPermission("chaircraft.*") || player.hasPermission("chaircraft.sit")) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && ( ClickedBlock == 53 || ClickedBlock == 67 || ClickedBlock == 44 || ClickedBlock == 109 || ClickedBlock == 108 || ClickedBlock == 114) && t.contains(event.getClickedBlock().getRelative(BlockFace.UP).getTypeId()) && !t.contains(event.getClickedBlock().getRelative(BlockFace.DOWN).getTypeId())) {
			boolean seated = false;

			for (Seat seat : seats) {
				if (seat.getArrow().getPassenger() != null && seat.getArrow().getPassenger() instanceof Player && ((Player) seat.getArrow().getPassenger()).equals(player) && seat.getBlock().equals(event.getClickedBlock())) {
					seated = true;
				}
				if (seat.getBlock().equals(event.getClickedBlock()) && seat.getArrow().getPassenger() != null && !seat.getArrow().getPassenger().equals(player)) {
					event.setCancelled(true);
					return;
				}
			}

			if (!seated) {
				Block b = event.getClickedBlock();
				Location l = b.getLocation();
				boolean sit = true;

				if (!player.isSneaking()) {
					return;
				}

				l.setX(l.getX() + 0.5);
				l.setY(l.getY() + 0.5);
				l.setZ(l.getZ() + 0.5);
				if (b.getTypeId() != 44) {
					if (Byte.toString(b.getData()).equals("0")) {
						l.setX(l.getX() - ChairCraft.addition);
						if (ChairCraft.sign_check) {
							for (int i = 0; i < ChairCraft.max_width; i++) {
								int BlockTypePlusZ = b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId();
								
								if ( BlockTypePlusZ != 53 && BlockTypePlusZ != 67 && BlockTypePlusZ != 68 && BlockTypePlusZ != 108 && BlockTypePlusZ != 109 && BlockTypePlusZ != 68 && BlockTypePlusZ != 108 && BlockTypePlusZ != 114 ) {
									sit = false;
								}
								if (BlockTypePlusZ == 68) {
									i = ChairCraft.max_width;
								}
								if (BlockTypePlusZ != 68 && i == ChairCraft.max_width - 1) {
									sit = false;
								}
							}
							for (int i = 0; i < ChairCraft.max_width; i++) {
								int BlockTypeMin = b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId();
								
								if (BlockTypeMin != 53 && BlockTypeMin != 67 && BlockTypeMin != 68 && BlockTypeMin != 108 && BlockTypeMin != 109 && BlockTypeMin != 114) {
									sit = false;
								}
								if (BlockTypeMin == 68) {
									i = ChairCraft.max_width;
								}
								if (BlockTypeMin != 68 && i == ChairCraft.max_width - 1) {
									sit = false;
								}
							}
						}
					} else if (Byte.toString(b.getData()).equals("1")) {
						l.setX(l.getX() + ChairCraft.addition);
						if (ChairCraft.sign_check) {
							for (int i = 0; i < ChairCraft.max_width; i++) {
								int BlockTypePlusZ = b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId();
								
								if ( BlockTypePlusZ != 53 && BlockTypePlusZ != 67 && BlockTypePlusZ != 68 && BlockTypePlusZ != 108 && BlockTypePlusZ != 109 && BlockTypePlusZ != 114 ) {
									sit = false;
								}
								if (BlockTypePlusZ == 68) {
									i = ChairCraft.max_width;
								}
								if (BlockTypePlusZ != 68 && i == ChairCraft.max_width - 1) {
									sit = false;
								}
							}
							
							for(int i = 0; i < ChairCraft.max_width; i++){
								int BlockTypeMinZ = b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId();
								int BlockTypePlusZ = b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId();
								
								if(BlockTypeMinZ != 53 && BlockTypeMinZ != 67 && BlockTypeMinZ != 68 && BlockTypePlusZ != 108 && BlockTypePlusZ != 109 && BlockTypePlusZ != 114 ){
									sit = false;
								}
								if(BlockTypeMinZ == 68){
									i = ChairCraft.max_width;
								}
								if(BlockTypeMinZ != 68 && i == ChairCraft.max_width - 1){
									sit = false;
								}
							}
						}
					} else if (Byte.toString(b.getData()).equals("2")) {
						l.setZ(l.getZ() - ChairCraft.addition);
						if (ChairCraft.sign_check) {
							for (int i = 0; i < ChairCraft.max_width; i++) {
								int BlockTypePlusX = b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId();
								int BlockTypePlusZ = b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId();
								
								if (BlockTypePlusX != 53 && BlockTypePlusX != 67 && BlockTypePlusX != 68 && BlockTypePlusZ != 108 && BlockTypePlusZ != 109 && BlockTypePlusZ != 114 ) {
									sit = false;
								}
								if (BlockTypePlusX == 68) {
									i = ChairCraft.max_width;
								}
								if (BlockTypePlusX != 68 && i == ChairCraft.max_width - 1) {
									sit = false;
								}
							}
							
							for(int i = 0; i < ChairCraft.max_width; i++){
								int BlockTypeMinX = b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId();
								int BlockTypePlusZ = b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId();
								
								if(BlockTypeMinX != 53 && BlockTypeMinX != 67 && BlockTypeMinX != 68 && BlockTypePlusZ != 108 && BlockTypePlusZ != 109 && BlockTypePlusZ != 114 ){
									sit = false;
								}
								if(BlockTypeMinX == 68){
									i = ChairCraft.max_width;
								}
								if(BlockTypeMinX != 68 && i == ChairCraft.max_width - 1){
									sit = false;
								}
							}
						}
					} else if (Byte.toString(b.getData()).equals("3")) {
						l.setZ(l.getZ() + ChairCraft.addition);
						if (ChairCraft.sign_check) {
							for (int i = 0; i < ChairCraft.max_width; i++) {
								int BlockTypePlusX = b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId();
								int BlockTypePlusZ = b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId();
								
								if (BlockTypePlusX != 53 && BlockTypePlusX != 67 && BlockTypePlusX != 68 && BlockTypePlusZ != 108 && BlockTypePlusZ != 109 && BlockTypePlusZ != 114 ) {
									sit = false;
								}
								if (b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId() == 68) {
									i = ChairCraft.max_width;
								}
								if (b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId() != 68 && i == ChairCraft.max_width - 1) {
									sit = false;
								}
							}
							for(int i = 0; i < ChairCraft.max_width; i++){
								int BlockTypeMinX = b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId();
								int BlockTypePlusZ = b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId();
								
								if(BlockTypeMinX != 53 && BlockTypeMinX != 67 && BlockTypeMinX != 68 && BlockTypePlusZ != 108 && BlockTypePlusZ != 109 && BlockTypePlusZ != 114 ){
									sit = false;
								}
								if(BlockTypeMinX == 68){
									i = ChairCraft.max_width;
								}
								if(BlockTypeMinX != 68 && i == ChairCraft.max_width - 1){
									sit = false;
								}
							}
						}
					} else {
						sit = false;
					}
				} else {
					if (ChairCraft.sign_check) {
						boolean s1 = true, s2 = true;
						for(int i = 0; i < ChairCraft.max_width; i++){
							int BlockTypePlusX = b.getWorld().getBlockAt(b.getX() + i, b.getY(), b.getZ()).getTypeId();
							
							if(BlockTypePlusX != 44 && BlockTypePlusX != 68){
								s1 = false;
							}
							if(BlockTypePlusX == 68){
								i = ChairCraft.max_width;
							}
							if(BlockTypePlusX != 68 && i == ChairCraft.max_width - 1){
								s1 = false;
							}
						}
						for(int i = 0; i < ChairCraft.max_width; i++){
							int BlockTypeMinX = b.getWorld().getBlockAt(b.getX() - i, b.getY(), b.getZ()).getTypeId();
							
							if(BlockTypeMinX != 44 && BlockTypeMinX != 68){
								s1 = false;
							}
							if(BlockTypeMinX == 68){
								i = ChairCraft.max_width;
							}
							if(BlockTypeMinX != 68 && i == ChairCraft.max_width - 1){
								s1 = false;
							}
						}
						for(int i = 0; i < ChairCraft.max_width; i++){
							int BlockTypePlusZ = b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() + i).getTypeId();
							
							if(BlockTypePlusZ != 44 && BlockTypePlusZ != 68){
								s2 = false;
							}
							if(BlockTypePlusZ == 68){
								i = ChairCraft.max_width;
							}
							if(BlockTypePlusZ != 68 && i == ChairCraft.max_width - 1){
								s2 = false;
							}
						}
						for(int i = 0; i < ChairCraft.max_width; i++){
							int BlockTypeMinZ = b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ() - i).getTypeId();
							
							if(BlockTypeMinZ != 44 && BlockTypeMinZ != 68){
								s2 = false;
							}
							if(BlockTypeMinZ == 68){
								i = ChairCraft.max_width;
							}
							if(BlockTypeMinZ != 68 && i == ChairCraft.max_width - 1){
								s2 = false;
							}
						}
						if(!s1 && !s2){
							sit = false;
						}
					}
				}
				if (sit) {
					Arrow arrow = l.getWorld().spawnArrow(l, new Vector(0, -1, 0), 0, 0);

					event.setCancelled(true);
					for (int i = 0; i < seats.size(); i++) {
						if (seats.get(i).getArrow().getPassenger() != null && seats.get(i).getArrow().getPassenger() instanceof Player && ((Player) seats.get(i).getArrow().getPassenger()).equals(player)) {
							player.teleport(player.getLocation());
							seats.get(i).getArrow().remove();
							seats.remove(i);
							i--;
						}
					}
					seats.add(new Seat(arrow, event.getClickedBlock()));
					arrow.setPassenger(player);
				} else {
					return;
				}
			} else {
				event.setCancelled(true);
				for (int i = 0; i < seats.size(); i++) {
					if (seats.get(i).getArrow().getPassenger() != null && seats.get(i).getArrow().getPassenger() instanceof Player && ((Player) seats.get(i).getArrow().getPassenger()).equals(player) && seats.get(i).getBlock().equals(event.getClickedBlock())) {
						player.teleport(player.getLocation());
						seats.get(i).getArrow().remove();
						seats.remove(i);
						i--;
					}
				}
			}
		}
	}
}