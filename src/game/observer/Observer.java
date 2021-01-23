package game.observer;

public interface Observer {
    /**
     * Makes updates when notified by Subject
     * @param arg needed for update
     *            (this particular method: no argument is needed - failed to find a workaround)
     */
    void update(Object arg);
}
