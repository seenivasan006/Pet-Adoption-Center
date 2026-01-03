import java.io.Serializable;

public class Pets implements Serializable {

    private static final long serialVersionUID = 1L;

    private int petId;
    private String name;
    private String species;
    private int age;

    public Pets() {}

    public Pets(int petId, String name, String species, String age) {
        this.petId = petId;
        this.name = name;
        this.species = species;
        setAge(age);
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(String age) {
        if (age.matches("\\d+")) {
            this.age = Integer.parseInt(age);
        } else {
            throw new IllegalArgumentException("Invalid age! Age must be numeric.");
        }
    }

    @Override
    public String toString() {
        return "Pets [petId=" + petId +
               ", name=" + name +
               ", species=" + species +
               ", age=" + age + "]";
    }
}
