package model;

import java.util.ArrayList;

public class HumanPlayer extends Player {
	
	private UserProfile profile;

	public HumanPlayer(UserProfile profile) {
		super(profile.getNickname());
		this.profile = profile;
	}
	
	public UserProfile getProfile() {
		return profile;
	}

	@Override
	public Card chooseCardToPlay(CardSuit requiredSuit) {
		// la scelta verrà passata dal controller
		return null;
	}

}
