package com.flappy.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int Tube_WIDTH=0;
    private static final int FLUCTUATION=130;//khoang random
    private static final int TUBE_GAP=100;//distance
    private static final int LOWEST_OPENING=120;//do  cao thap nhat
    private Texture topTube,bottomTube;
    private Vector2 posTopTube,posBotTube;
    private Rectangle boundsTop,boundsBot;
    private Random rand;

    public Tube(float x){
        topTube=new Texture("images/toptube.png");
        bottomTube=new Texture("images/bottomtube.png");
        rand=new Random();
        posTopTube=new Vector2(x,rand.nextInt(FLUCTUATION)+TUBE_GAP+LOWEST_OPENING);//random arround [0,130]
        posBotTube=new Vector2(x,posTopTube.y-TUBE_GAP-bottomTube.getHeight());
        boundsTop=new Rectangle(posTopTube.x,posTopTube.y,topTube.getWidth(),topTube.getHeight());
        boundsBot=new Rectangle(posBotTube.x,posBotTube.y,bottomTube.getWidth(),bottomTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }
    public void reposition(float x){
        posTopTube.set(x,rand.nextInt(FLUCTUATION)+TUBE_GAP+LOWEST_OPENING);
        posBotTube.set(x,posTopTube.y-TUBE_GAP-bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x,posTopTube.y);//di chuyen cai container ao theo
        boundsBot.setPosition(posBotTube.x,posBotTube.y);
    }
    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop)||player.overlaps(boundsBot);
    }
    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
