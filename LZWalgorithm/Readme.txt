Project-1
----------
Name: Sreelakshmi Setturu
student id: 800934503
•Programming Language used : JAVA 
•Compiler version: 51

Encoding:(Encoder.java)

•	Data structure design : I have used Hash Map to initialize the table which has 0 to 255 ASCII values, stored in values  and corresponding characters as keys in my hashmap. 

How the algorithm is implemented?

•	Input text file and an integer N (which will be used to calculate maximum size of the table) are given through command line arguments.  
•	Fileinputstream is used to read input file character by character and is stored in a variable ‘sym’. Initialized a variable ‘str’ to null and checked if str+sym has an ASCII value in the table.
•	While encoding ,if the str+sym is not found in hasmap, the string(str+sym) and new ascii value is inserted to the hashmap. The size of the hashmap is checked before adding new key and value. If it is less than maximum defined table size, then only the new key value pair is added.
•	The output is being encoded to hexadecimal by UTF-16BE encoding and written to .lzw file

breakdown of files:

input1.txt,input2.txt,input3.txt,input4.txt are used one each time as inputfiles. input1.lzw,input2.lzw,input3.lzw,input4.lzw are the corresponding output files.

How to run the program?

•Through Eclipse:  After importing the project, Select the .java file , go to runrun configurations arguments<filename><space><bitlength>. Program runs much faster in Eclipse IDE than through command prompt. The input file should be present in same folder of the .java file.
• Put the input text file in the same folder of Encoder.JAVA file .In command prompt change the directory to Encoder.java and run “java Encoder <filename> <bitlength>”
 
 
Success and failure of the program
•	Program has been tested for input1, input2, input3 and input4 text files. It worked well with these files as input and bit length 12.

 --------------
Decoding: (Decoder.java)
 
Data structure and implementation of the algorithm:

•	Hashmap is used as table as it has O(1) running time for searching . It is initialized in the same way as in the encoding  part.
•	Reading .lzw file from FileInputStream by passing file name and UTF-16BE as arguments of the constructor, was unsuccessful. So the encoded codes are converted to integer by first storing them in Byte array and later they are copied to Integer array.
•	The obtained integer array has length of the byte array. 
•	Every two elements of the integer are paired and appended after they are converted to binary string.
Ex:  I got 0,97,0,98,1 etc in my integer array for input 1 and converted each of them to binary string like 00000000, 01100001, 00000000, 01100010, 00000001, 00000001, 00000001, 00000000. Now two successive (1&2, 3&4 etc) binary strings are appended and converted to integer to get 97,98,257,256 . These values are taken while decoding.
•	ConverttoBinary() method converts elements of integer array to its binary string  of 8bits.
•	toInteger() method is converting the appended binary strings back to integers. How to compile


Success and failure of the program
•	Program has been tested for input1, input2 and input4 text files. It worked well with these files as input and bit length 12
•	Integer.parseInt(string,radix)  method of java converts directly a binary string to specified number system. Since it throws number format exception for large  data, toInteger() is used to convert binary string to integers. 

breakdown of files:

input1.lzw,input2.lzw,input4.lzw are used one each time as inputfiles. input1_decoded.txt,input2_decoded.txt,input4_decoded.txt are the corresponding output files.

How to run the program?

•Through Eclipse:  After importing the project, Select the .java file , go to runrun configurations arguments<filename><space><bitlength>. Program runs much faster in Eclipse IDE than through command prompt. The input file should be present in same folder of the .java file.
• Put the input text file in the same folder of Decoder.JAVA file .In command prompt change the directory to Decoder.java and run “java Encoder <filename> <bitlength>”. Should compile the program before running.




