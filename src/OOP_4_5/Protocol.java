package OOP_4_5;

public class Protocol {

    final protected int INITIAL = 0;
    final protected int INTHELOOP = 1;

    protected int state = INITIAL;

    Database db = new Database();

    public String getOutPut(String fromClient){

        Person p = db.getPerson(fromClient);

        if (state == INITIAL) {
            state = INTHELOOP;
            return "Enter a name: ";
        }
        else if (state == INTHELOOP) {
            if (p == null) {
                return fromClient + " finns inte i systemet.\nEnter a name: ";
            }
            return p + "\nEnter a name: ";
        }
        return "state error.";
    }
}
