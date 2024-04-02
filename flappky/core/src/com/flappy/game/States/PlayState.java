package com.flappy.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flappy.game.MyGdxGame;
import com.flappy.game.sprites.Bird;
import com.flappy.game.sprites.Tube;

public class PlayState extends State {
    private static final int TUBE_SPACING=250;
    private static final int TUBE_COUNT=5;//so tube  appear at same time
    private static final int GROUND_OFFSET=-30;
    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1,groundPos2;
    private Array<Tube> tubes;
    public  PlayState(GameStateManager gsm){
        super(gsm);

        bird =new Bird(50,300);
        cam.setToOrtho(false, MyGdxGame.width/2,MyGdxGame.height/2);//false chi dinh y tang nguoc tu duoi len neu la tru thi lon nguoc lai, mygdxgame.with/2 chi dinh do rong view port

        bg =new Texture("images/bg.png");
        ground=new Texture("images/ground.png");
        groundPos1=new Vector2(cam.position.x-cam.viewportWidth/2,0);
        groundPos2=new Vector2(groundPos1.x+ground.getWidth(),0);
        tubes=new Array<Tube>();
        for(int i=1;i<=TUBE_COUNT;i++){
            tubes.add(new Tube(i*(TUBE_SPACING+Tube.Tube_WIDTH)));
        }
    }
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x=bird.getPosition().x+cam.viewportWidth/4;
        for(int i=0;i<tubes.size;i++){
            Tube tube=tubes.get(i);
            if(cam.position.x-(cam.viewportWidth/2)>tube.getPosTopTube().x+tube.getTopTube().getWidth()){//ì tube vut qua
                tube.reposition(tube.getPosTopTube().x+((Tube.Tube_WIDTH+TUBE_SPACING)*TUBE_COUNT));
            }
            if(tube.collides(bird.getBounds())){
                gsm.set(new PlayState(gsm));
            }
        }
        if(bird.getPosition().y<=ground.getHeight()+GROUND_OFFSET){
            gsm.set(new PlayState(gsm));
        }
        cam.update();//the sma has reposition
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);//ap dung matrix chieu cho spriteBatch kich thuoc cac doi tuong co the doi
        sb.begin();
        sb.draw(bg,cam.position.x-cam.viewportWidth/2,0,MyGdxGame.width,MyGdxGame.height);
        sb.draw(bird.getTexture(),bird.getPosition().x,bird.getPosition().y);

        for(Tube tube:tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground,groundPos1.x,groundPos1.y+GROUND_OFFSET,MyGdxGame.width,ground.getHeight());
        sb.draw(ground,groundPos2.x,groundPos2.y+GROUND_OFFSET,MyGdxGame.width,ground.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube:tubes){
            tube.dispose();
            System.out.printf("play state disposed");
        }
    }
    private void updateGround(){
        if(cam.position.x-cam.viewportWidth/2>groundPos1.x+ground.getWidth()){//khi ma cai mang dat no thoat ra khoi man
            groundPos1.add(ground.getWidth()*2,0);
        }
        if(cam.position.x-cam.viewportWidth/2>groundPos2.x+ground.getWidth()){//khi ma cai mang dat no thoat ra khoi man
            groundPos2.add(ground.getWidth()*2,0);
        }
    }
}
