package jpt;

public class StringMethod {
	static String addString(String s1, int index, String s2) {
		String[] b = new String[3];
		b[0]=s1.substring(0,index+1);
		b[1]=s2;
		b[2]=s1.substring(index+1);
		return String.join("",b);
	}
	
	static String reverse(String s) {
		String[] ss = s.split("");
		String[] rs = new String[s.length()];
		for (int i=0;i<s.length();i++) {
			rs[s.length()-i-1]=ss[i];
		}
		return String.join("",rs);
	}
	static String removeString(String s1, String s2) {
		return String.join("",s1.split(s2));
	}
	public static void main(String[] args) {
		System.out.println(addString("0123456",3,"-"));
		System.out.println(reverse("abc"));
		System.out.println(removeString("01001000","00"));
	}
}
