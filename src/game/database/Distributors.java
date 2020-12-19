package game.database;

import game.player.Consumer;
import game.player.Distributor;

import java.util.ArrayList;
import java.util.List;

public final class Distributors {
    private static final Distributors instance;

    private static List<Distributor> distributorList;

    private Distributors() { distributorList = new ArrayList<>(); }

    static {
        instance = new Distributors();
    }

    public static Distributors getInstance() {
        return instance;
    }

    public List<Distributor> getDistributorList() {
        return distributorList;
    }

    public void addToDB(Distributor distributor) {
        Distributors.distributorList.add(distributor);
    }

    public Distributor findDistributorByID (int id) {
        for (Distributor distributor : distributorList) {
            if (distributor.getId() == id) {
                return distributor;
            }
        }

        return  null;
    }
}
