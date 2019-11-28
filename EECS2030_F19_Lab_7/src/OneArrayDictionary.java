/*
 * You are required to use the given `dict` array to implement the methods.
 * See test_one_array_implementation_insert and test_one_array_implementation_remove 
 * in class TestArrayImplementations.
 *
 * Requirements:
 * 		- You are not allowed to add new attributes to this class.
 * 		- You are not allowed to use any Java collection library (no import statements).
 * 
 * The idea is that `dict` is initialized as an array of size `MAX_CAPACITY` with each slot storing null.
 * Entries are added from left to right, whereas all free slots remain null.
 * When an entry is removed, all slots to its right are shifted to the left to close the gap.
 * 
 * For example, given a dictionary with four entries:
 * {(w1, d1), (w2, d2), (w3, d3), (w4, d4), null, null, ...} 
 * Removing the entry for word `w2` has the resulting dictionary:
 * {(w1, d1), (w3, d3), (w4, d4), null, null, null, ...}
 * 
 * You may consider studying this note on manipulating basic array:
 * https://www.eecs.yorku.ca/~jackie/teaching/lectures/2019/F/EECS2030/notes/EECS2030_F19_Notes_Tracing_PointCollectorTester.pdf
 */

public class OneArrayDictionary implements Dictionary {
	
	int MAX_CAPACITY = 100;
	int count = 0;
	WordDefinitionPair[] dict;
	
	/*
	 * Your tasks: declare and implement methods from the Dictionary interface.
	 */
	public OneArrayDictionary() {
		this.dict = new WordDefinitionPair[MAX_CAPACITY];
		this.count = 0;		
	}


	@Override
	public int size() {
		return this.count;
	}

	@Override
	public boolean isEmpty() {
		if(count == 0) {
			return true;
		}
		return false;
	}

	@Override
	public String getDefinition(String word) throws WordNotInDictionaryException {
		int index = -1;
		
		for(int i = 0; i < this.count; i++) {
			if(this.dict[i].getWord().equals(word)) {
				index = i;
			}else {
				
			}
		}
		/*
		 *  IF the word does not match any word in the array, then throw an exception
		 *  If it exists then return the definition
		 */
		if(index == -1) {
			throw new WordNotInDictionaryException(word);
		}
		
		return this.dict[index].getDefinition();		
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
			if(this.dict[i].getWord().equals(word)) {
				throw new WordAlreadyExistsInDictionaryException(word);
			}
		}
		
		
		WordDefinitionPair pair = new WordDefinitionPair(word, definition);
			
		this.dict[count] = pair;
		count++;
				
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
		
		for(int i = 0; i < this.count && (index == -1); i++) {
			if(this.dict[i].getWord().equals(word)) {
				index = i;
			}
		}
		/*
		 *  If the word does not match any word in the array, then throw an exception
		 *  If it exists then return the definition
		 */
		if(index == -1) {
			throw new WordNotInDictionaryException(word);
		}
		// Word is in the array, now remove it.
		String removedDefinition = this.dict[index].getDefinition();
		for(int i = index; i < MAX_CAPACITY; i++) {
			if(i == MAX_CAPACITY - 1) {
				this.dict[i] = null;
			}else {
				this.dict[i] = this.dict[i + 1];
			}			
		}
		count = count - 1;
		
		return removedDefinition;
	}

	@Override
	public String[] getWords() {
		/**
		 * All words stored in the dictionary
		 * @return the collection of words stored in this dictionary
		 */
		// Make a new array of size this.count, and copy all the words from each index of this.dict, and return the new array.		
		String[] collectionOfWords = new String[this.count];
		
		for(int i = 0; i < collectionOfWords.length; i++) {
			collectionOfWords[i] = this.dict[i].getWord();
		}
		
		return collectionOfWords;
	}

	@Override
	public String[] getDefinitions() {
		/**
		 * All definitions stored in the dictionary
		 * @return the collection of definitions stored in this dictionary
		 */
		// Make a new array of size this.count, and copy all definitions from each index of this.dict, and return the new array.
		String[] collectionOfDefinitions = new String[this.count];
		
		for(int i = 0; i < collectionOfDefinitions.length; i++) {
			collectionOfDefinitions[i] = this.dict[i].getDefinition();
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
		
		for(int i = 0; i < collectionOfWordDefinitionPairs.length; i++) {
			collectionOfWordDefinitionPairs[i] = this.dict[i];
		}
		
		return collectionOfWordDefinitionPairs;
	}
}
