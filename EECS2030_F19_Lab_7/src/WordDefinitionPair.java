
public class WordDefinitionPair {
	private String word;
	private String definition;
	
	public WordDefinitionPair(String word, String definition) {
		this.word = word;
		this.definition = definition;
	}
	
	public String getWord() {		
		return this.word;
	}
	
	public String getDefinition() {
		return this.definition;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof WordDefinitionPair)) {
			return false;
		}
		
		WordDefinitionPair w = (WordDefinitionPair) o;
		
		if((this.word == w.word) && this.definition == w.definition) {
			return true;
		}
		
		return true;
	}
	
}
