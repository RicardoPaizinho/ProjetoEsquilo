package com.me.programeando;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
//import com.me.programeando.SuperKoalio.Koala.State;

public class Esquilo {
	static float WIDTH;
	static float HEIGHT;
	static float MAX_VELOCITY = 30f;	//10
	static float JUMP_VELOCITY = 40f;  //5
	static float DAMPING = 0.82f;	//0,99 deslizando no chao
	enum State {	Standing,Walking,Jumping		}

	final Vector2 position = new Vector2();
	final Vector2 velocity = new Vector2();
	State state = State.Standing;
	float stateTime = 0;
	boolean facesRight = true;
	boolean grounded = false;
//	private Texture koalaTexture;
	Texture                         		proto1; 
	private TextureRegion                   currentFrame,TR;
	private Animation stand,walk,jump;
	
	
	public void EsqInit(){
proto1 = new Texture(Gdx.files.internal("data/esquiloneg.png"));
TR = new TextureRegion(proto1, 0, 0, 224, 116); 
TextureRegion[][] BBB = TR.split(56,58);		
		
stand = new Animation(0.625f, BBB[0][0],BBB[0][1]); 	
walk = new Animation(0.125f, BBB[0][2], BBB[1][0]);
jump = new Animation(0.825f, BBB[1][1],BBB[1][2]); 	
//andandobaixo = new Animation(0.125f, BBB[1][0],BBB[1][1],BBB[1][2],BBB[1][3],BBB[1][4],BBB[1][5]);
//paradocima = new Animation(0.525f,BBB[0][2]);
//paradobaixo = new Animation(0.925f,BBB[0][3]);
//paradodir = new Animation(0.525f,BBB[0][4]);
//paradoesq = new Animation(0.525f,BBB[0][1]);		
//estado=PARADO_BAIXO;
		
		
		
		
		// load the koala frames, split them, and assign them to Animations
		
				//koalaTexture = new Texture("data/maps/tiled/super-koalio/koalio.png"); 
				//TextureRegion[] regions = TextureRegion.split(koalaTexture, 18, 26)[0];
				//stand = new Animation(0, regions[0]);			//standing-parando, depe
				//jump = new Animation(0, regions[1]);			//jumping
				//walk = new Animation(0.15f, regions[2], regions[3], regions[4]);//andando
//lower											//lowering-abaixando
	
				stand.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
				walk.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
				jump.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
				//jump.setPlayMode(Animation.);

				// figure out the width and height of the koala for collision
				// detection and rendering by converting a koala frames pixel
				// size into world units (1 unit == 16 pixels)
				WIDTH = 1 / 35f * BBB[1][1].getRegionWidth();		//16
				HEIGHT = 1 / 35f * BBB[1][1].getRegionHeight();
		
		
		
		
	}
	
	
	public TextureRegion AnimaEsq(){
		switch(state) {
		case Standing: currentFrame = stand.getKeyFrame(stateTime, true); break;
		case Walking:  currentFrame = walk.getKeyFrame(stateTime, true); break;
		case Jumping:  currentFrame = jump.getKeyFrame(stateTime, true); break; 
	}
		//ProcessaTeclas();
		//currentFrame=BBB[0][1];
		//if(estado==ANDANDO_ESQ)currentFrame = andandoesq.getKeyFrame(stateTime, true);
		return currentFrame;
	}
	

}
