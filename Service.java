import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Service implements DAO {

    private File data;
    private List<Pets> pets;

    public Service() {
        data = new File("petinfo.doc");
        pets = new ArrayList<>();   // ALWAYS initialize

        if (!data.exists()) {
            writeToFile();
        }
    }

    // Serialization
    private void writeToFile() {
        try (FileOutputStream fos = new FileOutputStream(data);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(pets);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Deserialization
    
    private void readFromFile() {
        try (FileInputStream fis = new FileInputStream(data)) {

            if (data.length() == 0) {
                pets = new ArrayList<>();
            } else {
                ObjectInputStream ois = new ObjectInputStream(fis);
                pets = (List<Pets>) ois.readObject();
                ois.close();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            pets = new ArrayList<>();
        }
    }

    @Override
    public void addPet(Pets pet) {
        readFromFile();
        pets.add(pet);
        writeToFile();
        System.out.println("Pet added successfully");
    }

    @Override
    public void updatePet(Pets pet) {
        readFromFile();
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getPetId() == pet.getPetId()) {
                pets.set(i, pet);
                writeToFile();
                System.out.println("Pet updated successfully");
                return;
            }
        }
        System.out.println("Pet not found");
    }

    @Override
    public List<Pets> getAllPets() {
        readFromFile();
        return pets;
    }

    @Override
    public Pets getPetById(int petId) {
        readFromFile();
        for (Pets pet : pets) {
            if (pet.getPetId() == petId) {
                return pet;
            }
        }
        return null;
    }

    @Override
    public void deletePetById(int petId) {
        readFromFile();
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getPetId() == petId) {
                pets.remove(i);
                writeToFile();
                System.out.println("Pet deleted successfully");
                return;
            }
        }
        System.out.println("Pet not found");
    }
}
