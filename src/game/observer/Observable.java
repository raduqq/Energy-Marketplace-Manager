package game.observer;

public interface Observable {
    /**
     * Notifies all observers
     * @param arg needed for notification
     *            (this particular method: no argument is needed - failed to find a workaround)
     */
    void notify(Object arg);
}
