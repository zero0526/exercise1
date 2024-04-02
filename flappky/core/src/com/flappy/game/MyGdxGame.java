package com.flappy.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.flappy.game.States.GameStateManager;
import com.flappy.game.States.MenuState;

public class MyGdxGame extends ApplicationAdapter {
	public static final int width=1500;
	public static final int height=1000;
	public static final String TITLE="flappy Bird";
	private GameStateManager gsm;

	private SpriteBatch batch;//khong ne tao spriteBatch cho bat ky doioi tuong nao chi 1 va ve tat ca
	private Music music ;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm=new GameStateManager();
		music=Gdx.audio.newMusic(Gdx.files.internal("music/sfx_wing.ogg"));//am thanh phat tai cai play state
		music .setLooping(true);//lap music
		music.setVolume(0.2f);
		music.play();
		ScreenUtils.clear(1, 0, 0, 1);//mau nen
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);//khong can lan nao cung phai xoa
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();//bo khi no tat
	}
}
