package eecs2030.lab4;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A simple class to implement LZW compression (encoding) and decompression (decoding) of 
 * a given input string.
 * 
 * 
 * <p>
 * The LZWCompressor maintains a list of characters representing an input sequence to be 
 * encoded/decoded. It also maintains a local LZWDictionary, which is initialized with unique
 * characters from the input sequence, and is used to encode/decode the input sequence.
 * <p>
 * 
 * <p>
 * Class invariant: The LZWDictionary always holds only the initial single character 
 * patterns as its entries (i.e. it may grow during encode/decode operations, however must
 * be reset to its initial state after an encode/decode operation).
 * </p> 
 * 
 * 
 * @author eecs2030
 *
 */
public class LZWCompressor {

	String inputString = "";
	LZWDictionary dict;
	double encodeSize = 0.0;
	
	// FIELDS
	// input sequence to be encoded
	// dictionary to use when encoding/decoding



	/**
	 * Initialize this LZWCompressor to encode/decode a specified input string.
	 * 
	 * 
	 * <p>
	 * A list of characters is initialized from the sequence of characters specified in
	 * a provided string.  The unique characters from this string are also used to initialize
	 * an LZWDictionary maintained and used by the LZWCompressor when encoding/decoding
	 * </p>
	 * 
	 * 
	 * 
	 * @param input a string representing an input sequence of characters to be encoded/decoded
	 * 
	 * @throws an IllegalArgumentException if the input string is empty
	 * 
	 */
	public LZWCompressor(String input) {
		inputString = input;
		if(inputString.length() == 0) {
			throw new IllegalArgumentException("The input string is empty");
		}
		
		dict = new LZWDictionary(inputString);
	}


	/**
	 * Returns the original input sequence to be encoded/decoded by this LZWCompressor
	 * 
	 * @return a string representing the original input sequence
	 * 
	 */
	public String getInput() {
		return inputString;
	}


	/**
	 * Returns the dictionary used by this LZWCompressor
	 * 
	 * @return a reference to the LZWDictionary used by this LZWCompressor
	 */
	public LZWDictionary getDictionary() {
		return dict;	
	}



	/**
	 * Generates an LZW encoding of the input sequence
	 * 
	 * 
	 * <p>
	 * Uses the LZW encoding algorithm given in the lab4 specification
	 * 
	 * @return a list of integers representing the sequence of codes (indexes of character 
	 * patterns) learned by an LZWDictionary during the encoding process
	 * 
	 */
	public List<Integer> encode() {
		
		dict.reset();
		
		/*
		 *  Unique characters are already there in the dictionary,
		 *  Other combinations need to be added to the map, and output the map as a list.
		 */
		
		char[] cArray = inputString.toCharArray();
		String[] sArray = new String[cArray.length];
		for(int i = 0; i < cArray.length; i++) {
			sArray[i] = Character.toString(cArray[i]);
		}
		
		/*
		 * The inputString is in a unique single character string array, 
		 * do the encoding now, and update the dictionary while encoding. 
		 */
		
		/*
		 * Keep adding the longest pattern that was used for the encoding before adding the sequence 
		 * to the dictionary.
		 */
		List<Integer> finalList = new ArrayList<Integer>();
	//	finalList.add(0);
		
		int k = 0;	
		int j = 0;
		String W = sArray[k];
		while(k < sArray.length - 1) {			
			String C = sArray[k + 1];
			String WC = W + C;
			if(dict.map.containsKey(WC) == true) {
				W = W + C;
				k++;
			}
			else {
				finalList.add(dict.map.get(W));
				dict.map.put(WC, (dict.map.size()));
				W = C; 
				k++;
				j++;
			}
		}
		finalList.add(dict.map.get(W));
		encodeSize = finalList.size();		
		
		return finalList;		
	}







	/**
	 * Decodes an LZW encoding to generate the original input sequence
	 * 
	 * 
	 * <p>
	 * Uses the LZW decoding algorithm given in the lab4 specification
	 * 
	 * @param encoded a list of integers representing a sequence of codes (indexes 
	 * of character patterns) learned by an LZWDictionary during the encoding process
	 * 
	 * @return a string representation of the decoded input sequence
	 * 
	 * @throws an IllegalArgumentException if encoded is an empty list
	 * 
	 */
	public String decode(List<Integer> encoded) {		
		
		if(encoded.isEmpty()) {
			throw new IllegalArgumentException("Encoded is an empty list");
		}
		
		dict.reset();
		dict.getMap();
		
		String finalAnswer = "";
		String S;
		char C;
		String ENTRY;
		
		int prev = encoded.get(0);
		finalAnswer += dict.get(prev);
		
		int k = 0;
		int j = 1;
		while(k < encoded.size() - 2){
			int next = encoded.get(j);
			
			if(dict.map.containsValue(next)) {
				S = dict.get(next);
				// k++;
				j++;
			}
			else {
				 S = dict.get(prev);
				 C = S.charAt(0);
				 S = S + C;
				 k++;
				// j++;
			}
			
			finalAnswer += S;
			ENTRY = dict.get(prev) + S.charAt(0);
			dict.map.put(ENTRY, dict.map.size());
			dict.list.add(ENTRY);
			prev = next;
		}
		
		
		
		return finalAnswer;
	}






	/**
	 * Returns the compression ratio of an encoding
	 * 
	 * 
	 * <p> 
	 * The compression ration (CR) is defined as the number of characters in the input
	 * sequence, divided by the number of codes in the encoded version of the input sequence
	 * </p>
	 * 
	 * 
	 * @return a double representing the compression ratio
	 * 
	 */
	public double compressionRatio() {
		
		double inputLength = inputString.length();
		double encodeLength = encodeSize;		
		
		return inputLength / encodeLength;
	}




	/**
	 * Some simple test cases that can be run independently of the junit tester
	 * 
	 */
	public static void main(String[] args) {

		LZWCompressor codec = new LZWCompressor("ababababa");

		//codec = new LZWCompressor("#@$*@#($*@#$@(#*$@(#*$@#$");
		//codec = new LZWCompressor("the fat the cat the bat the rat the mat the sat the tat");
		//codec = new LZWCompressor("1231411212312312312124312413");
		//codec = new LZWCompressor("thefatthecatthebattheratthematthesatthetatthefatthecatthebattheratthematthesatthe");



		// ENCODE
		System.out.println("original input sequence: " + codec.getInput());
		List<Integer> enc = codec.encode();
		System.out.println("encoded sequence: " + enc);
		System.out.println("compression ratio: " + codec.compressionRatio());
		System.out.println("-------------------");


		// DECODE
		String dec = codec.decode(enc);
		System.out.println("decoded sequence: " + dec);
		System.out.println("successful decode = " + dec.equals(codec.getInput()));


	}
}
