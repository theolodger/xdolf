package com.darkcart.xdolf.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.util.Category;
import com.darkcart.xdolf.util.Value;

import net.minecraft.client.entity.EntityPlayerSP;

public class Flight extends Module {
	
	public static Value flySpeed = new Value("Fly");
	
	public Flight() {
		super("Flight", "Flying mode.", Keyboard.KEY_V, 0xFFFFFF, Category.Player);
	}

	@Override
	public void onUpdate(EntityPlayerSP player) {
		if(isEnabled()) {
			try {
				player.motionX = 0;
				player.motionY = 0;
				player.motionZ = 0;
				player.landMovementFactor = flySpeed.getValue();
				player.jumpMovementFactor = flySpeed.getValue();
				player.inWater = false;
				if(Wrapper.getMinecraft().inGameHasFocus) {
					if(Keyboard.isKeyDown(Wrapper.getMinecraft().gameSettings.keyBindJump.keyCode)) {
						player.motionY += flySpeed.getValue() / 2 + 0.2F;
					}
					if(Keyboard.isKeyDown(Wrapper.getMinecraft().gameSettings.keyBindSneak.keyCode)) {
						player.motionY -= flySpeed.getValue() / 2 + 0.2F;
					}
				}
			}catch(Exception ex){}
		}
	}
}
