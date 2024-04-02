package com.flappy.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;//dung de ve 1 phan cua texture
    private float maxFrameTime;//thoi gian de khung hinh phai o che dop xem
    private float  currentFrameTime;//khong thoi gian annimation o khung hinh hien tai
    private int frameCount;//so khung hinh
    private int frame;//khung hinh hien tai
     public Animation(TextureRegion region,int frameCount,float cycleTime){
            frames=new Array<TextureRegion>();
            int frameWidth=region.getRegionWidth()/frameCount;//do rong cua  frame =tong frames chia cho frame
            for(int i=0;i<frameCount;i++){
                frames.add(new TextureRegion(region,i*frameWidth,0,frameWidth,region.getRegionHeight()));//chia anh goc rra cac anh con
            }
            this.frameCount=frameCount;
            this.maxFrameTime=cycleTime/frameCount;
            frame=0;
            currentFrameTime=0;
     }
     public void update(float dt){
         currentFrameTime+=dt;//dt la thoi gian ma currentFrame xuat hien tren man
         if(currentFrameTime>maxFrameTime){
             frame++;
             currentFrameTime=0;
         }
         if(frame>=frameCount)frame=0;
     }
     public TextureRegion  getFrame(){
         return frames.get(frame);
     }
}
