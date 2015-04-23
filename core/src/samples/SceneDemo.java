package samples;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import java.util.Random;

public class SceneDemo implements ApplicationListener {
	
	 private Jet[] jets;
	    private Stage stage;
	    
	    @Override
	    public void create() {        
	        stage = new Stage();
	        final TextureRegion jetTexture = new TextureRegion(new Texture("data/nave.png"));

	        
	        
	        jets = new Jet[10];
	        
	        // Create/seed our random number for positioning jets randomly
	        Random random = new Random();
	        
	        // Create 10 Jet objects at random on screen locations
	        for(int i = 0; i < 10; i++){
	            jets[i] = new Jet(jetTexture);
	            
	            //Assign the position of the jet to a random value within the screen boundaries
	            jets[i].setPosition(random.nextInt(Gdx.graphics.getWidth() - (int)jets[i].getWidth())
	                    , random.nextInt(Gdx.graphics.getHeight() - (int)jets[i].getHeight()));
	            
	            // Set the name of the Jet to it's index within the loop
	            jets[i].setName(Integer.toString(i));
	            
	            // Add them to the stage
	            stage.addActor(jets[i]);
	        }
	        
	        Gdx.input.setInputProcessor(stage);
	    }

    
   
   
    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void render() {    
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}