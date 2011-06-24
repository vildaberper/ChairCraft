package com.vildaberper.ChairCraft;

import org.bukkit.block.Block;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;

public class ChairCraftEntityListener extends EntityListener{
	@Override
	public void onEntityExplode(EntityExplodeEvent event){
		if(!event.isCancelled()){
			for(Block block : event.blockList()){
				for(int i = 0; i < ChairCraftPlayerListener.seats.size(); i++){
					if(ChairCraftPlayerListener.seats.get(i).getBlock().equals(block)){
						if(ChairCraftPlayerListener.seats.get(i).getArrow().getPassenger() != null){
							ChairCraftPlayerListener.seats.get(i).getArrow().getPassenger().teleport(ChairCraftPlayerListener.seats.get(i).getArrow().getPassenger());
						}
						ChairCraftPlayerListener.seats.get(i).getArrow().remove();
						ChairCraftPlayerListener.seats.remove(i);
					}
				}
			}
		}
	}
}