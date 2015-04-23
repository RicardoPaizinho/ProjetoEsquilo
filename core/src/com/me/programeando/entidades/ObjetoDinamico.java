package com.me.programeando.entidades;

import com.badlogic.gdx.math.Vector2;

public class ObjetoDinamico {
	public enum State {	Walking,Dead,Teleporting, Jumping, Standing,		}
	public State state ;
		public float width;
		public float height;
		public final Vector2 position = new Vector2();
		public final Vector2 velocity = new Vector2();
		public float maxVelocity ;	//22f
		public float jumpVelocity ;  //33f
		public float acceleration = 5f;	//5f
		public float damping = 0.72f;	//0,99 deslizando no chao
		public float gravity = -1.9f;	//original 2
		public boolean facesRight = true;
		public boolean grounded = false;
		public boolean isLive = true;
		boolean isNear = false;
		float stateTime = 0;

}
