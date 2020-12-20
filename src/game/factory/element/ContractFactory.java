package game.factory.element;

import game.element.AbstractContract;
import game.element.Contract;

public class ContractFactory implements AbstractContractFactory{
    @Override
    public AbstractContract create(String[] args) {
        int issuerID = Integer.parseInt(args[0]);
        int beneficiaryID = Integer.parseInt(args[1]);
        int price = Integer.parseInt(args[2]);
        int remContractMonths = Integer.parseInt(args[3]);

        return new Contract(issuerID, beneficiaryID, price, remContractMonths);
    }
}
