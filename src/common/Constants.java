package common;

public final class Constants {
    private Constants() {
    }

    // Used for factories
    public static final int NO_CONSUMER_PARAM = 3;
    public static final int NO_DISTRIB_PARAM = 6;
    public static final int NO_CONTRACT_PARAM = 4;
    public static final int NO_PRODUCER_PARAM = 5;

    // Factories -> String[] args
    public static final int ZEROTH_ARG = 0;
    public static final int FIRST_ARG = 1;
    public static final int SECOND_ARG = 2;
    public static final int THIRD_ARG = 3;
    public static final int FOURTH_ARG = 4;
    public static final int FIFTH_ARG = 5;

    // Used for price calculations
    public static final double PROFIT_FACTOR = 0.2;
    public static final double OVERDUE_FACTOR = 1.2;
}
