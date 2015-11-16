package rhdr.afrl.trialsurvey;

public class Protocol {

    //private variables
    int _id;
    String _name;
    int _numSubjects;
    int _numShotcodes;
    String _Question1;
    String _Question1_min;
    String _Question1_max;

    // Empty constructor
    public Protocol(){
    }

    // constructor
    public Protocol(int id, String name, int numSubjects, int numShotcodes, String question1, String question1_min, String question1_max){
        this._id = id;
        this._name = name;
        this._numSubjects = numSubjects;
        this._numShotcodes =  numShotcodes;
        this._Question1 = question1;
        this._Question1_min = question1_min;
        this._Question1_max = question1_max;


    }
    // constructor
    public Protocol(String name, int numSubjects, int numShotcodes, String question1, String question1_min, String question1_max){
        this._name = name;
        this._numSubjects = numSubjects;
        this._numShotcodes = numShotcodes;
        this._Question1 = question1;
        this._Question1_min = question1_min;
        this._Question1_max = question1_max;
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
    public int getnumSubjects(){
        return this._numSubjects;
    }

    // setting numSubjects
    public void setnumSubjects(int numSubjects){
        this._numSubjects = numSubjects;
    }

    // getting numShotcodes
    public int getnumShotcodes(){
        return this._numShotcodes;
    }

    // setting numShotcodes
    public void setnumShotcodes(int numShotcodes){
        this._numShotcodes = numShotcodes;
    }

    public String toString(){
        return this._name;
    }

    // getting question1
    public String getquestion1(){
        return this._Question1;
    }

    // setting question1
    public void setquestion1(String question1){
        this._Question1 = question1;
    }

    // getting question1_min
    public String getquestion1_min(){
        return this._Question1_min;
    }

    // setting question1_min
    public void setquestion1_min(String question1_min){
        this._Question1_min = question1_min;
    }

    // getting question1_max
    public String getquestion1_max(){
        return this._Question1_max;
    }

    // setting question1_max
    public void setquestion1_max(String question1_max){
        this._Question1_max = question1_max;
    }
}
