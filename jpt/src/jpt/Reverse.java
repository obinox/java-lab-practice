package jpt;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;

public class Reverse {
	public static void main(String[] args) throws IOException {
		try(InputStream fis = new FileInputStream("input.txt");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);)
		{
			ArrayList<String> datas = new ArrayList<String>();
			String data;
			while ((data = br.readLine()) != null) {
				datas.add(data);
			}
			br.close(); isr.close(); fis.close();
			FileOutputStream fos = new FileOutputStream("output.txt");
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter (osw);
			for (int i = datas.size()-1;i>=0;i--) {
				bw.write(datas.get(i));
				bw.newLine();
				bw.flush();
			}
			bw.close(); osw.close(); fos.close();
		} catch (Exception e){
			System.out.println(e + "file not found");
		}
	}
}
