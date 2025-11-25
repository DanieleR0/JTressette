package model;

public class UserProfile {
	
	private String nickname;
	private String avatarPath;
	private int gamesPlayed;
	private int gamesWon;
	private int gamesLost;
	private int level;
	
	public UserProfile (String nickname) {
		this.nickname = nickname;
		this.level = 1;
	}
	
	// --- METODI LOGICI ---
	
	public void registerWin() {
		gamesPlayed++;
		gamesWon++;
		updateLevel();
	}
	
	public void registerLoss() {
		gamesPlayed++;
		gamesLost++;
		updateLevel();
	}
	
	private void updateLevel() {
		this.level = 1 + (gamesWon / 10);	 // esempio semplice
	}
	
	// --- GETTER E SETTER ---
	
	public String getNickname() { 
		return nickname; 
		}
    public String getAvatarPath() { 
    	return avatarPath; 
    	}
    public int getGamesPlayed() { 
    	return gamesPlayed; 
    	}
    public int getGamesWon() { 
    	return gamesWon; 
    	}
    public int getGamesLost() { 
    	return gamesLost; 
    	}
    public int getLevel() { 
    	return level; 
    	}

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
	
}
