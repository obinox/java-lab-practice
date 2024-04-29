package jpt;
import java.util.Scanner;
import java.util.ArrayList;

public class Lab_2024311939 {
	public static ArrayList<Integer> Dec2Bin(int x){
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		while (x>=1) {
			intArray.add(0, x%2);
			x = x >> 1;
		}
		return intArray;
	}
	public static ArrayList<Integer> Bin2Oct(ArrayList<Integer> bins){
		ArrayList<Integer> octArray = new ArrayList<Integer>();
		int l = bins.size();
		for (int k = (3-l%3)%3; k > 0; k--) { bins.add(0,0); } //padding
//		System.out.println(l);
//		System.out.println(bins);
		l = bins.size();
		for (int i = 0; i < l/3; i++) {
			int oct = bins.get(l-1-i*3)+bins.get(l-2-i*3)*2+bins.get(l-3-i*3)*4;
//			System.out.println(l-1-i*3);
			octArray.add(0, oct);
		}
		return octArray;
	}
	public static ArrayList<Integer> Bin2Hex(ArrayList<Integer> bins){
		ArrayList<Integer> HexArray = new ArrayList<Integer>();
		int l = bins.size();
//		System.out.println((4-l)%4);
		for (int k = (4-l%4)%4; k > 0; k--) { bins.add(0,0); } //padding
		l = bins.size();
		for (int i = 0; i < l/4; i++) {
			int hex = bins.get(l-1-i*4)+bins.get(l-2-i*4)*2+bins.get(l-3-i*4)*4+bins.get(l-4-i*4)*8;
			if (hex>9){ hex = hex+87; }
			else { hex = hex+48; }
			HexArray.add(0, hex);
		}
		return HexArray;
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int x = scn.nextInt();
		ArrayList<Integer> bins = Dec2Bin(x);
		//Binary
		System.out.print("b ");
		for (int i: bins) {
			System.out.print(i);
		}
		System.out.println();
		//Octal
		System.out.print("o ");
		for (int i: Bin2Oct(bins)) {
			System.out.print(i);
		}
		System.out.println();
		//Hexadecimal
		System.out.print("h ");
		for (int i: Bin2Hex(bins)) {
			System.out.print(Character.toString((char) i));
		}
		System.out.println();
		scn.close();
	}
}
