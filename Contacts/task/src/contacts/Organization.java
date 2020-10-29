package contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Organization extends Record {
    private String organizationName;
    private String address;

    public Organization(String phoneNumber, boolean isPerson, String organizationName, String address) {
        super(phoneNumber, isPerson);
        this.organizationName = organizationName;
        this.address = address;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
        this.setLastEdit();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        this.setLastEdit();
    }

    @Override
    public String sayYourName() {
        return this.organizationName;
    }

    @Override
    public String[] getFieldsNames() {
        return new String[]{"name", "address", "number"};
    }

    @Override
    public String textFields() {
        return "Select a field (name, address, number): ";
    }

    @Override
    public void editField(String field, String value) {
        switch (field) {
            case "name":
                setOrganizationName(value);
                break;
            case "address":
                setAddress(value);
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

        matcher = pattern.matcher(this.organizationName);
        if (matcher.matches()) {
            return true;
        }
        matcher = pattern.matcher(this.address);
        if (matcher.matches()) {
            return true;
        }
        matcher = pattern.matcher(getPhoneNumber());
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Organization name: " + organizationName +
                "\nAddress: " + address +
                "\n" + super.toString();
    }
}
