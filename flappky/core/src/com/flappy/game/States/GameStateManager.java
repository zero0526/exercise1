package com.flappy.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    protected Stack<State> states;
    public GameStateManager(){
        states=new Stack<State>();
    }
    public void push(State state){
        states.push(state);
    }
    public void pop(){
        states.pop().dispose();
    }
    public void set(State state){
            states.pop().dispose();
            states.push(state);
    }
    public void update(float dt){
        states.peek().update(dt);//cap nhat logic cho trang thai cua nguoi choi khoang thio gian giua hai state
    }
    public void render(SpriteBatch sb){
       states.peek().render(sb);//.peek( truy cap vao pt dau ma khong lay no ra khoi stack
    }
}
