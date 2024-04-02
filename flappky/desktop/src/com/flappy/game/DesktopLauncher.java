package com.flappy.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.flappy.game.MyGdxGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();//dung de cau hinh app ex:setFullScreen,setWindownSize, allwindown etc
		config.setWindowedMode(MyGdxGame.width,MyGdxGame.height);//thay  doi kich thuoc man hinh
		config.setResizable(false);//khong thay doi kich thuoc man hinh
		config.setForegroundFPS(60);
		config.setTitle(MyGdxGame.TITLE);
		new Lwjgl3Application(new MyGdxGame(), config);
	}
}

