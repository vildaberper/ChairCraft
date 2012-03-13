package com.vildaberper.ChairCraft;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class Seat {
	private Arrow a;
	private Block b;
	private int id = 0;
	private int tpid = 0;

	public Seat(Arrow arrow, Block block) {
		a = arrow;
		b = block;
		tpid = ChairCraft.p.getServer().getScheduler().scheduleAsyncRepeatingTask(ChairCraft.p, new Runnable() {
			@Override
			public void run() {
				Location l = b.getLocation();

				l.setX(l.getX() + 0.5);
				l.setY(l.getY() + 0.0);
				l.setZ(l.getZ() + 0.5);
				if (Byte.toString(b.getData()).equals("0")) {
					l.setX(l.getX() - 0.1);
				} else if (Byte.toString(b.getData()).equals("1")) {
					l.setX(l.getX() + 0.1);
				} else if (Byte.toString(b.getData()).equals("2")) {
					l.setZ(l.getZ() - 0.1);
				} else if (Byte.toString(b.getData()).equals("3")) {
					l.setZ(l.getZ() + 0.1);
				}
				a.teleport(l);
			}
		}, ChairCraft.arrow_teleport_ticks, ChairCraft.arrow_teleport_ticks);

		id = ChairCraft.p.getServer().getScheduler().scheduleAsyncRepeatingTask(ChairCraft.p, new Runnable() {
			@Override
			public void run() {
				if (a.getPassenger() == null) {
					ChairCraft.p.getServer().getScheduler().cancelTask(tpid);
					ChairCraft.p.getServer().getScheduler().cancelTask(id);
					a.remove();
					for (int i = 0; i < ChairCraftPlayerListener.seats.size(); i++) {
						if (ChairCraftPlayerListener.seats.get(i).getBlock().equals(b)) {
							ChairCraftPlayerListener.seats.remove(i);
							i--;
						}
					}
				} else {
					Location l = b.getLocation();
					Entity e = a.getPassenger();
					Arrow aa = a;

					l.setX(l.getX() + 0.5);
					l.setY(l.getY() + 0.5);
					l.setZ(l.getZ() + 0.5);
					if (Byte.toString(b.getData()).equals("0")) {
						l.setX(l.getX() - 0.1);
					} else if (Byte.toString(b.getData()).equals("1")) {
						l.setX(l.getX() + 0.1);
					} else if (Byte.toString(b.getData()).equals("2")) {
						l.setZ(l.getZ() - 0.1);
					} else if (Byte.toString(b.getData()).equals("3")) {
						l.setZ(l.getZ() + 0.1);
					}
					a = l.getWorld().spawnArrow(l, new Vector(0, -1, 0), 0, 0);
					a.setPassenger(e);
					aa.remove();
				}
			}
		}, 20 * ChairCraft.arrow_respawn, 20 * ChairCraft.arrow_respawn);
	}

	public void setArrow(Arrow arrow) {
		a = arrow;
	}

	public Arrow getArrow() {
		return a;
	}

	public void setBlock(Block block) {
		b = block;
	}

	public Block getBlock() {
		return b;
	}
}