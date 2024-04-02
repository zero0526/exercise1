package com.flappy.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.flappy.game.MyGdxGame;

public class Bird {
    private static final int MOVEMENT=200;
    private static final int GRAVITY=-15;
    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private Rectangle bounds;
    private Animation birdAnimation;

    public Bird(int x, int y){
        position=new Vector3(x,y,0);
        velocity=new Vector3(0,0,0);//bat dau thi khong chay
        texture=new Texture("images/birdanimation.png");
        birdAnimation=new Animation(new TextureRegion(texture),3,0.5f);
        bounds =new Rectangle(position.x,y,texture.getWidth()/3,texture.getHeight());
    }
    public void update(float dt){
        birdAnimation.update(dt);
        if(position.y>0)
        velocity.add(0,GRAVITY,0);//them vector trong luc vao vector van toc
        velocity.scl(dt);//dieu chinh vector van toc
        position.add(MOVEMENT*dt,velocity.y,0);//them vector van toc vao position
        if(position.y<0)position.y=0;
        
        velocity.scl(1/dt);//quay lai truoc khi scl
        bounds.setPosition(position.x,position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    public TextureRegion getTexture(){
        return birdAnimation.getFrame();
    }

    public void jump(){
       this.setVelocity(new Vector3(0,250,0));
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public void dispose(){
        texture.dispose();
    }
}
