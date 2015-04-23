package samples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Robo {
	public static final int PARADO_DIR	= 0;			//	2
	public static final int PARADO_ESQ	= 1;			//	inv2
	public static final int PARADO_CIMA	= 2;			// 1
	public static final int PARADO_BAIXO= 3;			// 2
	public static final int ANDANDO_DIR	= 4;			// inv 6
	public static final int ANDANDO_ESQ	= 5;			// 6
	public static final int ANDANDO_CIMA	= 6;		// 6
	public static final int ANDANDO_BAIXO	= 7;		// 6
	public static final int ATIRANDO	= 8;			// 2
	public static final int ESCUDO	= 9;				//
	public static final int MORRENDO		= 10;		// 1
	public static final int OBJETIVO		= 11;		// 2
	public int estado=0;
	
    int     x,y;     		    // #1
	int 	dx,dy;				//deslocamento x e y tente q validar primeiro cons a colisao e depois repassar para x ey para desenhar
boolean		LEFT;	//teclas
boolean   	RIGHT; 
boolean 	UP;
boolean   	DOWN;
boolean 	SPACE;
boolean 	Z;
	
	
    Animation                       walkAnimation;          // #3
    Texture                         proto1;              // #4
    TextureRegion[]                 walkFrames;             // #5
    SpriteBatch                     spriteBatch;            // #6
    TextureRegion                   currentFrame,TR;           // #7
	Texture frame0;
	TextureRegion[][] BBB;
	private Animation andandoesq,andandodir;
	private Animation andandocima,andandobaixo;
	private Animation paradocima,paradobaixo;
	private Animation paradodir,paradoesq;
	
    public void RoboInit(){
		//walkSheet = new Texture(Gdx.files.internal("data/animation_sheet.png"));
		proto1 = new Texture(Gdx.files.internal("data/robo11.png"));
		
		TR = new TextureRegion(proto1, 0, 0, 240, 228); 	//30*38... 38*6=228	30*8=240
		//TextureRegion[][] tmp = TextureRegion.split(frame0, 30, 38); 
		//TextureRegion[][] AAAA =TR.split(30,38);
		
		BBB=  TR.split(30,38);
			
		
		//frame5 = new TextureRegion(proto1, 49, 80, 50, 80);
		//frame6 = new TextureRegion(proto1, 99, 80, 50, 80);
		//walkFrames = new TextureRegion[2];	//3 frames de imagens
		x+=dx;
		y+=dy;
		//walkFrames[0] = frame4;
		//walkFrames[1] = frame6;	
		//walkFrames[2] = frame5;
		//if(LEFT)
        andandoesq = new Animation(0.125f, BBB[2][0],BBB[2][1],BBB[2][2],BBB[2][3],BBB[2][4],BBB[2][5]); 	
        andandodir = new Animation(0.125f, BBB[5][0],BBB[5][1],BBB[5][2],BBB[5][3],BBB[5][4],BBB[5][5]);
        andandocima = new Animation(0.125f, BBB[3][0],BBB[3][1],BBB[3][2],BBB[3][3],BBB[3][4],BBB[3][5]); 	
        andandobaixo = new Animation(0.125f, BBB[1][0],BBB[1][1],BBB[1][2],BBB[1][3],BBB[1][4],BBB[1][5]);
        paradocima = new Animation(0.525f,BBB[0][2]);
        paradobaixo = new Animation(0.925f,BBB[0][3]);
        paradodir = new Animation(0.525f,BBB[0][4]);
        paradoesq = new Animation(0.525f,BBB[0][1]);
        
        
        // ...Significa um número variável de argumentos. 
        //Ou seja, se o método recebe String... pode receber uma String, duas, tres,
        //quatro, quantas forem. O runtime automaticamente converte esses argumentos em um array que pode ser iterado dentro do corpo do método.
        
    	estado=PARADO_BAIXO;
    } 
    
	public TextureRegion AnimaRobo(float stateTime){
		ProcessaTeclas();
		//currentFrame=BBB[0][1];
		if(estado==ANDANDO_ESQ)currentFrame = andandoesq.getKeyFrame(stateTime, true);
		if(estado==ANDANDO_DIR)currentFrame = andandodir.getKeyFrame(stateTime, true);
		if(estado==ANDANDO_CIMA)currentFrame = andandocima.getKeyFrame(stateTime, true);
	    if(estado==ANDANDO_BAIXO)currentFrame = andandobaixo.getKeyFrame(stateTime, true);
    
		if(estado==PARADO_DIR)currentFrame = paradodir.getKeyFrame(stateTime, true);    
		if(estado==PARADO_ESQ)currentFrame = paradoesq.getKeyFrame(stateTime, true);    
		if(estado==PARADO_CIMA)currentFrame = paradocima.getKeyFrame(stateTime, true);    
		if(estado==PARADO_BAIXO)currentFrame = paradobaixo.getKeyFrame(stateTime, true);    
        return currentFrame;
	}
	
	
	public void ProcessaTeclas(){
		// TEsta as teclas
		if(UP){			y += 130 * Gdx.graphics.getDeltaTime();				estado = ANDANDO_CIMA;}
		if(UP==false && estado == ANDANDO_CIMA )							estado = PARADO_CIMA;
		
		if(DOWN){		y -= 130 * Gdx.graphics.getDeltaTime();				estado = ANDANDO_BAIXO;}
		if(DOWN==false && estado == ANDANDO_BAIXO )							estado = PARADO_BAIXO;
		
		if(LEFT){			x -= 130 * Gdx.graphics.getDeltaTime();			estado = ANDANDO_ESQ;}
		if(LEFT==false && estado == ANDANDO_ESQ )							estado = PARADO_ESQ;
			
		if(RIGHT){			x += 130 * Gdx.graphics.getDeltaTime();			estado = ANDANDO_DIR;}
		if(RIGHT==false && estado == ANDANDO_DIR )							estado = PARADO_DIR;
		
		
		//if(Z)			tiro=true;
		//else			tiro=false;
	//	if(Gdx.input.isKeyPressed(Keys.LEFT)) r.x -= 150 * Gdx.graphics.getDeltaTime();
       // if(Gdx.input.isKeyPressed(Keys.RIGHT)) r.x += 150 * Gdx.graphics.getDeltaTime(); 
       // if(Gdx.input.isKeyPressed(Keys.UP)) r.y += 150 * Gdx.graphics.getDeltaTime();
       // if(Gdx.input.isKeyPressed(Keys.DOWN)) r.y -= 150 * Gdx.graphics.getDeltaTime();
       // if(Gdx.input.isKeyPressed(Keys.SPACE)) r.y -= 150 * Gdx.graphics.getDeltaTime();
       // if(Gdx.input.isKeyPressed(Keys.Z)) r.y -= 150 * Gdx.graphics.getDeltaTime();
        
	}
	
	public void animaElemento(int dx, int dy)
	{
		// Máquina de estados do jogador
		switch(estado)
		{
			case PARADO_DIR:
				if(dx > 0 && dy == 0)
					estado = ANDANDO_DIR;
				else if(dx<0 && dy == 0)
					estado = ANDANDO_ESQ;
				
				break;
				
			case PARADO_ESQ:
				if(dx > 0 && dy == 0)
					estado = ANDANDO_DIR;
				else if(dx<0 && dy == 0)
					estado = ANDANDO_ESQ;
				
				break;

			case ANDANDO_DIR:
				if(dx == 0 && dy == 0)
					estado = PARADO_DIR;
				else if(dx<0 && dy == 0)
					estado = ANDANDO_ESQ;
				break;

			case ANDANDO_ESQ:
				if(dx == 0 && dy == 0)
					estado = PARADO_ESQ;
				else if(dx>0 && dy == 0)
					estado = ANDANDO_DIR;
				
				break;

			
		}
		
	//	if(tquadro++ >= 2)
	//	{
			// Incremento o quadro
		//	quadro++;
		//	tquadro = 0;	
	//	}
		// Valido se o quadro é consistente com o estado
		/*if(quadro >= qtdAnim[estado])
			quadro = 0 ;*/
	//	quadro = quadro % qtdAnim[estado];
		
	//	setFrame(vetorAnim[idxAnim[estado]+quadro]);
	}
	
	
}
