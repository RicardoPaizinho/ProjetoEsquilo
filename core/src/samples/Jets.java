package samples;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

 // Create an Actor "Jet" that displays the TextureRegion passed in
class Jet extends Actor {
    private TextureRegion textura;
    
    public Jet(TextureRegion texture){
        textura = texture;
        setBounds(getX(),getY(),texture.getRegionWidth(), texture.getRegionHeight());
        
        this.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons){
                System.out.println("Touched" + getName());
                setVisible(false);
                return true;
            }
        });
    }

    // Implement the full form of draw() so we can handle rotation and scaling.
    public void draw(Batch batch, float alpha){
        batch.draw(textura, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                getScaleX(), getScaleY(), getRotation());
    }
    
    // This hit() instead of checking against a bounding box, checks a bounding circle.
    public Actor hitss(float x, float y, boolean touchable){
        // If this Actor is hidden or untouchable, it cant be hit
        if(!this.isVisible() || this.getTouchable() == Touchable.disabled)
            return null;
        
        // Get centerpoint of bounding circle, also known as the center of the rect
        float centerX = getWidth()/2;
        float centerY = getHeight()/2;
        
        // Square roots are bad m'kay. In "real" code, simply square both sides for much speedy fastness
        // This however is the proper, unoptimized and easiest to grok equation for a hit within a circle
        // You could of course use LibGDX's Circle class instead.
        
        // Calculate radius of circle
        float radius = (float) Math.sqrt(centerX * centerX +
                centerY * centerY);

        // And distance of point from the center of the circle
        float distance = (float) Math.sqrt(((centerX - x) * (centerX - x)) 
                + ((centerY - y) * (centerY - y)));
        
        // If the distance is less than the circle radius, it's a hit
        if(distance <= radius) return this;
        
        // Otherwise, it isnt
        return null;
    }
}
