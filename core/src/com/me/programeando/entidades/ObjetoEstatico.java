package com.me.programeando.entidades;

import com.badlogic.gdx.math.Vector2;

public class ObjetoEstatico {
		public float width;
		public float height;
		public final Vector2 position = new Vector2();
		final Vector2 velocity = new Vector2();
		public boolean isLive = true;
		boolean isPlay = false;
		float stateTime = 0;

}
