/**
 * Enumarations to keep function type of decision tree
 *
 */
public enum  FunctionType implements NodeType{

    // Enumurations

    /**
     * represents if food ahead arity function
     */
    IF_FOOD_AHEAD,

    /**
     * represents if danger ahead arity function
     */
    IF_DANGER_AHEAD,

    /**
     * represents if danger right arity function
     */
    IF_DANGER_RIGHT,

    /**
     * represents if danger left arity function
     */
    IF_DANGER_LEFT,

    /**
     * represents progn2 function
     */
    PROGN2;

    // string representations of enumarations
    private static final String IF_FOOD_AHEAD_TO_STR = "If food ahead";
    private static final String IF_DANGER_AHEAD_TO_STR = "If danger ahead";
    private static final String IF_DANGER_RIGHT_TO_STR = "If danger right";
    private static final String IF_DANGER_LEFT_TO_STR = "If danger left";
    private static final String PROGN2_TO_STR = "Progn2";

    @Override
    public String toString() {

        switch (this) {
            case IF_FOOD_AHEAD:
                return IF_FOOD_AHEAD_TO_STR;
            case IF_DANGER_AHEAD:
                return IF_DANGER_AHEAD_TO_STR;
            case IF_DANGER_RIGHT:
                return IF_DANGER_RIGHT_TO_STR;
            case IF_DANGER_LEFT:
                return IF_DANGER_LEFT_TO_STR;
            case PROGN2:
                return PROGN2_TO_STR;
        }

        return null;
    }
}
