package game.levels;

public class Level {
	
	
	
	private int[][][] levelDev = {{ {1,1,1,1,1,1,1,1,1,1,1},
									{1,1,1,1,1,1,1,1,1,1,1},
									{0,1,1,1,1,1,1,1,1,1,1},
									{0,1,1,1,1,1,1,1,1,1,1},
									{0,1,1,1,1,1,1,1,1,1,1},
									{0,1,1,1,1,1,1,1,1,1,1},
									{0,1,1,1,1,1,1,1,1,0,1},
									{0,1,1,1,1,1,0,1,1,1,1},
									{0,1,1,1,1,1,0,1,1,1,1},
									{0,1,1,1,1,1,1,1,1,0,1},
									{0,1,1,1,1,1,1,1,1,1,1},
									{0,1,1,1,1,1,1,1,1,1,1},
									{0,1,1,1,1,1,1,1,1,1,1},
									{0,1,1,1,1,1,1,1,1,1,1},
									{0,1,1,1,1,1,1,1,1,1,1},
									{0,1,1,1,1,1,1,1,1,1,0},
									{0,1,1,1,1,0,1,1,1,1,1},
									{1,1,1,1,1,1,1,1,1,0,1},
									{1,1,1,1,1,1,1,1,1,1,1}},
			
								 {  {1,1,1,1,1,1,1,1,1,1,1},
									{1,1,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,1,1,0,0},
									{0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,1,1,0},
									{0,1,0,0,0,0,0,0,0,0,0},
									{0,1,1,1,0,0,0,0,0,0,0},
									{0,1,0,0,0,0,0,0,0,0,0},
									{0,1,0,1,1,0,0,0,0,0,0},
									{0,1,0,1,0,0,0,1,0,0,0},
									{0,1,0,0,0,0,0,0,0,1,1},
									{1,1,0,0,0,0,0,0,0,0,0},
									{1,1,1,1,1,1,1,1,1,1,1}},
							
								 {  {0,0,0,0,0,0,0,0,0,0,1},
									{0,0,0,0,0,0,0,0,0,1,0},
									{0,0,0,0,0,0,0,0,1,0,0},
									{0,0,0,0,0,0,0,1,0,0,0},
									{0,0,0,0,0,0,1,0,0,0,0},
									{0,0,0,0,0,1,0,0,0,0,0},
									{0,0,0,0,1,0,0,0,0,0,0},
									{0,0,0,1,0,0,0,0,0,0,0},
									{0,0,1,0,0,0,0,0,0,0,0},
									{0,1,0,0,0,0,0,0,0,0,0},
									{1,0,0,0,0,0,0,0,0,0,0},
									{0,1,0,0,0,0,0,0,0,0,0},
									{0,0,1,0,0,0,0,0,0,0,0},
									{0,0,0,1,0,0,0,0,0,0,0},
									{0,0,0,0,1,0,0,0,0,0,0},
									{0,0,0,0,0,1,0,0,0,0,0},
									{0,0,0,0,0,0,1,0,0,0,0},
									{0,0,0,0,0,0,0,1,0,0,0},
									{0,0,0,0,0,0,0,0,1,1,1}},
								 
								 {  {1,1,0,0,1,1,0,0,1,1,0},
									{0,0,1,1,0,0,1,1,0,0,1},
									{1,1,0,0,1,1,0,0,1,1,0},
									{0,0,1,1,0,0,1,1,0,0,1},
									{1,1,0,0,1,1,0,0,1,1,0},
									{0,0,1,1,0,0,1,1,0,0,1},
									{1,1,0,0,1,1,0,0,1,1,0},
									{0,0,1,1,0,0,1,1,0,0,1},
									{1,1,0,0,1,1,0,0,1,1,0},
									{0,0,1,1,0,0,1,1,0,0,1},
									{1,1,0,0,1,1,0,0,1,1,0},
									{0,0,1,1,0,0,1,1,0,0,1},
									{1,1,0,0,1,1,0,0,1,1,0},
									{0,0,1,1,0,0,1,1,0,0,1},
									{1,1,0,0,1,1,0,0,1,1,0},
									{0,0,1,1,0,0,1,1,0,0,1},
									{1,1,0,0,1,1,0,0,1,1,0},
									{0,0,1,1,0,0,1,1,0,0,1},
									{1,1,0,0,1,1,0,0,1,1,0} }
									};
	public int[][][] getLevel(){
		return levelDev;
	}
	public int getPatternSize() {
		return levelDev.length;
	}
}
