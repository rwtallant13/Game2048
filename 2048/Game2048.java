import java.util.*;
import java.util.Random;

//Written By: Wesley Romary

public class Game2048 {
	public static int gos = 1;
	public Game2048(){

	}
	public static void main(String[] args){
		Scanner scn = new Scanner(System.in);

		int inp = 0;
		int end = 0;
		int zc = 0;

		System.out.println("+------------------------------------------------------------------+");

		//welcome message
		while(inp != 1){
			System.out.println("2048 Game");
			System.out.println("---------");
			System.out.println("1 To start!");
			System.out.println("2. Help.");
			inp = scn.nextInt();

			if(inp == 2){
				System.out.println("wsad are their respective controls.");
				System.out.println("Try to make 2048!");
				System.out.println("Press and number to continue.");
				inp = scn.nextInt();
			}
		}

		//initializing game board
		Move move = new Move();
		drawBoard(move);
		while(end == 0){
			move(move, scn.next());
			drawBoard(move);
		}
		System.out.println("You lost sucka");

	}
	public static Move move(Move mM, String m){ //Sends move keys to move class
		Scanner scn = new Scanner(System.in);
		Ai ai = new Ai();
		if(m.equals("w")){
			mM.moveUp();
			gos++;
		}
		if(m.equals("s")){
			mM.moveDown();
			gos++;
		}
		if(m.equals("a")){
			mM.moveLeft();
			gos++;
		}
		if(m.equals("d")) {
			mM.moveRight();
			gos++;
		}
		 	if(m.equals("h")){
			System.out.println("How deep fam? ");
			int d = scn.nextInt();
			System.out.println(ai.calculate(mM, d));
			
		}
		if(m.equals("win")){

			ai.win(mM);
		}
		if(m.equals("q")){
			final int end = 1;
		}
		System.out.println("Turns: "+gos);

		System.out.println("Clustering Score: "+ai.calculateClusteringScore(mM));
		System.out.println("Empty Cells: "+ ai.calculateEmptyCells(mM));
		System.out.println("Huristic Score: "+ ai.getHuristicScore(mM));
		return mM;
	}
	public static void drawBoard(Move m){ //draws board
		int[][] b = m.getBoard();
		System.out.println("+-------------------------------------+");
		System.out.println("|        |         |         |         |");
		System.out.println("|   "+b[0][0]+"    |    "+b[1][0]+"    |    "+b[2][0]+"    |    "+b[3][0]+"    |");
		System.out.println("|        |         |         |         |");
		System.out.println("---------------------------------------");
		System.out.println("|        |         |         |         |");
		System.out.println("|   "+b[0][1]+"    |    "+b[1][1]+"    |    "+b[2][1]+"    |    "+b[3][1]+"    |");
		System.out.println("|        |         |         |         |");
		System.out.println("---------------------------------------");
		System.out.println("|        |         |         |         |");
		System.out.println("|   "+b[0][2]+"    |    "+b[1][2]+"    |    "+b[2][2]+"    |    "+b[3][2]+"    |");
		System.out.println("|        |         |         |         |");
		System.out.println("---------------------------------------");
		System.out.println("|        |         |         |         |");
		System.out.println("|   "+b[0][3]+"    |    "+b[1][3]+"    |    "+b[2][3]+"    |    "+b[3][3]+"    |");
		System.out.println("|        |         |         |         |");
		System.out.println("+-------------------------------------+");
		System.out.println(m.getScore());
	}
}
