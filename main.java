import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Service serv = new Service();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println(
                "1. Add Pet \n" +
                "2. Update Pet\n" +
                "3. Fetch All Pets\n" +
                "4. Delete Pet by ID\n" +
                "5. Get Pet by ID\n" +
                "Any other key to exit"
            );

            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {

                case 1:
                    System.out.println("Add Pet");
                    Pets pet = new Pets();

                    System.out.print("Enter Pet ID: ");
                    pet.setPetId(sc.nextInt());
                    sc.nextLine();

                    if(serv.getPetById(pet.getPetId()) != null) {
                        System.out.println("Pet with ID " + pet.getPetId() + " already exists.");
                        System.out.println("Enter valid pet ID");
                        pet.setPetId(sc.nextInt());
                        sc.nextLine();
                    }

                    System.out.print("Enter Name: ");
                    pet.setName(sc.nextLine());

                    System.out.print("Enter Species: ");
                    pet.setSpecies(sc.nextLine());

                    System.out.print("Enter Age: ");
                    try {
                        pet.setAge(sc.nextLine()); // regex validation
                        serv.addPet(pet);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Update Pet");
                    Pets updatePet = new Pets();

                    System.out.print("Enter Pet ID: ");
                    updatePet.setPetId(sc.nextInt());
                    sc.nextLine();

                    if(serv.getPetById(updatePet.getPetId()) == null) {
                        System.out.println("Pet with ID " + updatePet.getPetId() + " does not exist.");
                        break;
                    }

                    System.out.print("Enter New Name: ");
                    updatePet.setName(sc.nextLine());

                    System.out.print("Enter New Species: ");
                    updatePet.setSpecies(sc.nextLine());

                    System.out.print("Enter New Age: ");
                    try {
                        updatePet.setAge(sc.nextLine());
                        serv.updatePet(updatePet);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println(serv.getAllPets());
                    break;

                case 4:
                    System.out.print("Enter Pet ID to delete: ");
                    int id = sc.nextInt();
                    serv.deletePetById(id);
                    break;

                case 5:
                    System.out.print("Enter Pet ID to fetch: ");
                    id = sc.nextInt();
                    System.out.println(serv.getPetById(id));
                    break;

                default:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
            }

        } while (true);
    }
}
