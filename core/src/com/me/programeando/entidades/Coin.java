package com.me.programeando.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Coin extends ObjetoEstatico {

	//private Texture                         		textura; 
	private TextureRegion                   currentFrame;
	private Animation running;

		
	
	public Coin(){
		TextureAtlas atlas;
		atlas = new TextureAtlas(Gdx.files.internal("data/texture/esquilo.atlas"));
		AtlasRegion coin1 = atlas.findRegion("coin1");
		AtlasRegion coin2 = atlas.findRegion("coin2");
		AtlasRegion coin3 = atlas.findRegion("coin3");
		
running = new Animation(0.120f, coin1,coin2,coin3);	
running.setPlayMode(Animation.PlayMode.LOOP_REVERSED);
this.stateTime=0;
this.isLive = true;
				// figure out the width and height of the koala for collision
				// detection and rendering by converting a koala frames pixel
				// size into world units (1 unit == 16 pixels)
				this.width =  1 / 120f *coin1.getRegionWidth();		//120
				this.height = 1 / 120f *coin1.getRegionHeight();
	}
	
	
	
	public TextureRegion anima(float deltaTime){
		stateTime += deltaTime;
		currentFrame = running.getKeyFrame(stateTime, true); 
		return currentFrame;
	}
	

	

}
