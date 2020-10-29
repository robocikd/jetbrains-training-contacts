package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();

        while (true) {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            final String menu = scanner.nextLine();
            if ("exit".equals(menu)) {
                return;
            }
            if ("add".equals(menu)) {
                Record record;
                String number;
                System.out.print("Enter the type (person, organization): ");
                final String type = scanner.nextLine();
                if ("person".equals(type)) {
                    System.out.print("Enter the name: ");
                    final String name = scanner.nextLine();
                    System.out.print("Enter the surname: ");
                    final String surname = scanner.nextLine();
                    System.out.print("Enter the birth day: ");
                    LocalDate birthDate = LocalDate.parse("1800-01-01");
                    try {
                        birthDate = LocalDate.parse(scanner.nextLine());
                    } catch (DateTimeParseException e) {
                        System.out.println("Bad birth date!");
                    }
                    System.out.print("Enter the gender (M, F): ");
                    final String gender = scanner.nextLine();
                    if (!"M".equals(gender) && !"F".equals(gender)) {
                        System.out.println("Bad gender!");
                    }
                    System.out.print("Enter the number: ");
                    number = scanner.nextLine();
                    number = Record.validatePhoneNumber(number) ? number : "";
                    record = new Person(number, true, name, surname, birthDate, gender);
                } else {
                    System.out.print("Enter the organization name: ");
                    final String organizationName = scanner.nextLine();
                    System.out.print("Enter the address: ");
                    final String address = scanner.nextLine();
                    System.out.print("Enter the number: ");
                    number = scanner.nextLine();
                    number = Record.validatePhoneNumber(number) ? number : "";
                    record = new Organization(number, false, organizationName, address);
                }
                phoneBook.addRecord(record);
                if ("".equals(number)) {
                    System.out.println("Wrong number format!");
                }
                System.out.println("The record added.\n");
            } else if ("search".equals(menu)) {
                do {
                    System.out.print("Enter search query: ");
                    final String search = scanner.nextLine();
                    final List<Record> records = phoneBook.getRecords();

                    int counter = 0;
                    List<Integer> indexes = new ArrayList<>();
                    for (int i = 0; i < records.size(); i++) {
                        final boolean found = records.get(i).search(search);
                        if (found) {
                            indexes.add(i);
                            counter++;
                        }
                    }
                    System.out.println("Found " + counter + " results:");
                    for (int i = 0; i < indexes.size(); i++) {
                        System.out.println(i + 1 + ". " + records.get(indexes.get(i)).sayYourName());
                    }
                    System.out.println("\n[search] Enter action ([number], back, again): ");
                    final String action = scanner.nextLine();
                    if(isNumber(action)){
                        records.get(Integer.parseInt(action)-1).sayYourName();
                        System.out.println();
                        break;
                    }else if("back".equals(action)){
                        break;
                    }
                } while (true);

            } else if ("edit".equals(menu)) {
                if (!phoneBook.hasRecords()) {
                    System.out.println("No records to edit!\n");
                    continue;
                }
                phoneBook.listRecords();
                System.out.print("Select a record: ");
                Integer selectedRecord = Integer.valueOf(scanner.nextLine());
                Record record = phoneBook.getRecords().get(selectedRecord - 1);
                if (record.isPerson()) {
                    System.out.println("Select a field (name, surname, birth, gender, number): ");
                    String selectedField = scanner.nextLine();
                    if ("name".equals(selectedField)) {
                        System.out.print("Enter name: ");
                        ((Person) record).setName(scanner.nextLine());
                    } else if ("surname".equals(selectedField)) {
                        System.out.print("Enter surname: ");
                        ((Person) record).setSurname(scanner.nextLine());
                    } else if ("birth".equals(selectedField)) {
                        System.out.print("Enter the birth day: ");
                        final LocalDate localDate = LocalDate.parse(scanner.nextLine());
                        ((Person) record).setBirthDate(localDate);
                    } else if ("gender".equals(selectedField)) {
                        System.out.println("Enter the gender (M, F): ");
                        ((Person) record).setGender(scanner.nextLine());
                    } else if ("number".equals(selectedField)) {
                        System.out.print("Enter number: ");
                        record.setPhoneNumber(scanner.nextLine());
                    }
                } else {
                    System.out.println("Select a field (name, address, number): ");
                    String selectedField = scanner.nextLine();
                    if ("name".equals(selectedField)) {
                        ((Organization) record).setOrganizationName(scanner.nextLine());
                    } else if ("address".equals(selectedField)) {
                        ((Organization) record).setAddress(scanner.nextLine());
                    } else if ("number".equals(selectedField)) {
                        record.setPhoneNumber(scanner.nextLine());
                    }
                }
                System.out.println("The record updated\n");

            } else if ("count".equals(menu)) {
                System.out.println("The Phone Book has " + phoneBook.getRecords().size() + " records.\n");
            } else if ("list".equals(menu)) {
                if (phoneBook.hasRecords()) {
                    phoneBook.listRecords();
                    System.out.print("\n[list] Enter action ([number], back): ");
                    final String choice = scanner.nextLine();
                    if (isNumber(choice)) {
                        final Record record = phoneBook.getRecords().get(Integer.parseInt(choice) - 1);
                        System.out.println(record);
                        System.out.println();
                        boolean wantToEdit = true;
                        do {
                            System.out.print("[record] Enter action (edit, delete, menu): ");
                            final String action = scanner.nextLine();
                            switch (action) {
                                case "edit":
                                    final String[] fieldsNames = record.getFieldsNames();
                                    System.out.print(record.textFields());
                                    final String field = scanner.nextLine();
                                    for (String s : fieldsNames) {
                                        if (s.equals(field)) {
                                            System.out.print("Enter " + s + ": ");
                                            final String value = scanner.nextLine();
                                            record.editField(field, value);
                                            System.out.println("Saved");
                                            break;
                                        }
                                    }
                                    System.out.println(record);
                                    break;
                                case "delete":
                                    if (!phoneBook.hasRecords()) {
                                        System.out.println("No records to remove!\n");
                                        break;
                                    }
                                    phoneBook.listRecords();
                                    System.out.print("Select a record: ");
                                    final Integer selectedRecord = Integer.valueOf(scanner.nextLine());
                                    phoneBook.removeRecord(selectedRecord - 1);
                                    System.out.println("The record removed!\n");
                                    wantToEdit = false;
                                    break;
                                case "menu":
                                    wantToEdit = false;
                                    break;
                            }
                        } while (wantToEdit);
                    }
                    System.out.println();
                } else {
                    System.out.println("No records to list!\n");
                }
            }
        }
    }

    public static boolean isNumber(String string) {
        if (string == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
