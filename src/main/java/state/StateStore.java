package state;

/**
 * StateStore class holds the currect state of the application. 
 * It can also be used to initiate a change to state.
 */
public class StateStore {

    private static StateStore instance;

    private State currentState;
    private IReducer<State> reducer;

    /**
     * Constructor is private since Singleton pattern is in play.
     */
    @SuppressWarnings("unchecked")
    private StateStore() {
        currentState = new State();
        reducer = new PoorReducer();
    }

    /**
     * updateInValues is the method responsible to iniate a recalculation of the state of the application.
     * 
     * @param action - What state variable to update.
     * @param value - new value for the variable thats updating.
     */
    public void updateInValues(String action, int value) {
        currentState = reducer.reduce(currentState, action, value);

        //TODO: We might need to notify all interested partys that our state has changed!
    }
    
    /**
     * Retrive the current state of application.
     * 
     * @return State - current state of the application
     */
    public State getState() {
        return currentState;
    }

    /**
     * Gives back the and and only instance of this class.
     * 
     * @return StateStore - this.
     */
    public static synchronized StateStore getInstance() {
        if (instance == null) {
            instance = new StateStore();
        }

        return instance;
    }
}
