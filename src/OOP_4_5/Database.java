package OOP_4_5;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Person> people = new ArrayList<>();

    Person p1 = new Person
            ("Melvin", 24, 180, "address1", "070123456789");
    Person p2 = new Person
            ("Tim", 36, 197, "address2", "070123987654");


    public Database() {
        people.add(p1);
        people.add(p2);
    }

    public Person getPerson(String name){
        for (Person p : people) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

}
