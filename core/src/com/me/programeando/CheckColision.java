package com.me.programeando;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.me.programeando.entidades.Coin;
import com.me.programeando.entidades.ObjetoDinamico;
import com.me.programeando.entidades.ObjetoDinamico.State;


public class CheckColision {
	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
	protected Rectangle newObject () {return new Rectangle();}};
	private Array<Rectangle> tiles = new Array<Rectangle>();
	private Coin coin;
	private boolean playCoin;
	public int coletadas;
	
	
public CheckColision(){


}	
	
	
//checa colisao de objeto dinamico com cenario
public void checkColisionPlayer(ObjetoDinamico od,TiledMap map, float deltaTime){
	if(deltaTime == 0) return;
	

	// check input and apply to velocity & state
	
	// apply gravity if we are falling
	od.velocity.add(0, od.gravity);
	
	//velocidade max caindo(y-) pra nao deixar a gravidade aumentar a velocidade infinitamente
	if(od.velocity.y<-od.maxVelocity){		
		od.velocity.y =-od.maxVelocity;
		}	
	
	// clamp the velocity to the maximum, x-axis only
	if(Math.abs(od.velocity.x) > od.maxVelocity) {
		od.velocity.x = Math.signum(od.velocity.x) * od.maxVelocity;
	}

	// clamp the velocity to 0 if it's < 1, and set the state to standign
	if(Math.abs(od.velocity.x) < 1) {
		od.velocity.x = 0;
		if(od.grounded) od.state = State.Standing;
	}

	
	//incrementa tempo
	//od.stateTime += deltaTime;	
	// multiply by delta time so we know how far we go
	// in this frame
	od.velocity.scl(deltaTime);

	// perform collision detection & response, on each axis, separately
	// if the od is moving right, check the tiles to the right of it's
	// right bounding box edge, otherwise check the ones to the left
	Rectangle odRect = rectPool.obtain();
	odRect.set(od.position.x, od.position.y, od.width, od.height);
	int startX, startY, endX, endY;
	if(od.velocity.x > 0) {
		startX = endX = (int)(od.position.x + od.width + od.velocity.x);
	} else {
		startX = endX = (int)(od.position.x + od.velocity.x);
	}
	startY = (int)(od.position.y);
	endY = (int)(od.position.y + od.height);
	getTiles(map,startX, startY, endX, endY, tiles);
	odRect.x += od.velocity.x;
	for(Rectangle tile: tiles) {
		if(odRect.overlaps(tile)) {
			od.velocity.x = 0;
			break;
		}
	}
	odRect.x = od.position.x;

	// if the od is moving upwards, check the tiles to the top of it's
	// top bounding box edge, otherwise check the ones to the bottom
	if(od.velocity.y > 0) {
		startY = endY = (int)(od.position.y + od.height + od.velocity.y);
	} else {
		startY = endY = (int)(od.position.y + od.velocity.y);
	}
	startX = (int)(od.position.x);
	endX = (int)(od.position.x + od.width);
	getTiles(map,startX, startY, endX, endY, tiles);
	//map.getLayers();
	odRect.y += od.velocity.y;
	for(Rectangle tile: tiles) {
		if(odRect.overlaps(tile)) {
			// we actually reset the od y-position here
			// so it is just below/above the tile we collided with
			// this removes bouncing :)
			if(od.velocity.y > 0) {
				od.position.y = tile.y - od.height;
				// we hit a block jumping upwards, let's destroy it!
				//TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(1);
				//layer.setCell((int)tile.x, (int)tile.y, null);
			} else {
				od.position.y = tile.y + tile.height;
				// if we hit the ground, mark us as grounded so we can jump
				od.grounded = true;
			}
			od.velocity.y = 0;
			break;
		}
	}
	rectPool.free(odRect);

	// unscale the velocity by the inverse delta time and set 
	// the latest position
	od.position.add(od.velocity);
	od.velocity.scl(1/deltaTime);

	// Apply damping to the velocity on the x-axis so we don't
	// walk infinitely once a key was pressed
	od.velocity.x *= od.damping;
if(od.velocity.y<0){	od.state= State.Jumping;	}
	
	
}
	

public boolean CheckColisionCoins(ObjetoDinamico player,ArrayList<Coin> coins, float deltaTime){
	if(deltaTime == 0) return false;
	playCoin=false;
	Rectangle odPlayer = rectPool.obtain();
	odPlayer.set(player.position.x, player.position.y, player.width, player.height);
	
	for(int e = 0; e < coins.size(); e++){
		coin= coins.get(e);
	Rectangle oeCoin = rectPool.obtain();
	oeCoin.set(coin.position.x, coin.position.y, coin.width,  coin.height);
	

	//player.velocity.scl(deltaTime);
	if(odPlayer.overlaps(oeCoin) && coin.isLive){
		coin.isLive=false;
		playCoin=true;
		coins.set(e, coin);
		coletadas=coletadas+1;
		//coins.isLive=false;
		
	}
	}
	return playCoin;
}
	

/*checa colisao de objeto dinamico com cenario
public void ColisionCoin(Boomerang od, Player odz,TiledMap map,  float deltaTime){
	if(deltaTime == 0) return;
	if(!od.isLive)	return;
	
	if(od.back){						//se ele pode voltar aplica a gravidade pra fazer a curva pra cima
	od.velocity.add(0, od.gravity);}
	
	Rectangle odRect = rectPool.obtain();
	odRect.set(od.position.x, od.position.y, od.width, od.height);
	Rectangle zangRect = rectPool.obtain();
	zangRect.set(odz.position.x, odz.position.y, odz.width, odz.height);
	
	
	if(!od.back){
		//if(odz.position.x == od.position.x)
		if(odRect.overlaps(zangRect)) {od.isLive=false;	}
	}
		
	// apply gravity if we are falling
	//od.velocity.add(0, od.gravity);
	
	//velocidade max caindo(y-) pra nao deixar a gravidade aumentar a velocidade infinitamente
	//if(od.velocity.y<-od.maxVelocity){		
	//	od.velocity.y =-od.maxVelocity;
	//	}	
	
	// clamp the velocity to the maximum, x-axis only
	if(Math.abs(od.velocity.x) > od.maxVelocity) {
		od.velocity.x = Math.signum(od.velocity.x) * od.maxVelocity;
	}

	// clamp the velocity to 0 if it's < 1, and set the state to standign
	//if(Math.abs(od.velocity.x) < 1) {
	//	od.velocity.x = 0;
	//	if(od.grounded) od.state = State.Standing;
	//}

	
	//incrementa tempo
	//od.stateTime += deltaTime;	
	// multiply by delta time so we know how far we go
	// in this frame
	od.velocity.scl(deltaTime);

	// perform collision detection & response, on each axis, separately
	// if the od is moving right, check the tiles to the right of it's
	// right bounding box edge, otherwise check the ones to the left
////////////////////////////////////////////////////////////////	Rectangle odRect = rectPool.obtain();
	odRect.set(od.position.x, od.position.y, od.width, od.height);
	int startX, startY, endX, endY;
	if(od.velocity.x > 0) {
		startX = endX = (int)(od.position.x + od.width + od.velocity.x);
	} else {
		startX = endX = (int)(od.position.x + od.velocity.x);
	}
	startY = (int)(od.position.y);
	endY = (int)(od.position.y + od.height);
	getTiles(map,startX, startY, endX, endY, tiles);
	odRect.x += od.velocity.x;
	
	od.canTele=true;
	for(Rectangle tile: tiles) {
		
		if(odRect.overlaps(tile)) {
//			colocar excessao aqui se ele esta aqui colidindo não pode teletransporte
			od.canTele=false;	
			
			break;
		}
		
	}
	odRect.x = od.position.x;

	// if the od is moving upwards, check the tiles to the top of it's
	// top bounding box edge, otherwise check the ones to the bottom
	if(od.velocity.y > 0) {
		startY = endY = (int)(od.position.y + od.height + od.velocity.y);
	} else {
		startY = endY = (int)(od.position.y + od.velocity.y);
	}
	startX = (int)(od.position.x);
	endX = (int)(od.position.x + od.width);
	getTiles(map,startX, startY, endX, endY, tiles);
	odRect.y += od.velocity.y;
	
	
	for(Rectangle tile: tiles) {
		if(odRect.overlaps(tile)) {
			od.canTele=false;	
			break;
//			
		}
	}	
	
	
	rectPool.free(odRect);
	rectPool.free(zangRect);
	

	// unscale the velocity by the inverse delta time and set 
	// the latest position
	od.position.add(od.velocity);
	od.velocity.scl(1/deltaTime);

	// Apply damping to the velocity on the x-axis so we don't
	// walk infinitely once a key was pressed
	//od.velocity.x *= od.damping;

}	
	
	*/
	
	private void getTiles(TiledMap map, int startX, int startY, int endX, int endY, Array<Rectangle> tiles) {
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);		//1
		Cell cell;		//otimizar ???
		rectPool.freeAll(tiles);
		tiles.clear();
		for(int y = startY; y <= endY; y++) {
			for(int x = startX; x <= endX; x++) {
				cell = layer.getCell(x, y);
				if(cell != null) {
					Rectangle rect = rectPool.obtain();
					rect.set(x, y, 1, 1);
					tiles.add(rect);
				}
			}
		}
	}
	

	
	
	//return false;

	public float distancia(ObjetoDinamico od1, ObjetoDinamico od2){
//		Teorema de pitagoras. Os catetos medem x2-x1 e y2-y1. É só somar os quadrados deles e tirar a raiz:
//		coloque no começo do programa esse import:

	//	Depois calcule assim:
	//	double distancia = sqrt( pow(P1.x - P2.x, 2) + pow(P1.y - P2.y, 2) );
	//	sqrt é raiz quadrada. E pow é potencia. Elevamos ao quadrado ^
		
		//ObjetoDinamico od1
		
		float distancia = (float) Math.sqrt( Math.pow(od1.position.x - od2.position.x, 2) + Math.pow(od1.position.y - od2.position.y, 2) );
		
		return distancia;
	}


	//public void checkColisionCenarioOLD(ArrayList<Enemy1> enemys, TiledMap map,float deltaTime) {
	//	for(int e = 0; e < enemys.size(); e++){
	//		checkColisionPlayer(enemys.get(e),map,deltaTime);
			
	//	}
		// TODO Auto-generated method stub
		
	//}
	
	
	
	
	
	
	
}
