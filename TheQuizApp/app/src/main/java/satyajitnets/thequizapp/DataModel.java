package satyajitnets.thequizapp;


public class DataModel {

    String Ques;
    String answer;

    String enter;

    public DataModel(String Ques, String answer, String enter ) {
        this.Ques=Ques;
        this.answer=answer;

        this.enter=enter;

    }

    public String getQ() {
        return Ques;
    }



    public String getAns() {
        return answer;
    }

    public String getEntrd() {
        return enter;
    }

}
