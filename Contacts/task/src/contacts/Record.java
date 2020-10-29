package contacts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.LocalDateTime.now;

public abstract class Record {

    private String phoneNumber;
    final private boolean isPerson;
    final private LocalDateTime created;
    private LocalDateTime lastEdit;

    public Record(String phoneNumber, boolean isPerson) {
        this.phoneNumber = phoneNumber;
        this.isPerson = isPerson;
        this.created = now();
        this.lastEdit = now();
    }

    public String getPhoneNumber() {
        if (hasNumber()) {
            return phoneNumber;
        }
        return "[no number]";
    }

    public boolean setPhoneNumber(String phoneNumber) {
        if (validatePhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
            setLastEdit();
            return true;
        }
        this.phoneNumber = "";
        setLastEdit();
        return false;
    }

    public LocalDateTime getLastEdit() {
        return lastEdit;
    }

    public final void setLastEdit() {
        this.lastEdit = now();
    }

    public boolean isPerson() {
        return isPerson;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        String text = "((\\+?(([a-zA-z0-9]+[- ][a-zA-z0-9]{2,})|([a-zA-z0-9]+[- ]\\([a-zA-z0-9]{2,}\\))|(\\([a-zA-z0-9]+\\)[- ][a-zA-z0-9]{2,})))|(\\+?)\\(?[a-zA-Z0-9]+\\)?)([- ][a-zA-z0-9]{2,})*";
        Pattern pattern = Pattern.compile(text);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    private boolean hasNumber() {
        return !"".equals(phoneNumber);
    }

    public abstract String sayYourName();

    public abstract String textFields();

    public abstract String[] getFieldsNames();

    public abstract boolean search(String search);

    @Override
    public String toString() {
        return "Number: " + phoneNumber +
                "\nTime created: " + created +
                "\nTime last edit: " + lastEdit;
    }

    public abstract void editField(String field, String value);
}
