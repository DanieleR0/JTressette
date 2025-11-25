package model;

public enum CardValue {
	TRE(10, "Tre", 1.0),
    DUE(9, "Due", 0.33),
    ASSO(8, "Asso", 1.0),
    RE(7, "Re", 0.33),
    CAVALLO(6, "Cavallo", 0.33),
    FANTE(5, "Fante", 0.33),
    SETTE(4, "Sette", 0.0),
    SEI(3, "Sei", 0.0), 
    CINQUE(2, "Cinque", 0.0), 
    QUATTRO(1, "Quattro", 0.0);
	
	private final int cardValue;
	private final String cardName;
	private final double pointValue;

	CardValue(int cardValue, String cardName, double pointValue) {
		this.cardValue = cardValue;
		this.cardName = cardName;
		this.pointValue = pointValue;
	}
	
	//------- GETTER -------\\
	
	public int getCardValue() {
		return cardValue;
	}

	public String getCardName() {
		return cardName;
	}

	public double getPointValue() {
		return pointValue;
	}

}
