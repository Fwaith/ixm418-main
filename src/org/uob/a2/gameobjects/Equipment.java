package org.uob.a2.gameobjects;

public class Equipment extends GameObject implements Usable {
    protected UseInformation useInformation;

    public Equipment(String id, String name, String description, boolean hidden, UseInformation useInformation) {
        super(id, name, description, hidden);
        this.useInformation = useInformation;
    }

    public void setUseInformation(UseInformation useInformation) {
        this.useInformation = useInformation;
    }

    public UseInformation getUseInformation() {
        return useInformation;
    }

    public String use(GameObject target, GameState gameState) {
        if (!(target instanceof Container)) {
            return "This equipment cannot be used on the target.";
        }

        Container container = (Container) target;

        // Verify the target matches the expected target in the use information
        if (!container.getId().equals(useInformation.getTarget())) {
            return "The equipment doesn't work on this target.";
        }

        // Perform the action: reveal contents of the container
        for (GameObject obj : container.open())  {
            obj.setHidden(false); // Reveal all hidden objects in the container
        }

        // Mark the equipment as used
        useInformation.setUsed(true);

        // Return the result message from the use information
        return useInformation.getMessage();
    }

    /**
     * Returns a string representation of this equipment, including the attributes inherited from {@code GameObject}
     * and the associated use information.
     *
     * @return a string describing the equipment
     */
    @Override
    public String toString() {
        return super.toString() + ", useInformation=" + useInformation;
    }
}
