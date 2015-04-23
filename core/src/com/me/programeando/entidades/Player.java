package com.me.programeando.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends ObjetoDinamico {

	
	private TextureRegion                   currentFrame;
	Animation stand,walk,jump,dead;
		
	
	public  Player(){
		TextureAtlas atlas;
		atlas = new TextureAtlas(Gdx.files.internal("data/texture/esquilo.atlas"));
		
		AtlasRegion walk1 = atlas.findRegion("walk1");
		AtlasRegion walk2 = atlas.findRegion("walk2");
		AtlasRegion walk3 = atlas.findRegion("walk3");
		AtlasRegion walk4 = atlas.findRegion("walk4");
		AtlasRegion walk5 = atlas.findRegion("walk5");
		AtlasRegion stand1 = atlas.findRegion("stand1");
		//AtlasRegion stand2 = atlas.findRegion("stand2");
		AtlasRegion stand3 = atlas.findRegion("stand3");
		 
		
		//AtlasRegion coin1 = atlas.findRegion("coin1");
		//AtlasRegion coin2 = atlas.findRegion("coin2");
		//AtlasRegion coin3 = atlas.findRegion("coin3");
		//Sprite sprite = atlas.createSprite("otherimage		name");
		//NinePatch patch = atlas.createPatch("patchimagename");
		
		
		
		
		// load the koala frames, split them, and assign them to Animations
//textura = new Texture(Gdx.files.internal("data/certo.png"));
//TR = new TextureRegion(textura, 0, 0, 1024, 512); 
//TextureRegion[][] BBB = TR.split(82,110);		
		
stand = new Animation(0.400f, stand1,stand3);	
walk = new Animation(0.140f, walk1,walk2,walk3,walk4,walk5);
jump = new Animation(0.140f, walk1,walk2,walk3,walk4,walk5);
dead= new Animation(0.140f, walk1);
	
				stand.setPlayMode(Animation.PlayMode.LOOP_RANDOM);
				walk.setPlayMode(Animation.PlayMode.LOOP);
				jump.setPlayMode(Animation.PlayMode.LOOP);
				
				this.stateTime=0;
				this.maxVelocity=10f ;	//22f
				this.jumpVelocity=20f ;  //33f
				this.acceleration = 3.1f;	//5f
				this.damping = 0.87f;	//0,99 deslizando no chao
				this.gravity = -1.3f;	//original 2
				this.facesRight = true;
				this.grounded = false;
				this.isLive = true;
				this.isNear = false;
				state = State.Jumping;
				// figure out the width and height of the koala for collision
				// detection and rendering by converting a koala frames pixel
				// size into world units (1 unit == 16 pixels)
				this.width = 1 / 32f * walk1.getRegionWidth();		//16
				this.height = 1 / 32f * walk1.getRegionHeight();
	
	}
	
	
	public TextureRegion anima(float deltaTime){
		stateTime += deltaTime;
if (walk.isAnimationFinished(deltaTime));
		
		
		switch(state) {
		case Standing: currentFrame = stand.getKeyFrame(stateTime, true); break;
		case Walking:  currentFrame = walk.getKeyFrame(stateTime, true); break;
		case Jumping:  currentFrame = jump.getKeyFrame(stateTime, true); break;
		default:
			break; 
	}	return currentFrame;
	}
	

	

}
