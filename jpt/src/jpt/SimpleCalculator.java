package jpt;

import java.util.Scanner;
public class SimpleCalculator {
	public static void main(String[] args) {
		
		
		
		try{
			System.out.println(ne());
		}catch(AddZeroException e) {
			System.out.println(e.s());
		}catch(SubtractZeroException e) {
			System.out.println(e.s());
		}catch(OutOfRangeException e) {
			System.out.println(e.s());
		}
		
		
	}
	public static int ne() throws AddZeroException, SubtractZeroException, OutOfRangeException{
		Scanner scn = new Scanner(System.in);
		
		String s = scn.nextLine();
		int op = 0;
		int result = 0;
		String[] ss = new String[2]; 
		if (s.indexOf("+")!=-1) {
			op = 1;
			ss = s.split("\\+");
		} else if (s.indexOf("-")!=-1) {
			op = -1;
			ss = s.split("\\-");
		} else {
			op = 0;
		}
		result = Integer.valueOf(ss[0]) + op*Integer.valueOf(ss[1]);
		scn.close();
		if (Integer.valueOf(ss[0])*Integer.valueOf(ss[1]) == 0) {
			if (op==1) {
				throw new AddZeroException();
			}else {
				throw new SubtractZeroException();
			}
		}
		if (Integer.valueOf(ss[0])<0||Integer.valueOf(ss[0])>1000||
			Integer.valueOf(ss[1])<0||Integer.valueOf(ss[1])>1000||
			result<0||result>1000) {
			throw new OutOfRangeException();
		}
		return result;
	}
}

class AddZeroException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5689552356455302595L;
	public AddZeroException() {
		
	}
	public String s() {
		return "AddZeroException";
	}
}
class SubtractZeroException extends Exception{
	public SubtractZeroException() {
		
	}
	public String s() {
		return "SubtractZeroException";
	}
}
class OutOfRangeException extends Exception{
	public OutOfRangeException() {
		
	}
	public String s() {
		return "OutOfRangeException";
	}
}