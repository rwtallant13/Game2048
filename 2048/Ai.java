import java.util.*;

public class Ai{
	private String status;
	private int depth = 7;
	private Move m;
	private String move;
	private int emptyCells;
	

	public Ai(){

	}
	public String getMove(){
		return move;
	}
	public static String hint(Move m){
		return "";
	}
	private static int huristicScore(Move m, int eC, int cS){
		int aS = m.getScore();

		int score = (int)(aS+Math.log(aS)*eC-cS);
		return Math.max(score, Math.min(aS, 1));
	}
	public int getHuristicScore(Move m){
		return huristicScore(m, calculateEmptyCells(m), calculateClusteringScore(m));
	}
	//private
	public static int calculateEmptyCells(Move m){
		int emptyCells = 0;	
		int[][] b; 
		b = (m.getBoard());	

		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				if(b[x][y]==0){
					emptyCells++;
				}
			}
		}

		return emptyCells;
	}
	//private
	public static int calculateClusteringScore(Move m){
		int[][] b = m.getBoard();
		int clusteringScore = 0;
		int[] nhbd = {-1,0,1}; //neighbors

		for(int x1 = 0;x1 < 4;x1++){
			for(int y1 = 0;y1 < 4;y1++){
				if(b[x1][y1]!=0){

					int nNum = 0;
					int sum = 0;

					for(int x2 : nhbd){
						int x = x1+x2;
						if(x==0 || x>=b.length){
							continue;
						}

						for(int y2 : nhbd){
							int y=y1+y2;
							if(y<0 || y>=b.length){
								continue;
							}
							if(x>0 && y>0){
								if(b[x][y]>0){
									nNum++;
									sum+=Math.abs(b[x1][y1]-b[x][y]);
								}
							}else continue;
						}
					}
					if(nNum>0){
						clusteringScore+=sum/nNum;
					}else continue;
				}
			}
		}
		return clusteringScore;
	}
	public String calculate(Move m, int depth){
		Game2048 game = new Game2048();
		String moves = "";
		int l,r,u,d,nMax,nMin;
		Move mL = new Move(m);
		Move mR = new Move(m);
		Move mU = new Move(m);
		Move mD = new Move(m); 
		String max;
		String min;
		for(int i =0;i<depth;i++){
			//max
			if(i%2!=0){
				nMax = 0;
				mL.moveLeft(m.getBoard(), m.getScore());
				l = getHuristicScore(mL);
				
				mR.moveRight(m.getBoard(), m.getScore());
				r = getHuristicScore(mR);
				if(r>l){
					max = "Right ";
					nMax = r;
				}else{
					max = "Left ";
					nMax = l;
				}
				
				mU.moveUp(m.getBoard(), m.getScore());
				u = getHuristicScore(mU);
				if(u>nMax){
					max = "Up ";
					nMax = r;
				}
				
				mD.moveDown(m.getBoard(), m.getScore());
				d = getHuristicScore(mD);
				if(d>nMax){
					max = "Down ";
					nMax = d;
				}
				moves += max;
			}
			if(i%2==0){
				nMin = 0;
				mL.moveLeft(m.getBoard(), m.getScore());
				l = getHuristicScore(mL);
				
				mR.moveRight(m.getBoard(), m.getScore());
				r = getHuristicScore(mR);
				if(r<l){
					min = "Right ";
					nMin = r;
				}else{
					min = "Left ";
					nMin = l;
				}
				
				mU.moveUp(m.getBoard(), m.getScore());
				u = getHuristicScore(mU);
				if(u<nMin){
					min = "Up ";
					nMin = r;
				}
				
				mD.moveDown(m.getBoard(), m.getScore());
				d = getHuristicScore(mD);
				if(d<nMin){
					min = "Down ";
					nMin = d;
				}
				moves += min;
			}
			game.drawBoard(m);
		}
		return moves;
	}
}