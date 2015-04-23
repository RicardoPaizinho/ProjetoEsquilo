package samples;

public class ProjetilAngle {

	//seta anguloe velocidade inicial
	float angle=30;
	float speed=20;
	
	//calcuça x e y escalas, o xey equivalente ao angulo
	double scale_x = Math.cos(angle) ;
	double scale_y = Math.sin(angle);
	
	float velocity_x = (float) (speed* scale_x);
	float velocity_y = (float) (speed* scale_y); //calcula velocidade de acordo com tempo
	
	public void loop(){
		//movingObject.x = movingObject.x + velocity_x;
		//movingObject.y = movingObject.y + velocity_y;
	}
			
			
			
}
