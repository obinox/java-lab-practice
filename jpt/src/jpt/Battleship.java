package jpt;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Battleship {
	public static void main(String[] args) throws IOException {
		Scanner scn = new Scanner(System.in);
		int mode = 0;
		String[] s = scn.nextLine().split(" ");
		String[] f = new String[s.length-2];
		for (int i=0;i<f.length;i++) {
			f[i]=s[i+2];
		}
		try(InputStream fis = new FileInputStream(String.join(" ", f));
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);) {
			Methods.bombs(Integer.valueOf(s[0]));
			mode = Methods.modes(s[1]);
		} catch(BombInputException e) {
			e.printStackTrace();
		} catch(ModeInputException e) {
			e.printStackTrace();
		} catch(IOException e) {
			Methods.randomBoard();
		}
		scn.close();
		
	}
	
}

class Methods{
	public static void bombs(int s) throws BombInputException{
		if (!(s>0)) {
			throw new BombInputException();
		}
	}
	public static int modes(String s) throws ModeInputException{
		switch(s) {
			case "r","R":
				return 1;
			case "d","D":
				return 2;
			default:
				throw new ModeInputException();
		}
	}
	public static Board randomBoard() {
		return new Board();
	}
}

class Board{
	ship[][] board;
	public Board(){
		this.board = new ship[10][10];
		
	}
	public Board(String s) {
		this.board = new ship[10][10];
	}
	public void hit(int r, int c) {
		this.board[r][c].hit();
	}
} 

class BombInputException extends Exception {
	private static final long serialVersionUID = 1L;
	public BombInputException() {
		
	}
	public BombInputException(String message){
		super(message);
	}
	
}

class ModeInputException extends Exception {
	private static final long serialVersionUID = 1L;
	public ModeInputException() {
		
	}
	public ModeInputException(String message){
		super(message);
	}
}
//12214
abstract class ship {
	int size=0;
	String type="ship";
	String disp="  ";
	String hdsp="X ";
	boolean hits=false;
	public void hit() {
		this.hits = true;
	}
}
class AircraftCarrier extends ship{
	public AircraftCarrier() {
		this.size = 6;
		this.type = "carrier";
		this.disp = "A ";
		this.hdsp = "Xa";
	}
}
class BattleShip extends ship{
	public BattleShip() {
		this.size = 6;
		this.type = "battleship";
		this.disp = "B ";
		this.hdsp = "Xb";
	}
}
class Submarine extends ship{
	public Submarine() {
		this.size = 6;
		this.type = "submarine";
		this.disp = "S ";
		this.hdsp = "Xs";
	}
}
class Destroyer extends ship{
	public Destroyer() {
		this.size = 6;
		this.type = "destroyer";
		this.disp = "D ";
		this.hdsp = "Xd";
	}
}
class PatrolBoat extends ship{
	public PatrolBoat() {
		this.size = 6;
		this.type = "patrolboat";
		this.disp = "P ";
		this.hdsp = "Xp";
	}
}