package com.me.programeando;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;



/**
 * Super Mario Brothers like very basic platformer, using a tile map build
 * via <a href="http://www.mapeditor.org/>Tiled</a> and a tileset
 * and sprites by <a href="http://www.vickiwenderlich.com/">Vicky Wenderlich</a></p>
 * 
 * Shows simple platformer collision detection as well as on-the-fly map modifications
 * through destructable blocks!
 * @author mzechner
 *
 */
public class GameEsquilo implements ApplicationListener {
	/**
	 * The player character, has state and state time, 
	 */
	public static final String TITLE = "Esquilo Neg�o", VERSION = "0.0.5";
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private World world;
	private boolean isrun=true;
	private float deltaTime;
	private Texture                    		paralax; 
	private TextureRegion                   paralaxtr;
	TextureRegion frame = null;
	private Music mainMusic;
	private Sound coinWave,jumpWave;
	Hud hud;
    private Stage stage;
    private Table tableTop;


	@Override
	public void create () {
		
		TextureRegionDrawable draw = new TextureRegionDrawable();
		TextureAtlas atlas;
		atlas = new TextureAtlas(Gdx.files.internal("data/texture/bot.atlas"));
		AtlasRegion bota = atlas.findRegion("botA");
		AtlasRegion botb = atlas.findRegion("botB");
		AtlasRegion bote = atlas.findRegion("botE");
		AtlasRegion botd = atlas.findRegion("botD");
	
		
		//TextureRegion upRegion = ...
		//		TextureRegion downRegion = ...
		//		BitmapFont buttonFont = ...
		
		Table tableL = new Table();
		Table tableR = new Table();
		tableTop = new Table();
		
		//stage.addActor(table);
		tableR.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //sem isso tabela direita nao aparece
		tableTop.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //sem isso tabela direita nao aparece
		//tableR.setPosition(0, 0);
		// 
		
		//draw = new TextureRegionDrawable(hud.getDezena(world.coletadas));
		
		
		
		 Hud myActor = new Hud();
		 draw = new TextureRegionDrawable(myActor.getDezena(84));
		Image digito1 = new Image(draw);
		 tableTop.add(digito1);
		 draw = new TextureRegionDrawable(myActor.getUnidade(84));
			Image digito2 = new Image(draw);
			 tableTop.add(digito2);
		 
		
		 //Button button1 = new Button(draw);
				draw = new TextureRegionDrawable(bote);
				Button button1 = new Button(draw);
				tableL.add(button1);
				
				draw = new TextureRegionDrawable(botd);
				Button button2 = new Button(draw);
				tableL.add(button2);	
				
				draw = new TextureRegionDrawable(bota);
				Button button3 = new Button(draw);
				tableR.add(button3).align(Align.right);
				
				draw = new TextureRegionDrawable(botb);
				Button button4 = new Button(draw);
				tableR.add(button4).align(Align.right);;	
				
				
				button1.addListener(new InputListener() {
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
						 world.LEFT=true;
						return true;
					}
					public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
						 world.LEFT=false;
						
					}				
				});
				
				button2.addListener(new InputListener() {
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
						 world.RIGHT=true;
						return true;
					}
					public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
						 world.RIGHT=false;
						
					}				
				});
				
				button3.addListener(new InputListener() {
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
						 world.JUMP=true;
						return true;
					}
					public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
						 world.JUMP=false;
						
					}				
				});
				

				tableL.align(Align.left | Align.bottom);
				tableR.align(Align.right | Align.bottom);
				tableTop.align(Align.center | Align.top);
			//	TextButton button2 = new TextButton("Button 2", style);
				//table.add(button2);
		
		
        Texture tknob = new Texture(Gdx.files.internal("data/knob.png"));
        TextureRegion TTouchBackground = 	new TextureRegion(tknob, 1, 1, 200, 200);
        TextureRegion TTouchKnob = 			new TextureRegion(tknob, 203, 55, 72, 72);
        TextureRegion TTblock = 				new TextureRegion(tknob, 203, 129, 72, 72);
        
        //Create a touchpad skin    
      //  touchpadSkin = new Skin();
        //Set background image
      //  touchpadSkin.add("touchBackground",  TTouchBackground);
        //Set knob image
     //   touchpadSkin.add("touchKnob", TTouchKnob);
        //Create TouchPad Style
     //   touchpadStyle = new TouchpadStyle();
        //Create Drawable's from TouchPad skin
    //    touchBackground = touchpadSkin.getDrawable("touchBackground");
   //     touchKnob = touchpadSkin.getDrawable("touchKnob");
      //  touchKnob.
        //Apply the Drawables to the TouchPad Style
      //  touchpadStyle.background = touchBackground;
     //   touchpadStyle.knob = touchKnob;
        //Create new TouchPad with the created style
     //   touchpad = new Touchpad(8, touchpadStyle);
        //setBounds(x,y,width,height)
     //   touchpad.setBounds(15, 15, Gdx.graphics.getHeight()/3, Gdx.graphics.getHeight()/3); //tamanho touch 1/3 autura
        //touchpad.s
        //Create a Stage and add TouchPad
      
        
        
        
        
        
        
        
 //       stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true, batch);
        stage = new Stage();
        stage.addActor(tableL);  
        stage.addActor(tableR);       
        //stage.addActor(tableTop);
        
        Gdx.input.setInputProcessor(stage);
		
		
		map = new TmxMapLoader().load("data/maps/tiled/city2.tmx");
		world = new World(map);
		//MapBodyManager mp = new MapBodyManager(null, deltaTime, null, 0);
		mainMusic = Gdx.audio.newMusic(Gdx.files.internal("data/sounds/Adventure-Time.mp3"));
	    mainMusic.setLooping(true);
	    coinWave = Gdx.audio.newSound(Gdx.files.internal("data/sounds/coin.wav"));
	    jumpWave = Gdx.audio.newSound(Gdx.files.internal("data/sounds/jump.wav"));
	    
	    
		
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 64f);	//mod para 32 tam cada tile
		//renderer.getViewBounds.x;
		
		paralax = new Texture(Gdx.files.internal("data/Background01.png"));
		paralaxtr = new TextureRegion(paralax, 0, 0, 471,202);  //200 23
		//paralaxtr.
		

		// create an orthographic camera, shows us 30x20 units of the world
	
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 15f,15f);
		camera.update();
		
	}

	@Override
	public void render () {
		// clear the screen
		//System.out.println(renderer.getViewBounds());
		soundManager(world);
		
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Gdx.graphics.getFramesPerSecond();
		//boolean r=Gdx.graphics.isGL30Available();
		
		//fps=Gdx.graphics.getFramesPerSecond();

		// get the delta time
		//float deltaTime = Gdx.graphics.getDeltaTime();
		deltaTime = Gdx.graphics.getDeltaTime(); 

		
		// blockSprite.setX(blockSprite.getX() + touchpad.getKnobPercentX()*blockSpeed);
	     //   blockSprite.setY(blockSprite.getY() + touchpad.getKnobPercentY()*blockSpeed);
		
	  //      world.esquilo.position.x=(world.esquilo.position.x + touchpad.getKnobPercentX()*blockSpeed);
	        
		//world.pauseWorld();
				//ta errado tenho que arrumar
		
		
		
		// update the koala (process input, collision detection, position update)
		//zangs.updateKoala(deltaTime,map,enemy);
		//esq.stateTime += deltaTime;		//isso vai no update do esquilo
		//enemy.stateTime += deltaTime;
		
		// let the camera follow the koala, x-axis only
		
		if(world.esquilo.position.y>=5){	//se ele cair camera para de acompanhar
		
		camera.position.y = world.esquilo.position.y+(world.esquilo.height/2);
		}
		camera.position.x = world.esquilo.position.x+(world.esquilo.width/2);
		
		
		//camera.position.z = 100f;
		camera.update();
		
		// set the tile map rendere view based on what the
		// camera sees and render the map
		renderer.setView(camera);
		
		//spriteBatch.setProjectionMatrix(camera.combined);
		renderAll(deltaTime);
		
		
		//
		
		 stage.act(Gdx.graphics.getDeltaTime());     		// render scene2d  
	    stage.draw();										 // render scene2d  
	
	    world.update(deltaTime,map);
		soundManager(world);
	    //tableTop.
		
		
	}

	private void renderAll(float deltaTime) {
		
		//batch.draw(paralaxtr, camera.position.x-10, 0, 471/10, 202/10);		//tamanho do paralax 12x12 blocos de 64 pixels
		Batch batch = renderer.getBatch();	//mod
		renderer.render();
		//stage.act(Gdx.graphics.getDeltaTime());        
      //  stage.draw();
		
		
		batch.begin();
	
		//batch.end();
		
		
		renderCoins(deltaTime, batch);		// desenha o boomerang
		renderEnemy1(deltaTime, batch);
		renderPlayer(deltaTime, batch);		
		
		//renderHud(deltaTime, batch);
		batch.end();
		
	}
	
	
	private void renderPlayer(float deltaTime, Batch batch) {
		//if(world.boomerang.state==State.Boom && !world.boomerang.explosion.isAnimationFinished(deltaTime))	return;
		//System.out.println("time "+deltaTime);
		frame= world.esquilo.anima(deltaTime);

		if(world.esquilo.facesRight) {
			batch.draw(frame, world.esquilo.position.x, world.esquilo.position.y, world.esquilo.width, world.esquilo.height);
		} else {
			batch.draw(frame, world.esquilo.position.x + world.esquilo.width, world.esquilo.position.y, -world.esquilo.width, world.esquilo.height);
		}

		
		//System.out.println("PLayer posx: "+world.esquilo.position.x+(" posy: ")+world.esquilo.position.y);
		//
	}	
	
	
	private void renderCoins(float deltaTime, Batch batch) {
				//mod
		// based on the koala state, get the animation frame
		//world.enemy.facesRight=false;
		
		for(int e = 0; e < world.coins.size(); e++){
			frame = world.coins.get(e).anima(deltaTime);
			
		if(world.coins.get(e).isLive){
		
		batch.draw(frame, world.coins.get(e).position.x, world.coins.get(e).position.y, world.coins.get(e).width, world.coins.get(e).height);
		//batch.draw(frame, world.coin.position.x, world.coin.position.y, world.coin.width, world.coin.height);
		
		}
	
		
		}//fim do for
		
	}
		
	private void renderHud(float deltaTime, Batch batch) {


		//hud.digitos(15);
		
			//batch = new SpriteBatch();
			//frame = world.coins.get(e).anima(deltaTime);

		//batch.draw(hud.getDezena(world.coletadas), world.esquilo.position.x, world.esquilo.position.y+10,hud.width,hud.height);
		//batch.draw(frame, world.coin.position.x, world.coin.position.y, world.coin.width, world.coin.height);
		//batch.draw(hud.getUnidade(world.coletadas), world.esquilo.position.x+1, world.esquilo.position.y+10,hud.width,hud.height);
	
		//fim do for
		
	}
	
	
	
	private void renderEnemy1(float deltaTime, Batch batch) {
		
			//mod
		// based on the koala state, get the animation frame
		//world.enemy.facesRight=false;
		
		for(int e = 0; e < world.enemys.size(); e++){
			frame = world.enemys.get(e).anima(deltaTime);
		
		if(world.enemys.get(e).isLive){
			if(world.enemys.get(e).facesRight) {
				batch.draw(frame, world.enemys.get(e).position.x, world.enemys.get(e).position.y, world.enemys.get(e).width, world.enemys.get(e).height);
			} else {
				batch.draw(frame, world.enemys.get(e).position.x+world.enemys.get(e).width, world.enemys.get(e).position.y, -world.enemys.get(e).width, world.enemys.get(e).height);
			}
			
		//batch.draw(frame, world.enemys.get(e).position.x, world.enemys.get(e).position.y, world.enemys.get(e).width, world.enemys.get(e).height);
		//batch.draw(frame, world.coin.position.x, world.coin.position.y, world.coin.width, world.coin.height);
		
		}
	
		
		}//fim do for
		
		

	}
	
	private void soundManager(World world) {
		if(!mainMusic.isPlaying()){
			mainMusic.play();
		mainMusic.setVolume(0.55f);	//1 max 0 min
		coinWave.play();
		}
		
		if(world.playCoin){
		coinWave.play();
		//coinWave.setVolume(soundId, volume);
		world.playCoin=false;}
		
		if(world.playJump){
			jumpWave.play();
			//coinWave.setVolume(soundId, volume);
			world.playJump=false;}
		
		
	}
	
	public void ScreemControl(){
		
		
	}

	
	
	@Override
	public void dispose () {
		
	}

	@Override
	public void resize(int width, int height) {
		// stage.getViewport().update(width, height, true);
	
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}