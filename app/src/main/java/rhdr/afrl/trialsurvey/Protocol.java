package rhdr.afrl.trialsurvey;

public class Protocol {

    //private variables
    int _id;
    String _name;
    String _numSubjects;
    String _numShotcodes;

    // Empty constructor
    public Protocol(){
    }

    // constructor
    public Protocol(int id, String name, String _numSubjects, String _numShotcodes){
        this._id = id;
        this._name = name;
        this._numSubjects = _numSubjects;
        this._numShotcodes =  _numShotcodes;
    }

    // constructor
    public Protocol(String name, String _numSubjects, String _numShotcodes){
        this._name = name;
        this._numSubjects = _numSubjects;
        this._numShotcodes =  _numShotcodes;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting numSubjects
    public String getnumSubjects(){
        return this._numSubjects;
    }

    // setting numSubjects
    public void setnumSubjects(String numSubjects){
        this._numSubjects = numSubjects;
    }

    // getting numShotcodes
    public String getnumShotcodes(){
        return this._numShotcodes;
    }

    // setting numShotcodes
    public void setnumShotcodes(String numShotcodes){
        this._numShotcodes = numShotcodes;
    }
}
