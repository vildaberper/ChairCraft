package com.vildaberper.ChairCraft;

import org.bukkit.entity.Player;

public class ChairCraftHealer implements Runnable{
	@Override
	public void run(){
		for(Seat seat : ChairCraftPlayerListener.seats){
			if(seat.getArrow().getPassenger() != null){
				if(((Player) seat.getArrow().getPassenger()).getHealth() > 0 && !((Player) seat.getArrow().getPassenger()).isDead() && ((Player) seat.getArrow().getPassenger()).isOnline()){
					if(((Player) seat.getArrow().getPassenger()).getHealth() + ChairCraft.amount <= 20){
						((Player) seat.getArrow().getPassenger()).setHealth(((Player) seat.getArrow().getPassenger()).getHealth() + ChairCraft.amount);
					}else{
						if(((Player) seat.getArrow().getPassenger()).getHealth() != 20){
							((Player) seat.getArrow().getPassenger()).setHealth(20);
						}
					}
				}
			}
		}
	}
}