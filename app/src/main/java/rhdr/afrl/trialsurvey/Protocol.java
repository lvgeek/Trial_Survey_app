package rhdr.afrl.trialsurvey;

public class Protocol {

    //private variables
    int _id;
    String _name;
    int _numSubjects;
    int _numShotcodes;

    // Empty constructor
    public Protocol() {
    }

    // constructor
    public Protocol(int id, String name, int numSubjects, int numShotcodes) {
        this._id = id;
        this._name = name;
        this._numSubjects = numSubjects;
        this._numShotcodes = numShotcodes;
    }

    // constructor
    public Protocol(String name, int numSubjects, int numShotcodes) {
        this._name = name;
        this._numSubjects = numSubjects;
        this._numShotcodes = numShotcodes;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int id) {
        this._id = id;
    }

    // getting name
    public String getName() {
        return this._name;
    }

    // setting name
    public void setName(String name) {
        this._name = name;
    }

    // getting numSubjects
    public int getnumSubjects() {
        return this._numSubjects;
    }

    // setting numSubjects
    public void setnumSubjects(int numSubjects) {
        this._numSubjects = numSubjects;
    }

    // getting numShotcodes
    public int getnumShotcodes() {
        return this._numShotcodes;
    }

    // setting numShotcodes
    public void setnumShotcodes(int numShotcodes) {
        this._numShotcodes = numShotcodes;
    }

}