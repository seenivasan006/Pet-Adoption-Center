import java.util.List;

public interface DAO {

    void addPet(Pets pet);

    void updatePet(Pets pet);

    List<Pets> getAllPets();

    Pets getPetById(int petId);

    void deletePetById(int petId);
}
