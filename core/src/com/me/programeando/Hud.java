package com.me.programeando;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Hud extends Actor {
	private TextureRegion[] font;
	public float width;
	public float height;
	TextureRegion digitos;
	AtlasRegion numeros;
	TextureAtlas atlas;
	int dezenas;
	int unidades;

	public  Hud(){
		//cria as texturas 128 largura* 190 altura
		
		font = new TextureRegion[10];
		
		atlas = new TextureAtlas(Gdx.files.internal("data/texture/hud.atlas"));
		
		 numeros = atlas.findRegion("numeros");

			for(int i = 0; i < 5; i++) {
			font[i] = new TextureRegion(numeros,  i * 128, 0, 128, 190);
		}
		for(int i = 0; i < 5; i++) {
			font[i + 5] = new TextureRegion(numeros, i * 128, 190, 128, 190);
		}
	
		this.width = 1 / 80f * font[0].getRegionWidth();		//16
		this.height = 1 / 80f *font[0].getRegionHeight();
		
		

	}
	
	//@Override
  //  public void draw(Batch batch, float alpha){
       // Texture texture;
		//batch.draw(texture,0,0);
	
//}
	

	
	/*public TextureRegion[] digitos(int num){
		digitos = new TextureRegion[2];

		//centenas  = num / 100;  
		  
		//O que sobrar depois que tiramos as centenas, são as dezenas  
		dezenas = (num%100) / 10;  
		  
		//O que sobrar depois que tiramos as centenas e dezenas são as unidades  
		unidades = ((num%100)%10);  
		
		//stateTime += deltaTime;
		System.out.println("dezenas:"+dezenas+", unidades:"+unidades);
		digitos[0]= font[dezenas]	;			//	font[dezenas];
		digitos[1]= font[unidades]	;				//font[unidades];
		
		return digitos;
	}
	*/
	
	public TextureRegion getDezena(int num){
		dezenas = (num%100) / 10;  
		
		
		return font[dezenas];
	}
	
	public TextureRegion getUnidade(int num){
		unidades = ((num%100)%10);  
		
		
		return font[unidades];
	}
	
		//chamando
		//drawString(sb, player.getNumCrystals() + " / " + player.getTotalCrystals(), 132, 211);
		
		//190*128
		//criando
		
		
		

		
	
	
	
	
}
