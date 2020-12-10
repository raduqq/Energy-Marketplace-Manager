package game;

public class Game {
    private int numberOfTurns;

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    @Override
    public String toString() {
        return "Game{" +
                "numberOfTurns=" + numberOfTurns +
                '}';
    }
}
