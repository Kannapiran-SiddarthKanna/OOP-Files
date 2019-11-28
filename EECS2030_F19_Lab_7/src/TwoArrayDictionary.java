/* Make sure the instructions document is read carefully.
 * 
 * You are required to use the given `words` and `definitions` arrays to implement the methods.
 * See test_two_array_implementation_insert and test_two_array_implementation_remove 
 * in class TestArrayImplementations.
 * 
 * Requirements:
 * 		- You are not allowed to add new attributes to this class.
 * 		- You are not allowed to use any Java collection library (no import statements).
 * 
 * The idea is that both `words` and `definitions` are initialized as arrays of size `MAX_CAPACITY` with each slot storing null.
 * Entries (words and definitions) are added from left to right, whereas all free slots remain null.
 * When an entry is removed, all slots to its right are shifted to the left to close the gap.
 * 
 * For example, given a dictionary with four entries:
 * words:       {w1, w2, w3, w4, null, null, ...}
 * definitions: {d1, d2, d3, d4, null, null, ...}
 * Removing the entry for word `w2` has the resulting dictionary:
 * words:       {w1, w3, w4, null, null, null, ...}
 * definitions: {d1, d3, d4, null, null, null, ...}
 * 
 * You may consider studying this note on manipulating basic array:
 * https://www.eecs.yorku.ca/~jackie/teaching/lectures/2019/F/EECS2030/notes/EECS2030_F19_Notes_Tracing_PointCollectorTester.pdf
 */

public class TwoArrayDictionary implements Dictionary {
	
	/*
	 * Use these attributes only to implement the methods.
	 */
	int MAX_CAPACITY = 100;
	int count = 0; // number of entries in dictionary
	
	String[] words;
	String[] definitions;
	
	/*
	 * Your tasks: declare and implement methods from the Dictionary interface.
	 */
	
	public TwoArrayDictionary() {
		this.words = new String[MAX_CAPACITY];
		this.definitions = new String[MAX_CAPACITY];
		this.count = 0;
	}
	
	@Override
	public int size() {
		return this.count;
	}
	
	@Override
	public boolean isEmpty() {
		if(this.count == 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public String getDefinition(String word) throws WordNotInDictionaryException {
		/**
		 * 
		 * @param word an input word
		 * @return the associated definition of the input word if it exists in this dictionary
		 * @throws WordNotInDictionaryException if the input word does not exist
		 */
		int index = -1;
		
		for(int i = 0; i < this.count; i++) {
			if(this.words[i].equals(word)) {
				index = i;
			}
		}
		if(index == -1) {
			throw new WordNotInDictionaryException(word);
		}
		
		return this.definitions[index];
	}
	
	@Override
	public void insertEntry(String word, String definition)
			throws WordAlreadyExistsInDictionaryException, DictionaryFullException {
		/**
		 * Given inputs `word` and its associated `definition`, add them to the dictionary.
		 * @param word an input word
		 * @param definition associated definition of the input word
		 * @throws WordAlreadyExistsInDictionaryException if the new word already exists in the dictionary 
		 * @throws DictionaryFullException if the dictionary already stores the maximum number of entries
		 */
		if(this.count == 100) {
			throw new DictionaryFullException(word);
		}
		
		for(int i = 0; i < this.count; i++) {
			if(this.words[i].equals(word)) {
				throw new WordAlreadyExistsInDictionaryException(word);
			}
		}
		
		
		this.words[count] = word;
		this.definitions[count] = definition;
		this.count++;		
	}
	
	@Override
	public String removeWord(String word) throws WordNotInDictionaryException {
		/**
		 * Given an input `word`, remove it and its association, and return the removed definition.
		 * @param word an input word
		 * @return the removed definition
		 * @throws WordNotInDictionaryException if the input `word` exists in the dictionary
		 */
		
		int index = -1;
		
		for(int i = 0; i < this.count; i++) {
			if(this.words[i].equals(word)) {
				index = i;
			}
		}
		
		if(index == -1) {
			throw new WordNotInDictionaryException(word);
		}
		
		String answer = this.definitions[index];
		
		for(int i = index; i < MAX_CAPACITY; i++) {
			if(i == 99) {
				this.words[i] = null;
				this.definitions[i] = null;
			}
			else {
				this.words[i] = this.words[i + 1];
				this.definitions[i] = this.definitions[i + 1];
			}						
		}
		this.count = this.count - 1;
		
		return answer;
	}
	
	@Override
	public String[] getWords() {
		String[] collectionOfWords = new String[this.count];
		
		for(int i = 0; i < this.count; i++) {
			collectionOfWords[i] = this.words[i];
		}		
		
		return collectionOfWords;
	}
	
	@Override
	public String[] getDefinitions() {
		String[] collectionOfDefinitions = new String[this.count];
		
		for(int i = 0; i < this.count; i++) {
			collectionOfDefinitions[i] = this.definitions[i];
		}		
		
		return collectionOfDefinitions;
	}
	
	@Override
	public WordDefinitionPair[] getEntries() {
		/**
		 * All word-definition entries stored in the dictionary
		 * @return the collection of word-definition entries stored in this dictionary
		 */
		WordDefinitionPair[] collectionOfWordDefinitionPairs = new WordDefinitionPair[this.count];
		
		for(int i = 0; i < this.count; i++) {
			WordDefinitionPair entry = new WordDefinitionPair(this.words[i], this.definitions[i]);
			collectionOfWordDefinitionPairs[i] = entry;
		}
		
		return collectionOfWordDefinitionPairs;
	}
	
}
