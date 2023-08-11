package business;

public class FailedLogin {
    String datetime;
    String email;
    public FailedLogin(String datetime, String email){
        this.datetime = datetime;
        this.email = email;
    }

    public String getDatetime(){
        return datetime;
    }

    public String getEmail(){
        return email;
    }
}
