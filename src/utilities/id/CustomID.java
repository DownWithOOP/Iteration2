package utilities.id;

/**
 * Created by jordi on 2/24/2017.
 */
public class CustomID {
    //TODO:modify this accordingly
    IdType type;
    String id;

    public CustomID(IdType idType, String id) {
        type = idType;
        this.id = id;
    }

    public IdType getIdType() {
        return type;
    }

    public String getId() {
        return id;
    }


    public boolean equals(CustomID customID) {
        return (type == customID.getIdType() && id.equals(customID.getId()));
    }

    @Override
    public String toString() {
        return type.name() + id;
    }
}
