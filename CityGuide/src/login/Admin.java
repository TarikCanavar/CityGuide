package login;

public class Admin extends Login{


    public Admin(String name, String surname, String mailAddress, String password) {
        super(name, surname, mailAddress, password);
    }

	public Admin(Object name, Object surname, Object mailAddress, Object password) {
		super((String) name, (String) surname, (String) mailAddress, (String) password);
	}


}
