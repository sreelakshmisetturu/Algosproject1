//SREELAKSHMI SETTURU
package com;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Decoder {
	public static void main(String[] args) throws IOException {
		int code, maxsize_Table;
		String newstring, outputfile, strn, inputfile_decoder;
		String n;
		int counter = 0;

		String binary = new String();
		inputfile_decoder = args[0];
		n = args[1];

		// appending decoded.txt to the name of the input file without extension
		outputfile = inputfile_decoder.substring(0, 6) + "_decoded.txt";

		// command line argument is string,so converting bit length 'N' to
		// integer.
		int N = Integer.parseInt(n);

		// calculating max size of the table
		maxsize_Table = (int) Math.pow(2, N);

		// creating a table (here hashmapap has ASCII values in keys and
		// characters
		// as values)
		Map<Integer, String> hmap = new HashMap<Integer, String>();

		for (int i = 0; i < 256; i++) {
			hmap.put(i, "" + (char) i); // converting integers(ASCII values) 0
										// to
										// 255 back to their characters
		} // and storing those characters in hashmap

		OutputStreamWriter writer = new OutputStreamWriter(
				new FileOutputStream(outputfile));

		// Reading contents of .lzw file.
		File file = new File(inputfile_decoder);
		FileInputStream fis = new FileInputStream(file);

		byte[] byt = new byte[(int) file.length()];
		fis.read(byt, 0, (int) file.length());
		int[] intArray = new int[byt.length];

		for (int i = 0; i < byt.length; i++) { // hexadecimal byte values are
												// copied to integer array
			intArray[i] = byt[i];
		}

		for (int i = 0; i < 2; i++) {
			binary = binary + converToBinary(intArray[i]);

		}

		int in = toInteger(binary);
		strn = hmap.get(in);
		System.out.println(strn);
		writer.write(strn);

		binary = "";

		// converting integer array to binary string and decoding logic is
		// implemented here
		int i=2;
		while(i< intArray.length) {
			binary = binary + converToBinary(intArray[i]);
			counter++;
			if (counter == 2) {
				code = toInteger(binary);
				if (!hmap.containsKey(code)) {
					newstring = strn + strn.substring(0, 1);
				} else {
					newstring = hmap.get(code);
				}
				System.out.println(newstring);
				writer.write(newstring);
				if (hmap.size() < maxsize_Table) {
					hmap.put(hmap.size(), strn + newstring.substring(0, 1));
				}
				strn = newstring;
				binary = "";
				counter = 0;
			}
         i+=1;
		}
		writer.close();
		fis.close();
	}

	// method to convert binary string to integer.
	private static int toInteger(String binary) {
		int integer = 0, value, power = 15;
		for (char c : binary.toCharArray()) {
			value = Integer.parseInt("" + c);
			integer += value * Math.pow(2.0, power);
			power -= 1;
		}
		return integer;
	}

	// Method to convert integer to its binary value
	private static String converToBinary(int value) {
		String finalValue = "";
		finalValue = Integer.toBinaryString(value);
		int length = finalValue.length();
		for (int i = 0; i < (8 - length); i++) {
			finalValue = "0" + finalValue;
		}
		return finalValue;
	}
}
