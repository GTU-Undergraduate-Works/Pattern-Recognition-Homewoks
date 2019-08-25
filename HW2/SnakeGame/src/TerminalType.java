import javax.swing.tree.TreeNode;

/**
 * Enumarations to keep terminal type of decision tree
 *
 */

public enum TerminalType implements NodeType{

    // Enumrations

    /**
     * represents moving snake forward terminal command
     */
    MOVE_FORWARD,


    /**
     * represents turning snake left terminal command
     */
    TURN_LEFT,


    /**
     * represents turning snake right terminal command
     */
    TURN_RIGHT;

    // stirng representations of enumarations
    private static final String MOVE_FORWARD_TO_STR = "Move forward";
    private static final String TURN_LEFT_TO_STR = "Turn left";
    private static final String TURN_RIGHT_TO_STR = "Turn right";

    /**
     * returns string representation of this enumarations
     *
     * @return string representation of this enumarations
     */
    @Override
    public String toString() {

        switch (this) {
            case MOVE_FORWARD:
                return MOVE_FORWARD_TO_STR;
            case TURN_LEFT:
                return TURN_LEFT_TO_STR;
            case TURN_RIGHT:
                return TURN_RIGHT_TO_STR;
        }
        return null;
    }


}
