package org.uob.a2.gameobjects;

/**
 * Represents information about how an object can be used in the game.
 * 
 * <p>
 * This class stores details about the usage of an object, such as whether it has
 * already been used, the type of action it performs, the target of the action,
 * the result of the action, and any associated message.
 * </p>
 */
public class UseInformation {
    private boolean isUsed; // Whether the object has been used
    private String action; // The action performed by the object (e.g., "heal", "unlock")
    private String target; // The target of the action (e.g., "door", "player")
    private String result; // The result of the action (e.g., "door unlocked")
    private String message; // A message describing the action to the player

    // Constructor to initialize use information
    public UseInformation(String action, String target, String result, String message) {
        this.isUsed = false;
        this.action = action;
        this.target = target;
        this.result = result;
        this.message = message;
    }

    // Getters and setters
    public boolean isUsed() { return isUsed; }
    public void setUsed(boolean used) { isUsed = used; }
    public String getMessage() { return message; }
    /**
     * Returns a string representation of the usage information, including all attributes.
     *
     * @return a string describing the usage information
     */
    @Override
    public String toString() {
        return "UseInformation{" +
                "isUsed=" + isUsed +
                ", action='" + action + '\'' +
                ", target='" + target + '\'' +
                ", result='" + result + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
