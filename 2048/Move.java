import java.util.Random;
import java.util.*;

public class Move {
	private int[][] b = new int[4][4];
	private int s = 0;
	private boolean win = false;
	
	public Move(Move m){
		b = m.getBoard();
		s = m.getScore();
	}
	
	public Move(){ //initialized a new board with two 2's randomly placed on board
		Random rand = new Random();

		int x = rand.nextInt(4);
		int y = rand.nextInt(4);
		int x1 = rand.nextInt(4);
		int y1 = rand.nextInt(4);

		while(x == x1 && y == y1){ //makes sure the numbers are not placed in the same block
			x = rand.nextInt(4);
		}
		
		b[x][y]=2;
		b[x1][y1]=2;
	}
	public Move(int[][] board, int score){
		b = board;
		if(score > 0){
			s = score;
		}else s = 0;
	}
	
	/*
	*
	*
	* Testing constructor
	*
	*
	public Move(){

		//b[x][y]

		b[0][0] = 2;
		b[1][0] = 2;
		b[2][0] = 0;
		b[3][0] = 0;

		b[0][1] = 0;
		b[1][1] = 0;
		b[2][1] = 0;
		b[3][1] = 0;

		b[0][2] = 0;
		b[1][2] = 0;
		b[2][2] = 0;
		b[3][2] = 0;

		b[0][3] = 0;
		b[1][3] = 0;
		b[2][3] = 0;
		b[3][3] = 0;
	}*/
	public int[][] getBoard(){
		return b;
	}
	public int getScore(){
		return s;
	}
	public boolean isWon(){
		return win;
	}
	public Move clone(){
		int sS = s;
		int[][] n = new int[4][4];
		for(int i = 0; i< b.length; i++){
			for (int j = 0; j < b[i].length; j++){
				n[i][j] = b[i][j];
			}
		}

		return new Move(n, sS);
	}
	public static int[][] spawn(int[][] b){ //spawns 2 or 4 in a random block
		Random rand = new Random();

		int x = rand.nextInt(4);
		int y = rand.nextInt(4);
		int c = rand.nextInt(9);

		while(b[x][y]!=0){
			x = rand.nextInt(4);
			y = rand.nextInt(4);
		}
		if(c==9){
			b[x][y]=4;
		}else b[x][y]=2;

		return b;
	}
	public void moveUp(){
		int c;
		for(int y = 0; y < 3; y++){
			for(int x = 0; x < 4; x++){
				c = 1;						
				while(b[x][y]==0 && (c+y)<4){
					if(b[x][y+c]!=0){
						b[x][y]=b[x][y+c];
						b[x][y+c]=0;
					}else c++;
				}c = 2;
				while(b[x][y+1]==0 && (c+y)<4){
					if(b[x][y+c]!=0){
						b[x][y+1]=b[x][y+c];
						b[x][y+c]=0;
					}else c++;
				}if(b[x][y]==b[x][y+1]){
					b[x][y]+=b[x][y+1];
					b[x][y+1]=0;
					this.s+=b[x][y];
				}
				if (b[x][y]==2048){
					win = true;
				}
			}
		}
		spawn(b);
	}
	public void moveDown(){ //this dont work yet
		int c;
		for(int y = 3; y > 0; y--){
			for(int x = 0; x < 4; x++){
				c = 1;						
				while(b[x][y]==0 && c <= y){
					if(b[x][y-c]!=0){
						b[x][y]=b[x][y-c];
						b[x][y-c]=0;
					}else c++;
				}c = 2;
				while(b[x][y-1]==0 && c <= y){
					if(b[x][y-c]!=0){
						b[x][y-1]=b[x][y-c];
						b[x][y-c]=0;
					}else c++;
				}if(b[x][y]==b[x][y-1]){
					b[x][y]+=b[x][y-1];
					b[x][y-1]=0;
					this.s+=b[x][y];
				}
				if(b[x][y] == 2048){
					win = true;
				}
			}
		}
		spawn(b);
	}
	public void moveLeft(){
		int c;
		for(int y = 0; y < 4;y++){
			for(int x = 0; x < 3;x++){
				c = 1;						
				while(b[x][y]==0 && (c+x)<4){
					if(b[x+c][y]!=0){
						b[x][y]=b[x+c][y];
						b[x+c][y]=0;
					}else c++;
				}c=2;
				while(b[x+1][y]==0 && (c+x)<4){
					if(b[x+c][y]!=0){
						b[x+1][y]=b[x+c][y];
						b[x+c][y]=0;
					}else c++;
				}if(b[x][y]==b[x+1][y]){
					b[x][y]+=b[x+1][y];
					b[x+1][y]=0;
					this.s+=b[x][y];
				}
				if(b[x][y] == 2048){
					win = true;
				}
			}
		}
		spawn(b);
	}
	public void moveRight(){
		int c;
		for(int y = 0; y < 4;y++){
			for(int x = 3; x > 0;x--){
				c = 1;						
				while(b[x][y]==0 && c <= x){
					if(b[x-c][y]!=0){
						b[x][y]=b[x-c][y];
						b[x-c][y]=0;
					}else c++;
				}c=2;
				while(b[x-1][y]==0 && c <= x){
					if(b[x-c][y]!=0){
						b[x-1][y]=b[x-c][y];
						b[x-c][y]=0;
					}else c++;
				}if(b[x][y]==b[x-1][y]){
					b[x][y]+=b[x-1][y];
					b[x-1][y]=0;
					this.s+=b[x][y];
				}
				if(b[x][y] == 2048){
					win = true;
				}
			}
		}
		spawn(b);
	}
}