package com.me.programeando.desktop;


import samples.SceneDemo;
import touchControl.TouchTest;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.me.programeando.GameEsquilo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			
				config.title = GameEsquilo.TITLE + " v" + GameEsquilo.VERSION;
				config.vSyncEnabled = false;				
				config.width = 800;
				config.height = 480;
				config.resizable=false;
				config.fullscreen=false;
				//config.allowSoftwareMode=true;
	
				//config.fullscreen=false;
				//config.addIcon("img/icon.png", FileType.Internal);
				config.addIcon("iconzang.png", FileType.Internal);		
				new LwjglApplication(new GameEsquilo(), config);
			

	}
}
