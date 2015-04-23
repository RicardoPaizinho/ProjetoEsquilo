package com.me.programeando;



import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.me.programeando.entidades.Coin;
import com.me.programeando.entidades.IAenemy;
import com.me.programeando.entidades.Mosquito;
import com.me.programeando.entidades.ObjetoDinamico;
import com.me.programeando.entidades.Player;
import com.me.programeando.entidades.ObjetoDinamico.State;


public class World  {

	Player esquilo;
	ArrayList<Mosquito> enemys;		//array e como uma pilha tem q add e retirar os elementos
	ArrayList<Coin> coins;
	public boolean playCoin;
	public boolean playJump;
	public boolean JUMP,RIGHT,LEFT,ACTION;
	public int coletadas=0;
	boolean released;			// é assim q se escreve?
	
	CheckColision c = new CheckColision();
	IAenemy ia = new IAenemy();
	public boolean isRun;

    //private ArrayList<Rocket> rockets;
	
	
	
	public World(TiledMap map){
		isRun=true;
		esquilo = new Player();
			//iniciando variaveis
		
		coins = new ArrayList<Coin>();
		enemys = new ArrayList<Mosquito>();
		
MapLayer ml = map.getLayers().get(2);
		if(ml == null) return;
		for(MapObject mo : ml.getObjects()) {
			//name="coin" type="coin" x="768" y="448" width="52" height="48"
			float x =  ((Float) (mo.getProperties().get("x")) /64 )   ;		//tem q dividirr por 64 que é o tamanho dos tiles
			float y =  ((Float) (mo.getProperties().get("y")) /64 ) ;
		//	float w =  (Float) mo.getProperties().get("width") ;
		//	float h =  (Float) mo.getProperties().get("height") ;
		//	String a = (String) mo.getProperties().get("name") ;
			String b = (String) mo.getProperties().get("type") ;
			//System.out.print("x:"+x+", y:"+y+" nome:"+a);
			//System.out.println(" width:"+w+", height:"+h+" type:"+b);
			
			//mo.
			//cdef.position.set(x, y);
			if(b.equals("player")){								
				esquilo.position.set(x, y);
				
				}
			
			if(b.equals("coin")){
			Coin c = new Coin();
			c.position.set(x,y);
			c.isLive=true;
			coins.add(c);}
			if(b.equals("enemy1")){
			Mosquito e = new Mosquito();				//mooooooooooooooooooooooodddddddddddddddddd
			e.position.set(x,y);
			e.isLive=true;
			enemys.add(e);
			}
			
			
			
			
		}
		}
	
	
	
	public void update(float deltaTime, TiledMap map){
		
		LeTeclas(esquilo);      
		c.checkColisionPlayer(esquilo,map,deltaTime);
		ia.UpdateIAenemy(enemys, esquilo, map, deltaTime);  
		
		//	c.checkColisionCenario2(enemys, map, deltaTime);
		coletadas = c.coletadas;
		playCoin = c.CheckColisionCoins(esquilo, coins, deltaTime);		
		coletadas = c.coletadas;
		
	}
	
	
	private void LeTeclas(ObjetoDinamico odz){
			
		if(Gdx.input.isKeyPressed(Keys.P) ) {	//&& koala.grounded
			System.out.println("PLayer posx: "+esquilo.position.x+(" posy: ")+esquilo.position.y);
			
		}
		
		//if(Gdx.input.isKeyPressed(Keys.Q) ) {	//&& koala.grounded
		//	od.position.set(5, 100);
		//}
		
		
		
		// check input and apply to velocity & state
		if(Gdx.input.isKeyPressed(Keys.SPACE ) || JUMP   ) {	//&& released
	
	
			if(odz.grounded ){
				odz.velocity.y += odz.jumpVelocity;
				odz.state = State.Jumping;
				odz.grounded = false;
				playJump=true;
				released = false;
				}
			
		}
		
		if((Gdx.input.isKeyPressed(Keys.SPACE)  ) == false) {
		//	released = true;

		}
		
		

		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A) || LEFT ) {
			if(odz.velocity.x>-odz.maxVelocity){
				odz.velocity.add(-odz.acceleration,0);	}
			
			if(odz.grounded) odz.state = State.Walking;
			odz.facesRight = false;
		}

		if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D) || RIGHT) {
			if(odz.velocity.x<odz.maxVelocity){
				odz.velocity.add(odz.acceleration,0);  }
							
			if(odz.grounded) odz.state = State.Walking;
			odz.facesRight = true;
		}
		
		
	}
	
	
	/*private void Comandos(){
		if(Gdx.input.getInputProcessor().touchDown(screenX, screenY, pointer, button)) ) {	//&& koala.grounded
			System.out.println("PLayer posx: "+esquilo.position.x+(" posy: ")+esquilo.position.y);
			
		}
		  public boolean touchDown (int x, int y, int pointer, int button) {
		      // your touch down code here
		      return true; // return true to indicate the event was handled
		   }

		   public boolean touchUp (int x, int y, int pointer, int button) {
		      // your touch up code here
		      return true; // return true to indicate the event was handled
		   }
		}
	*/
	
	
	
	private void leObjectos(TiledMap map, ArrayList<Coin> coins) {
	//coins = new ArrayList<Coin>();
	//Map tileMap;
	//coins = new Array<Coin>();
		
		
		MapLayer ml = map.getLayers().get(2);
		
		//map.getLayers()
		if(ml == null) return;
		
		for(MapObject mo : ml.getObjects()) {
			
		//.	MapLayer layer = map.getLayers().get(2);
		//.	MapObjects objects = layer.getObjects();
		//.	System.out.println(objects.getCount());		//numero de objetos no layer
			
			//for(MapObject mo : ml.getObjects()) {
			//BodyDef cdef = new BodyDef();
			//cdef.type = BodyType.StaticBody;
			//mo.
			
			//name="coin" type="coin" x="768" y="448" width="52" height="48"
			float x =  ((Float) mo.getProperties().get("x")/64f)   ;		//tem q dividirr por 64 que é o tamanho dos tiles
			float y =  ((Float) mo.getProperties().get("y")/64f) ;
			float w =  (Float) mo.getProperties().get("width") ;
			float h =  (Float) mo.getProperties().get("height") ;
			String a = (String) mo.getProperties().get("name") ;
			String b = (String) mo.getProperties().get("type") ;
			
			System.out.print("x:"+x+", y:"+y+" nome:"+a);
			System.out.println(" width:"+w+", height:"+h+" type:"+b);
			


		}
		
	}
	
	
	private void dead(){
		if(esquilo.position.y>=5){	//verifica se caiu em buraco
			esquilo.state = State.Dead;
			
			}
			
		
		
		
	}
	
	
	public void pauseWorld(){
		
		if(Gdx.input.isKeyJustPressed(Keys.Q) ) {	//&& koala.grounded
			
			 if (isRun==false){
					isRun=true; return;}
			
			if(isRun){
			isRun=false; return;}
		
		}
	}
	
	/*
	private void UpdateBoomerang(Boomerang odb, ObjetoDinamico odz){
		if(Gdx.input.isKeyPressed(Keys.SPACE)  ) {	//&& released
			
			if(boomerang.isLive && boomerang.canTele){
				zangado.position.x= boomerang.position.x;
				zangado.position.y= boomerang.position.y;
				boomerang.isLive=false;
				boomerang.state= State.Boom;
				boomerang.stateTime=0;
				if(zangado.state == State.Walking){zangado.state = State.Standing;}
				
				
				}}
		
	//	if(!boomerang.isLive){boomerang.state= State.Boom;}
		
		if(odb.facesRight && odb.back)  {	//esta indo pra direita e tem q voltar
		odb.velocity.x = odb.maxVelocity;
		}
		
		if((!odb.facesRight) && odb.back)  {
			odb.velocity.x = -odb.maxVelocity;
		}
		
		//condicoes: ele pode voltar, ta indo pra direita e chegou na posicao de voltar
		if(odb.back && odb.facesRight && odb.position.x >= zangado.position.x+7){odb.back=false;}
		
		if(odb.back && !odb.facesRight && ( zangado.position.x-7>=odb.position.x)){odb.back=false;}
		
		
		//od.position.set(5, 100);
			//boomerang. 
			//zangado.walk.isAnimationFinished(zangado.stateTime);
			//od.state = State.Boom;
		
		if(odb.isLive && !odb.back)  {		//se back é falso ele ja esta voltando	
			
			
			
			//1  10
			if(odb.position.x - odz.position.x >1){
				odb.velocity.x = -odb.maxVelocity;			}
			if(odb.position.x - odz.position.x <1){
				odb.velocity.x = odb.maxVelocity;			}
			//if(odb.position.x == odz.position.x){
			//	odb.velocity.x = 0;			}
			
			 
			if(odb.position.y - odz.position.y >1 ){			//modificar depois não ta legalzinho
				odb.velocity.y = -odb.maxVelocity;			}
			if(odb.position.y - odz.position.y <1){
				odb.velocity.y = odb.maxVelocity;			}
			else{
				odb.velocity.y = 0;			}
			
			
			}
			
		
		//	if(!odb.facesRight && !odb.back)  {
		//		odb.velocity.x = odb.maxVelocity;
		//	}
			
		
		//if(odb.velocity.y >= odb.maxVelocity){
		//	odb.velocity.y = odb.maxVelocity;
		//}
		
		
	}	
		
	
	*/
	
}
