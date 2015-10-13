import java.util.*;
import java.util.Random;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Game2048Graphic extends JApplet implements ActionListener {
	/*public static void main(String[] args){
		Scanner scn = new Scanner(System.in);
		Random rand = new Random();
		int[] board = new int[16];

		int inp = 0;
		int end = 0;

		System.out.println("+------------------------------------------------------------------+");

		while(inp != 1){
			System.out.println("2408 Game");
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

		newGame(board);
		drawBoard(board);
		while(end == 0){
			move(scn.next(), board);
			spawn(board);
			drawBoard(board);
		}

	}*/

	Button w, s, a, d;

	public void init(){
		w = new Button("Up");
		s = new Button("Down");
		a = new Button("Left");
		d = new Button("Right");
	}
	public void actionPreformed( ActionEvent evt){

	}

	public void paint (Graphics g){
		g.drawString("Hello World", 0,0);
	}

	public static int[] newGame(int[] b){
		Random rand = new Random();

		int one = rand.nextInt(16);
		int two = rand.nextInt(16);

		while(one == two){
			one = rand.nextInt(16);
		}

		for(int i = 0;i<b.length;i++){
			if (i == one){
				b[i] = 2;
			}
			if (i == two){
				b[i] = 2;
			}
		}
		return b;
	}
	public static int[] move(String m, int[] b){
		if(m.equals("w")){ 
			for(int i = b.length;i>=0;i--){
				if(i<12){ //starts at the second to last row
					if(b[i]==b[i+4]){ //checks if the square below (+4) is the same
						b[i]+=b[i+4]; //if true -> adds the blocks
						b[i+4] = 0; //sets block below (+4) to 0
					}else if(b[i]==0){ //checks if the square is 0
						b[i]=b[i+4]; //if b[i] is 0 then it assumes the value below (+4)
						b[i+4]=0; //the value below (+4) is set to zero now
					}
				}
			}
		}
		if(m.equals("s")){
			for(int i = 0;i<b.length;i++){
				if(i>3){ //starts at the second row
					if(b[i]==b[i-4]){ //checks if the block above it (-4) is the same
						b[i]+=b[i-4]; //if true -> adds blocks
						b[i-4] = 0; //sets block above (-4) to 0
					}else if(b[i]==0){ //checks if the block is 0
						b[i]=b[i-4]; //if true -> block assumes value of block above (-4)
						b[i-4]=0; //block above (-4) is reset
					}
				}
			}
		}
		if(m.equals("a")){
			for(int k = 0;k<16;k+=4){
				for(int i = 2;i>=0;i--){
					if(b[i+k]==b[i+k+1]){
						b[i+k]+=b[i+k+1];
						b[i+k+1] = 0;
					}else if(b[i+k]==0){
						b[i+k]=b[i+k+1];
						b[i+k+1]=0;
					}
				}
			}
		}
		if(m.equals("d")){
			for(int k = 0;k<16;k+=4){
				for(int i = 1;i<=3;i++){
					if(b[i+k]==b[i+k-1]){
						b[i+k]+=b[i+k-1];
						b[i+k-1] = 0;
					}else if(b[i+k]==0){
						b[i+k]=b[i+k-1];
						b[i+k-1]=0;
					}
				}
			}
		}

		return b;
	}
	public static int[] spawn(int[] b){
		Random rand = new Random();

		int block = rand.nextInt(16);
		int four = rand.nextInt(10);

		while(b[block]!=0){
			block = rand.nextInt(16);
		}
		if(four==9){
			b[block]=4;
		}else b[block] = 2;
		return b;
	}
	public static void drawBoard(int[] b){
		System.out.println("+-------------------------------------+");
		System.out.println("|        |         |         |         |");
		System.out.println("|   "+b[0]+"    |    "+b[1]+"    |    "+b[2]+"    |    "+b[3]+"    |");
		System.out.println("|        |         |         |         |");
		System.out.println("---------------------------------------");
		System.out.println("|        |         |         |         |");
		System.out.println("|   "+b[4]+"    |    "+b[5]+"    |    "+b[6]+"    |    "+b[7]+"    |");
		System.out.println("|        |         |         |         |");
		System.out.println("---------------------------------------");
		System.out.println("|        |         |         |         |");
		System.out.println("|   "+b[8]+"    |    "+b[9]+"    |    "+b[10]+"    |    "+b[11]+"    |");
		System.out.println("|        |         |         |         |");
		System.out.println("---------------------------------------");
		System.out.println("|        |         |         |         |");
		System.out.println("|   "+b[12]+"    |    "+b[13]+"    |    "+b[14]+"    |    "+b[15]+"    |");
		System.out.println("|        |         |         |         |");
		System.out.println("+-------------------------------------+");
	}
}