package touchControl;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;



public class TouchTest extends Actor {
	 private OrthographicCamera camera;
	    private Stage stage;
	    private SpriteBatch batch;
	    private Touchpad touchpad;
	    private TouchpadStyle touchpadStyle;
	    private Skin touchpadSkin;

	    private Sprite blockSprite;
	    private float blockSpeed;
		private Drawable touchBackground;
		private Drawable touchKnob;
	//	private AtlasRegion TTouchBackground;
	//	private AtlasRegion TTouchKnob;
	//	private AtlasRegion TTblock;
	 
	    //construtor
	    public TouchTest() {        
	       // batch = new SpriteBatch();
	        //Create camera
	       // float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
	       // camera = new OrthographicCamera();
	       // camera.setToOrtho(false, 10f*aspectRatio, 10f);
	        
	        Texture tknob = new Texture(Gdx.files.internal("data/knob.png"));
	        TextureRegion TTouchBackground = 	new TextureRegion(tknob, 1, 1, 200, 200);
	        TextureRegion TTouchKnob = 			new TextureRegion(tknob, 203, 55, 72, 72);
	        TextureRegion TTblock = 				new TextureRegion(tknob, 203, 129, 72, 72);
	        
	        //Create a touchpad skin    
	        touchpadSkin = new Skin();
	        //Set background image
	        touchpadSkin.add("touchBackground",  TTouchBackground);
	        //Set knob image
	        touchpadSkin.add("touchKnob", TTouchKnob);
	        //Create TouchPad Style
	        touchpadStyle = new TouchpadStyle();
	        //Create Drawable's from TouchPad skin
	        touchBackground = touchpadSkin.getDrawable("touchBackground");
	        touchKnob = touchpadSkin.getDrawable("touchKnob");
	      //  touchKnob.
	        //Apply the Drawables to the TouchPad Style
	        touchpadStyle.background = touchBackground;
	        touchpadStyle.knob = touchKnob;
	        //Create new TouchPad with the created style
	        touchpad = new Touchpad(8, touchpadStyle);
	        //setBounds(x,y,width,height)
	        touchpad.setBounds(15, 15, Gdx.graphics.getHeight()/3, Gdx.graphics.getHeight()/3); //tamanho touch 1/3 autura
	        //touchpad.s
	        //Create a Stage and add TouchPad
	      
	        
	        
	        
	        
	        
	        
	        
	 //       stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true, batch);
	        stage = new Stage();
	        stage.addActor(touchpad);       
	        
	        Gdx.input.setInputProcessor(stage);
	 
	        //Create block sprite
	        
	        blockSprite = new Sprite(TTblock);
	        //Set position to centre of the screen
	        blockSprite.setPosition(Gdx.graphics.getWidth()/2-blockSprite.getWidth()/2, Gdx.graphics.getHeight()/2-blockSprite.getHeight()/2);
	 
	        blockSpeed = 4;
	    }
	 

	 
	   
	    public void update() {        
	        Gdx.gl.glClearColor(0.294f, 0.294f, 0.294f, 1f);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        camera.update();
	 
	        //Move blockSprite with TouchPad
	  
	        blockSprite.setX(blockSprite.getX() + touchpad.getKnobPercentX()*blockSpeed);
	        blockSprite.setY(blockSprite.getY() + touchpad.getKnobPercentY()*blockSpeed);
	 
	        //Draw
	        batch.begin();
	        blockSprite.draw(batch);
	        batch.end();      
	        stage.act(Gdx.graphics.getDeltaTime());        
	        stage.draw();
	 
	    }
	 

	}

