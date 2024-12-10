package org.uob.a2.gameobjects;

/**
 * Represents a generic game object that can be part of the game world.
 * 
 * <p>
 * Game objects have a name, description, unique identifier, and visibility state.
 * This abstract class serves as a base for more specific types of game objects.
 * </p>
 */
public abstract class GameObject {
    
    String id; // Unique identifier for the object
    protected String name; // Name of the object
    protected String description; // Description of the object
    protected boolean hidden; // Whether the object is initially hidden

    // Constructor to initialize a game object
    public GameObject(String id, String name, String description, boolean hidden) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hidden = hidden;
    }

    // Default constructor
    public GameObject() {
        this.id = "default_id";
        this.name = "Default Name";
        this.description = "Default Description";
        this.hidden = false; // Default visibility is not hidden
    }
 
    // Getters for attributes 
    public String getId() { 
        return id;
    }
    public String getName() { 
        return name; 
    }
    public String getDescription() { 
        return description; 
    }
    public boolean isHidden() { 
        return hidden; 
    }

    // Setters for attributes
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setHidden(boolean hidden) { 
        this.hidden = hidden;
    }
   
    /**
     * Returns a string representation of the game object, including its ID, name,
     * description, and visibility state.
     *
     * @return a string describing the game object
     */
    @Override
    public String toString() {
        return "GameObject {" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", hidden=" + hidden +
               '}';
    }
    
}