package game.control;

import org.newdawn.slick.geom.Rectangle;

import game.objects.GameObject;

//Classe que age como um armário de parametros

public class GameParams {
	//Nome do jogo
	public static final String	gameName 	= "Space Rush!";
	
	//id dos estados
	public static final int		menu		= 0;
	public static final int 	play		= 1;	
	public static final int 	gameOver	= 2;
	public static final int     gameWin 	= 3;


	//Tamanho da tela
	public static final int 	screenX		= 1280;
	public static final int 	screenY		= 720;
	
	//Parametros gerais do jogo
	public static final int		shootRate		= 500;
	public static final int		shootSpeed		= 2; //Quanto menos mais rápido
	public static final int		finish			= 42;
	public static final int		bossTime		= 1000;
	public static final int		spawnRate		= 400;
	public static final int		moveSpeed		= GameParams.screenY/500;
	public static final int		astroOffset 	= 10;
	public static final long    bossShotTimer 	= 300;
	public static final int     astroScore 		= 10;
	public static final int		bossLife		= 1;

	//Pontuação do jogador
	public static int			score = 0;
	
	//Modo de depuração, ou quase isso
	public static boolean		debug = false;
	//Par de métodos que mapeiam a tela em função de um valor 
	//que vai de 0 a 100 e retorna um ponto no eixo especificado
	public static float mapScreenY(float value){
		if((value>=0)&&(value<=100))
			return screenY/100 * value;
		else 
			return -1;
	}
	public static float mapScreenX(float value){
		if((value>=0)&&(value<=100))
			return screenX/100 * value;
		else 
			return -1;
	}
	
	
	//Metodo que identifica a colisão de dois objetos do jogo
	public static boolean trigger(GameObject shoot, GameObject astro){
		Rectangle box1	= shoot.getBox();
		Rectangle box2	= astro.getBox();
		
		if(box1.intersects(box2)){
			return true;
		}else{
			return false;
		}
	}
}
