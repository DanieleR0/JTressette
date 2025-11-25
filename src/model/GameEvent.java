package model;

public class GameEvent {

    public enum EventType {
        CARD_PLAYED,
        TRICK_COMPLETED,
        NEW_TURN,
        GAME_STARTED,
        GAME_ENDED
    }

    private final EventType type;
    private final Object payload;  // qui c’è la carta, la mano, o altro

    public GameEvent(EventType type, Object payload) {
        this.type = type;
        this.payload = payload;
    }

    public EventType getType() {
        return type;
    }

    public Object getPayload() {  // NON getData
        return payload;
    }
}