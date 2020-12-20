package game.factory.element;

import common.Constants;
import game.element.AbstractContract;
import game.element.Contract;

public final class ContractFactory implements AbstractContractFactory {
    @Override
    public AbstractContract create(final String[] args) {
        int beneficiaryID = Integer.parseInt(args[Constants.ZEROTH_ARG]);
        int price = Integer.parseInt(args[Constants.FIRST_ARG]);
        int remContractMonths = Integer.parseInt(args[Constants.SECOND_ARG]);

        return new Contract(beneficiaryID, price, remContractMonths);
    }
}
