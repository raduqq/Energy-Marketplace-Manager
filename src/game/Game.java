package game;

import game.database.Consumers;
import game.database.Distributors;
import game.player.Consumer;
import game.player.Distributor;

public final class Game {
    private static final Game instance;

    // puteai sa agregi aici o lista de players si terminai situatia

    private static int numberOfTurns;

    // de inclus si Game.reset();
    static { instance = new Game(); }

    public static Game getInstance() { return instance; }

    public static int getNumberOfTurns() {
        return numberOfTurns;
    }

    public static void setNumberOfTurns(int numberOfTurns) {
        Game.numberOfTurns = numberOfTurns;
    }

    public static void update() {
        // add new consumers(from monthlyUpdates)

        // cum faci ca sa nu pasezi aiurea prin program? dai remove la
        // monthlyUpdates.get(0) si gata :))

        /*
          treci prin toti Consumerii si toti Distributorii (inclusiv contractele) si ii updatezi
          intr-o alta varianta, puteam face asta unified, sa ai Players in loc de Consumers si Distributors
          !!! TODO: trebuie dat ca parametru monthlyUpdates aici
         */
        for (Consumer consumer : Consumers.getConsumerList()) { consumer.update(); }
        for (Distributor distributor : Distributors.getDistributorList()) { distributor.update(); }
    }

    public static void play() {
        for (int i = 0; i < numberOfTurns; i++) {
            System.out.println("Dummy play");
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "numberOfTurns=" + numberOfTurns +
                '}';
    }
}
