package teamWork;

import teamWork.entity.Guest;
import teamWork.repository.ProjectRepository;

import java.util.List;

public class Main {

    public static void main(String[] args) {


        ProjectRepository projectRepository = new ProjectRepository();

        List<Guest> allGuests = projectRepository.findAll();
        List<Guest> ona = projectRepository.selectFromGuests("Ona");
        List<Guest> oldestGuests = projectRepository.selectFromGuestsOldest();
        List<Guest> lt = projectRepository.selectByMailDomain("lt");
        List<Guest> lietuvis = projectRepository.selectByNationality("Lietuvis");
        System.out.println(lietuvis);

        //create
        Guest newGuest21 = new Guest();
        newGuest21.setName("Rokas");
        newGuest21.setAge(21);
        newGuest21.setEmailAddress("rokas@lt");
        newGuest21.setNationality("Lietuvis");
        projectRepository.insertNewGuest(newGuest21);
        Guest newGuest22 = new Guest("Ona", 60, "ona@gmail.lt", "Vokiete");
        projectRepository.insertNewGuest(newGuest22);

        //delete
        projectRepository.deleteGuest(8);

        //update
        projectRepository.updateGuest(new Guest("Ona", 60, "ona@gmail.lt", "test"), 6);
    }
}
