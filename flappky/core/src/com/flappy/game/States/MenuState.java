package com.flappy.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappy.game.MyGdxGame;

public class MenuState extends State{
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm){

        super(gsm);
        background=new Texture("images/bg.png");
        playBtn=new Texture("images/playbtn.png");

    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));//dua playState len dau trang thai
        }
    }

    @Override
    public  void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();//mo container
        sb.draw(background,0,0, MyGdxGame.width,MyGdxGame.height);
        sb.draw(playBtn,MyGdxGame.width/2-playBtn.getWidth()/2,MyGdxGame.height/2-playBtn.getHeight()/2);
        sb.end();
    }
    public void dispose(){
        background.dispose();//huy doi tuong giai phong bo nho bat cu doi tuong nao tao trong method create nen duoc huy
        playBtn.dispose();
        System.out.println("Menu State disposed ");
    }
}
