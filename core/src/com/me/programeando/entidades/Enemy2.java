package com.me.programeando.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.programeando.entidades.ObjetoDinamico.State;

public class Enemy2 extends ObjetoDinamico {

	Texture                         		textura; 
	private TextureRegion                   currentFrame;
	Animation walk;
		
	
	public  Enemy2(){
		TextureAtlas atlas;
		atlas = new TextureAtlas(Gdx.files.internal("data/texture/esquilo.atlas"));
		
		AtlasRegion walk1 = atlas.findRegion("troll1");
		AtlasRegion walk2 = atlas.findRegion("troll2");

		
	
walk = new Animation(0.155f, walk1,walk2);

				walk.setPlayMode(Animation.PlayMode.LOOP);
		
				this.stateTime=0;
				this.maxVelocity=7f ;	//22f
				this.jumpVelocity=19f ;  //33f
				this.acceleration = 1.2f;	//5f
				this.damping = 0.95f;	//0,99 deslizando no chao
				this.gravity = -1.3f;	//original 2
				this.facesRight = true;
				this.grounded = false;
				this.isLive = true;
				this.isNear = false;
				state = State.Walking;
				// figure out the width and height of the koala for collision
				// detection and rendering by converting a koala frames pixel
				// size into world units (1 unit == 16 pixels)
				this.width = 1 / 64f * walk1.getRegionWidth();		//16
				this.height = 1 / 64f * walk1.getRegionHeight();
	
	}
	
	
	public TextureRegion anima(float deltaTime){
		stateTime += deltaTime;
		currentFrame = walk.getKeyFrame(stateTime, true); 
		
		
		
 
		return currentFrame;
	}
	

	

}
