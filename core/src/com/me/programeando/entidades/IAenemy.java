package com.me.programeando.entidades;

import java.util.ArrayList;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.me.programeando.CheckColision;

public class IAenemy {
	CheckColision c = new CheckColision();
	
	//algoritmo de perseguição
	public void UpdateIAenemy (ArrayList<Mosquito> enemys,ObjetoDinamico player,TiledMap map, float deltaTime){
		
		float distancia = 0;
		
		
		for(int e = 0; e < enemys.size(); e++){
			distancia = c.distancia(enemys.get(e), player);
			
			
			if(player.position.x>  enemys.get(e).position.x  && distancia<12 ){
				enemys.get(e).velocity.add(enemys.get(e).acceleration,0);	
				enemys.get(e).facesRight=true;
				c.checkColisionPlayer(enemys.get(e),map,deltaTime);
			}
			else if(player.position.x<  enemys.get(e).position.x  && distancia<12 ){
				enemys.get(e).velocity.add(-enemys.get(e).acceleration,0);	
				enemys.get(e).facesRight=false;
				c.checkColisionPlayer(enemys.get(e),map,deltaTime);
			}
			//if(distancia<4 && enemys.get(e).grounded){
				//odz.velocity.y += odz.jumpVelocity;
				//enemys.get(e).state = State.Jumping;
				//enemys.get(e).grounded = false;
				//enemys.get(e).velocity.add(0,enemys.get(e).jumpVelocity);	
				//enemys.get(e).maxVelocity=10f;
				//enemys.get(e).acceleration=3.8f;
		//	}
			
			//mesmo update do player checa loliso cenario e aplica gravidade
			
		}
		
		//12-15 é uma boa distancia pra ativar ia
		//<>2-3 boa distancia para atacar fisico
		
		//c.checkColisionPlayer(enemy,map,deltaTime);	
		//float distancia = c.distancia(enemys.get(5), player);
		//System.out.println(distancia);
		//if (player.x>x and distance_to_object(player))<250
		//{x+=4//vai para a direita
		
		
		
	}
		
	
	/*if player.x>x and distance_to_object(player)<250
		{x+=4//vai para a direita

		if player.x<x and distance_to_object(player)<250
		{	x-=4// vai para a esquerda

		
				// se o nimigo estiver muito perto do player  ele ataca
		if player.x>x and distance_to_object(player)<10
		sprite_index= spr_inimigo_atacando//
		}

		if player.x<x and distance_to_object(player)<10
		{
		sprite_index= spr_inimigo_atacando// ele ataca
		}
		
		
		
		
	}
	if(odz.velocity.x>-odz.maxVelocity){
		odz.velocity.add(-odz.acceleration,0);	}
	
	if(odz.grounded) odz.state = State.Walking;
	odz.facesRight = false;
}

if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D) ) {
	if(odz.velocity.x<odz.maxVelocity){
		odz.velocity.add(odz.acceleration,0);  }
					
	if(odz.grounded) odz.state = State.Walking;
	odz.facesRight = true;
	
	if player.x>x and distance_to_object(player)<250//se a distancia do inimigo com o player for menor que 250
	{
	x+=4//vai para a direita

	}

	if player.x<x and distance_to_object(player)<250//se a distancia do inimigo com o player for maior que 250
	{
	x-=4// vai para a esquerda

	}

	if player.x>x and distance_to_object(player)<10// se o nimigo estiver muito perto do player 
	{
	sprite_index= spr_inimigo_atacando// ele ataca
	}

	if player.x<x and distance_to_object(player)<10// se o nimigo estiver muito perto do player
	{
	sprite_index= spr_inimigo_atacando// ele ataca
	}
	if place_free(x,y+1)// se estiver livre de colisões
	gravity=0.5//a gravidade é 0.5
	else//senao
	gravity=0//é 0
	*/
	
}
