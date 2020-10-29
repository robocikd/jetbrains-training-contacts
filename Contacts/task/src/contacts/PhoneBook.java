package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {

    private List<Record> records;

    public PhoneBook() {
        records = new ArrayList<>();
    }

    public List<Record> getRecords() {
        return records;
    }

    public void addRecord(Record record) {
        this.records.add(record);
    }

    public void removeRecord(int recordIndex) {
        this.records.remove(recordIndex);
    }

    public boolean hasRecords() {
        return this.records.size() > 0;
    }

    public void listRecords() {
        for (int i = 0; i < this.records.size(); i++) {
            System.out.println(i + 1 + ". " + this.records.get(i).sayYourName());
        }
    }
}
