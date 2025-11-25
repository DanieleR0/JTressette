package model;

public enum CardSuit {
	BASTONI("Bastoni"),
	COPPE("Coppe"),
	DENARI("Denari"),
	SPADE("Spade");

	private final String name;
	
	CardSuit(String name) {
		this.name = name;
	}
	
	public String getSuitName() { 
		return name; 
	}

}
