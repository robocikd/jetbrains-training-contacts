package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person extends Record {
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String gender;

    public Person(String phoneNumber, boolean isPerson, String name, String surname, LocalDate birthDate, String gender) {
        super(phoneNumber, isPerson);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    private boolean validateGender(String gender) {
        return "M".equals(gender) || "F".equals(gender);
    }

    public void setName(String name) {
        this.name = name;
        this.setLastEdit();
    }

    public void setSurname(String surname) {
        this.surname = surname;
        this.setLastEdit();
    }

    public boolean setBirthDate(LocalDate birthDate) {
        this.setLastEdit();
        if (validateBirthDate(birthDate)) {
            this.birthDate = birthDate;
            return true;
        }
        return false;
    }

    private boolean validateBirthDate(LocalDate birthDate) {
        return !LocalDate.parse("1800-01-01").equals(birthDate);
    }

    public void setGender(String gender) {
        this.gender = gender;
        this.setLastEdit();
    }

    @Override
    public String sayYourName() {
        return this.name + " " + this.surname;
    }

    @Override
    public String[] getFieldsNames() {
        return new String[]{"name", "surname", "birth", "gender", "number"};
    }

    @Override
    public String textFields() {
        return "Select a field (name, surname, birth, gender, number): ";
    }

    @Override
    public void editField(String field, String value) {
        switch (field) {
            case "name":
                setName(value);
                break;
            case "surname":
                setSurname(value);
                break;
            case "birth":
            try {
                setBirthDate(LocalDate.parse(value));
            }catch (DateTimeParseException e){
                System.out.println("\n***** Bad date format! Try yyyy-MM-dd ****\n");
            }
                break;
            case "gender":
                setGender(value);
                break;
            case "number":
                setPhoneNumber(value);
                break;
        }
    }

    @Override
    public boolean search(String search) {
        String text = ".*" + search + ".*";
        Pattern pattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
        Matcher matcher;

        matcher = pattern.matcher(this.name);
        if(matcher.matches()){
            return true;
        }
        matcher = pattern.matcher(this.surname);
        if(matcher.matches()){
            return true;
        }
        matcher = pattern.matcher(getPhoneNumber());
        if(matcher.matches()){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String genderToDisplay = validateGender(gender) ? gender : "[no data]";
        String birthDateToDisplay = validateBirthDate(birthDate) ? birthDate.toString() : "[no data]";
        return "Name: " + name +
                "\nSurname: " + surname +
                "\nBirth date: " + birthDateToDisplay +
                "\nGender: " + genderToDisplay +
                "\n" + super.toString();
    }
}
