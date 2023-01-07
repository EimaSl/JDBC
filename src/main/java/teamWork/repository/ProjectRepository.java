package teamWork.repository;

import teamWork.entity.Guest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static teamWork.utils.DatabaseUtils.dbCon;


public class ProjectRepository {

    private static final String SELECT_FROM_GUESTS = "SELECT * FROM guest";
    private static final String SELECT_FROM_GUEST_BY_GIVEN_NAME = "select * from guest where name= ";
    private static final String SELECT_FROM_GUEST_OLDEST = "select * from guest where age = (SELECT MAX(age) FROM guest)";
    private static final String SELECT_FROM_GUEST_ENDS_BY_DOMAIN = "select * from guest where email_adress like '%";
    private static final String SELECT_FROM_GUEST_BY_NATIONALITY = "select * from guest where nationality = '";

    private static final String INSERT_NEW_GUEST = "INSERT INTO guest (name, age, email_adress, nationality) VALUES ('%s',%d,'%s','%s')";
    private static final String DELETE_GUEST = "delete from guest where id = ";
    private static final String UPDATE_GUEST = "Update guest set name ='%s', age = %d,email_adress = '%s', nationality = '%s' where id=";

    /*
 + select * from guest;
 + select * from guest where name="Stasys";
 + select * from guest where age = (SELECT MAX(age) FROM guest);
 + select * from guest where email_adress like '%com';
 + select * from guest where nationality = "Lietuvis";
 + insert into guest(name, age,email_adress,nationality) values ("Ona", 60,"ona@gmail.lt","Vokiete");
 + delete from guest where id = 5;
 + update guest set name="Rasa",age=30,email_adress='rasa@gmail.com' where id=6;
     */

    public List<Guest> findAll() {

        List<Guest> guests = new ArrayList<>();

        try {
            Statement statement = dbCon.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_GUESTS);

            guests = constructGuestList(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;
    }

    public List<Guest> selectFromGuests(String name) {

        List<Guest> guests = new ArrayList<>();


        try {
            Statement statement = dbCon.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_GUEST_BY_GIVEN_NAME + "'" + name + "'");
            guests = constructGuestList(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;

    }

    public List<Guest> selectFromGuestsOldest() {

        List<Guest> guests = new ArrayList<>();
        try {
            Statement statement = dbCon.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_GUEST_OLDEST);
            guests = constructGuestList(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;

    }

    public List<Guest> selectByMailDomain(String domain) {

        List<Guest> guests = new ArrayList<>();


        try {
            Statement statement = dbCon.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_GUEST_ENDS_BY_DOMAIN + domain + "'");
            guests = constructGuestList(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;

    }

    public List<Guest> selectByNationality(String nationality) {

        List<Guest> guests = new ArrayList<>();


        try {
            Statement statement = dbCon.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_GUEST_BY_NATIONALITY + nationality + "'");
            guests = constructGuestList(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;

    }

    public void insertNewGuest(Guest guest) {
        try {
            Statement statement = dbCon.createStatement();
            statement.executeUpdate(String.format(INSERT_NEW_GUEST, guest.getName(), guest.getAge(), guest.getEmailAddress(), guest.getNationality()));
            System.out.println("New Guest added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGuest(Integer id) {
        try {
            Statement statement = dbCon.createStatement();
            statement.executeUpdate(String.format(DELETE_GUEST + id));
            System.out.println("Guest deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGuest(Guest guest, Integer id) {
        try {
            Statement statement = dbCon.createStatement();
            statement.executeUpdate(String.format(UPDATE_GUEST, guest.getName(), guest.getAge(), guest.getEmailAddress(), guest.getNationality()) + id);
            System.out.println("Guest updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static List<Guest> constructGuestList(ResultSet resultSet) throws SQLException {
        List<Guest> guests = new ArrayList<>();

        while (resultSet.next()) {
            Guest guest = new Guest();
            guest.setId(resultSet.getInt("id"));
            guest.setName(resultSet.getString("name"));
            guest.setAge(resultSet.getInt("age"));
            guest.setEmailAddress(resultSet.getString("email_adress"));
            guest.setNationality(resultSet.getString("nationality"));
            guests.add(guest);
        }
        return guests;

    }


}
