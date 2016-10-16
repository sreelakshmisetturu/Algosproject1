//SREELAKShmapI SETTURU
package com;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Encoder {
	public static void main(String[] args) throws Exception {
		String str = "", sym;
		String inputfile, outputfile, n;
		int i, N, sy;
		int Maxsize_table;

		inputfile = args[0];
		outputfile = inputfile.substring(0, 6) + ".lzw";
		n = args[1];

		// command line argument for bit length is string,so converting bit
		// length 'N' to integer.
		N = Integer.parseInt(n);

		// calculating max size of the table
		Maxsize_table = (int) Math.pow(2, N);

		// creating a table (here hashmapap has characters in keys and ASCII
		// values in values)

		Map<String, Integer> hmap = new HashMap<String, Integer>();
		OutputStreamWriter writer = new OutputStreamWriter(
				new FileOutputStream(outputfile), "UTF-16BE");

		for (i = 0; i < 256; i++) {
			hmap.put(("" + (char) i), i); // converting integers(ASCII values) 
											// 0 to 255 back to their characters
		} // and storing those characters as keys and ASCII values as values in
			// hashmapap

		// Reading contents of inputfile.
		File inp = new File(inputfile);
		FileInputStream fis = new FileInputStream(inp);
		while ((sy = fis.read()) != -1) {
			char content = (char) (sy);
			sym = String.valueOf(content);
			if (hmap.containsKey(str + sym)) {
				str = str + sym;
			} else {
				System.out.println("output: " + hmap.get(str));
				writer.write(hmap.get(str));
				if (hmap.size() < Maxsize_table) // checking if size of the
													// table less than maximum
													// size.
				{
					hmap.put(str + sym, hmap.size());
				}
				str = sym;
			}
		}
		System.out.println("output: " + hmap.get(str));
		writer.write(hmap.get(str));
		writer.close();
		fis.close();
	}

}
