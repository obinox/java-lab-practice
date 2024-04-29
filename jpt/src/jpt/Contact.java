package jpt;
import java.util.HashMap;
import java.util.Scanner;

public class Contact {
	public HashMap<String, String> map = new HashMap<>();
	public static void main(String[] args) {
		Contact con = new Contact();
		Scanner scn = new Scanner(System.in);
		String input;
		int flag = 0;
		while (true) {
			input = scn.nextLine();
			switch (input.split(" ")[0]) {
				case "add":
					con.Add(input);
					break;
				case "show":
					con.Show();
					break;
				case "find":
					con.Find(input);
					break;
				case "delete":
					con.Delete(input);
					break;
				default:
					con.Invalid();
					break;
			}
			if (flag > 0) {
				break;
			}
		}
		scn.close();
	}
	
	public void Show() {
		this.map.forEach((K, V)->{
			System.out.println(K+" "+V);
		});
	}
	public String Add(String I) {
		String output = "";
		try {
			String K = I.split(" ")[1];
			if (this.map.get(K) != null) {
				throw new Exception();
			}
			String V = I.split(" ")[2];
			this.map.put(K, V);
		} catch(Exception e) {
			output = "error\n";
		} finally {	
			System.out.print(output);
		}
		return output;
		
	}
	public String Find(String I) {
		String output = "";
		try {
			String K = I.split(" ")[1];
			output = this.map.get(K);
			if (output == null) {
				throw new Exception();
			}
		} catch(Exception e) {
			output = "error";
		} finally {	
			System.out.println(output);
		}
		return output;
	}
	public String Delete(String I) {
		String output = "";
		try {
			String K = I.split(" ")[1];
			if (this.map.get(K) == null) {
				throw new Exception();
			}
			this.map.remove(K);
		} catch(Exception e) {
			output = "error\n";
		} finally {	
			System.out.print(output);
		}
		return output;
	}
	public void Invalid() {
		System.out.println("error");
	}
	
}
