package com.vildaberper.ChairCraft;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;

public class ChairCraftBlockListener extends BlockListener{
	@Override
	public void onBlockBreak(BlockBreakEvent event){
		if(!event.isCancelled()){
			for(int i = 0; i < ChairCraftPlayerListener.seats.size(); i++){
				if(ChairCraftPlayerListener.seats.get(i).getBlock().equals(event.getBlock())){
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