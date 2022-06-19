package login;

public abstract class Login {
    private static int IDcounter = 0;
    private int ID;
    private String name;
    private String surname;
    private String mailAddress;
    private String password;

    public Login(String name, String surname, String mailAddress, String password) {
        this.name = name;
        this.surname = surname;
        this.mailAddress = mailAddress;
        this.password = password;
        this.ID = IDcounter;
        IDcounter++;
    }

    public String getSurname() {
        return surname;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    

    public static int getIDcounter() {
        return IDcounter;
    }
}
