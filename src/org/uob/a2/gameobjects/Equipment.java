package org.uob.a2.gameobjects;

public class Equipment extends GameObject implements Usable {
   
    /**
     * Returns a string representation of this equipment, including the attributes inherited from {@code GameObject}
     * and the associated use information.
     *
     * @return a string describing the equipment
     */
    private UseInformation useInformation;

    public Equipment(String id, String name, String description, boolean hidden, UseInformation useInformation) {
        super(id, name, description, hidden);
        this.useInformation = useInformation;
    }

    @Override
    public void use() {
        if (!useInformation.isUsed()) {
            useInformation.setUsed(true); // Mark as used
            System.out.println(useInformation.getMessage()); // Show the action's message
        } 
        else {
            System.out.println("This item has already been used.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", useInformation=" + useInformation;
    }
}
