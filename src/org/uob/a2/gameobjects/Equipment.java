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
        for (GameObject obj : container.open()) {
            obj.setHidden(false); // Reveal all hidden objects in the container
        }

        // Mark the equipment as used
        useInformation.setUsed(true);

        // In addition to opening the container, the "result" might be an item in the room to reveal
        // Locate the item specified by useInformation.getResult() in the current room and reveal it
        Map map = gameState.getMap();
        Room currentRoom = map.getCurrentRoom();
        // Attempt to find the result item/equipment by ID in the room and reveal it
        // Since we know result is "item1" or some item ID, we look for it in room's items
        for (Item roomItem : currentRoom.getItems()) {
            if (roomItem.getId().equals(useInformation.getResult())) {
                roomItem.setHidden(false);
            }
        }
        for (Equipment eq : currentRoom.getEquipments()) {
            if (eq.getId().equals(useInformation.getResult())) {
                eq.setHidden(false);
            }
        }

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