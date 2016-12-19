# LZW-Algorithm


Project-1
----------
Name: Sreelakshmi Setturu
Email: sreelakshmisetturu@gmail.com

The Lempel–Ziv–Welch (LZW) algorithm is a lossless data compression algorithm. 
LZW is an adaptive compression algorithm that does not assume prior knowledge of the input data distribution.
This algorithm works well when the input data is sufficiently large and there is redundancy in the data.
Two examples of commonly used file formats that use LZW compression are the GIF image format
served from websites and the TIFF image format. LZW compression is also suitable for compressing
text files, and is the algorithm in the compress Unix file compression utility.
This algorithm has two steps:
1. Encoding/Compressing
2. Decoding/Decompressing


Encoding/Compression algorithm description: 

The LZW algorithm reads an input sequence of symbols, groups the symbols into strings, and
represents the strings with integer codes. Since the codes require less space than the strings,
compression is achieved.

LZW starts out with a known dictionary of single characters that
constitute the input character set. For example, this could be the extended ASCII set of 256 characters
(for the case of 8 bits) that represent alphabets, numbers, punctuation symbols, etc., and it uses these as
the "standard" character set (see www.asciitable.com). It then reads symbols (i.e., characters) 8 bits at a
time (e.g., 't', 'h', 'e',etc.) and appends them one by one to the current string. Each time it appends a
symbol to the string, it checks whether the resulting string has a code in the dictionary. If it does, it
reads in the next symbol and appends it to the current string. If the resulting string does not exist in the
dictionary, it means it has found a new string: it outputs the code corresponding to the string without
the newest symbol, adds the string concatenated with the newest symbol (i.e., the new string) to the
dictionary with its code (which is the previous largest code value incremented by one), and resets the
current string to the newest symbol. Thus the next time the LZW algorithm encounters a repeated string
in the input sequence, it will be encoded with a single number. The algorithm continues to process
symbols from the input sequence, building new strings until the sequence is exhausted, and it then
outputs the code for the remaining string.

A bit length for the codes is specified, based on the
maximum number of entries for the dictionary, so that the process does not use a very large amount of
memory.

Encoding implementation:

• Input text file and an integer N (which will be used to calculate maximum size of the table) are given through command line arguments.
• Fileinputstream is used to read input file character by character and is stored in a variable ‘sym’. Initialized a variable ‘str’ to 
null and checked if str+sym has an ASCII value in the table. • While encoding ,if the str+sym is not found in hasmap, the string(str+sym) 
and new ascii value is inserted to the hashmap. The size of the hashmap is checked before adding new key and value. If it is less than maximum defined table size, 
then only the new key value pair is added. • The output is being encoded to hexadecimal by UTF-16BE encoding and written to .lzw file.



Decoding/Decompression algorithm description:

Decompression works in the reverse fashion to compression, converting integer codes into the strings
they represent.
A dictionary identical to the one created during compression is reconstructed during the decompression
process. 
Every time the decoder extracts a string from the dictionary using a code, it adds a string to the dictionary, consisting of the previous
string and the first character of the new string, with an updated code index. So the decoder is adding
strings to the dictionary one step behind the encoder. The LZW decoder first reads an input code (integer) from the encoded sequence, looks up the code in
the dictionary by using it as an index, and outputs the string associated with the code. Thereafter, the
decoder reads in a new code, finds the new string indexed by this code, and outputs it. The first
character of this new string is concatenated to the previously decoded string. This new concatenation is
added to the dictionary with an incremented code (simulating how the strings were added during
compression). The decoded new string then becomes the previous string, and the process repeats.

When the decoder receives a code that is not already in its dictionary, it can be shown that the new
string corresponding to this code consists of the previously decoded string with the first character of the
previously decoded string appended. Each time it reads in a code that does not exist in the dictionary, it
must add the code and the corresponding string to the dictionary.

Decoding Implementation:

Hashmap is used as table as it has O(1) running time for searching . It is initialized in the same way as in the encoding part. 
• Reading .lzw file from FileInputStream by passing file name and UTF-16BE as arguments of the constructor, was unsuccessful. 
So the encoded codes are converted to integer by first storing them in Byte array and later they are copied to Integer array.  
The obtained integer array has length of the byte array. • Every two elements of the integer are paired and appended after they 
are converted to binary string. Ex: I got 0,97,0,98,1 etc in my integer array for input 1 and converted each of them to binary 
string like 00000000, 01100001, 00000000, 01100010, 00000001, 00000001, 00000001, 00000000. Now two successive (1&2, 3&4 etc) 
binary strings are appended and converted to integer to get 97,98,257,256 . These values are taken while decoding.
