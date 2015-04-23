package com.me.programeando.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Mosquito extends ObjetoDinamico {

	
	private TextureRegion                   currentFrame;
	Animation flying;
		
	
	public  Mosquito(){
		TextureAtlas atlas;
		atlas = new TextureAtlas(Gdx.files.internal("data/texture/mosquito.atlas"));
		
		AtlasRegion frame01 = atlas.findRegion("frame1");
		AtlasRegion frame02 = atlas.findRegion("frame2");
		AtlasRegion frame03 = atlas.findRegion("frame3");
		AtlasRegion frame04 = atlas.findRegion("frame4");
		AtlasRegion frame05 = atlas.findRegion("frame5");
		 
		
		//AtlasRegion coin1 = atlas.findRegion("coin1");
		//AtlasRegion coin2 = atlas.findRegion("coin2");
		//AtlasRegion coin3 = atlas.findRegion("coin3");
		//Sprite sprite = atlas.createSprite("otherimage		name");
		//NinePatch patch = atlas.createPatch("patchimagename");
		
		
		
		
		// load the koala frames, split them, and assign them to Animations
//textura = new Texture(Gdx.files.internal("data/certo.png"));
//TR = new TextureRegion(textura, 0, 0, 1024, 512); 
//TextureRegion[][] BBB = TR.split(82,110);		
		
flying = new Animation(0.010f, frame01,frame02,frame03,frame04,frame05);	

	
				flying.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
				
				
				this.stateTime=0;
				this.maxVelocity=5f ;	//22f
				this.jumpVelocity=22f ; //33f
				this.acceleration = 2f;	//5f
				this.damping = 0.77f;	//0,99 deslizando no chao
				this.gravity = -1.1f;	//original 2
				this.facesRight = true;
				this.grounded = false;
				this.isLive = true;
				this.isNear = false;
				state = State.Jumping;
				// figure out the width and height of the koala for collision
				// detection and rendering by converting a koala frames pixel
				// size into world units (1 unit == 16 pixels)
				this.width = 1 / 50f * frame01.getRegionWidth();		//16
				this.height = 1 /50f * frame01.getRegionHeight();
	
	}
	
	
	public TextureRegion anima(float deltaTime){
		stateTime += deltaTime;

		
		 currentFrame = flying.getKeyFrame(stateTime, true);

		
		return currentFrame;
	}
	

	

}
