package rhdr.afrl.trialsurvey;

public class ProtoQuestions {

    //private variables
    int _id;
    int _Protocol_id;
    String _Question;
    String _Question_min;
    String _Question_max;

    // Empty constructor
    public ProtoQuestions(){
    }

    // constructor
    public ProtoQuestions(int id, int protocol_id, String question, String question_min, String question_max){
        this._id = id;
        this._Protocol_id = protocol_id;
        this._Question = question;
        this._Question_min = question_min;
        this._Question_max = question_max;
    }

    // constructor
    public ProtoQuestions(int protocol_id, String question, String question_min, String question_max){
        this._Protocol_id = protocol_id;
        this._Question = question;
        this._Question_min = question_min;
        this._Question_max = question_max;
    }

    // getting question
    public String getquestion(){
        return this._Question;
    }

    // setting question
    public void setquestion(String question){
        this._Question = question;
    }

    // getting question_min
    public String getquestion_min(){
        return this._Question_min;
    }

    // setting question_min
    public void setquestion_min(String question_min){
        this._Question_min = question_min;
    }

    // getting question_max
    public String getquestion_max(){
        return this._Question_max;
    }

    // setting question_max
    public void setquestion_max(String question_max){
        this._Question_max = question_max;
    }
}
