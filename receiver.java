package mcarbon1;

import java.io.FileWriter;
import java.net.*;

public class receiver {
	public static void main(String[] args) {
		try {
			FileWriter fw = new FileWriter("D:\\state1.txt");
			DatagramSocket ds = new DatagramSocket(8000);
			int i = 0, m, j,k;
			while (true) {
				byte[] buf = new byte[1024];

				DatagramPacket dp = new DatagramPacket(buf, 1024);
				ds.receive(dp);
				String str = new String(dp.getData(), 0, dp.getLength());
				String[] words = str.split(" ");
				j = Integer.parseInt(words[1]);
				k=j;
				j++;
				System.out.println(words[0] + "	" + k);
				fw.write(words[0] + "	" + j + "\r\n");
				if (i == 99) {
					fw.close();
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ds.close();
	}
}
