package login;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Pattern;
public class User extends Login {

    public User(String name, String surname, String mailAddress, String password) {
        super(name, surname, mailAddress, password);
    }
    public User(Object name, Object surname, Object mailAddress, Object password) {
		super((String) name, (String) surname, (String) mailAddress, (String) password);
	}

    public static boolean createUser(String name, String surname, String mailAddress, String password){
        if(Pattern.matches("^[a-zA-Z\\s]*$", name) && Pattern.matches("^[a-zA-Z\\s]*$", surname) && Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", mailAddress)){
            File users = new File("users.json");
            if(users.exists()){
                //TODO validation

                JSONParser parser = new JSONParser();
                try {

                    JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("users.json"));
                    JSONArray userList = (JSONArray) jsonObject.get("users");
                    
                    JSONObject userAttributes = new JSONObject();
                    userAttributes.put("id", Login.getIDcounter());
                    userAttributes.put("name", name);
                    userAttributes.put("surname", surname);
                    userAttributes.put("email", mailAddress);
                    userAttributes.put("password", password);
                    
                    
                    
                    userList.add(userAttributes);
                    JSONObject newRecords = new JSONObject();
                    newRecords.put("users", userList);
                    try (FileWriter file = new FileWriter("users.json")) {
                        file.write(newRecords.toString());
                        file.flush();

                    } catch(Exception e1){
                        System.out.println(e1);
                    }

                } catch (Exception e1) {

                    e1.printStackTrace();
                }
            }else{
                //TODO validation
                JSONArray jsonArray = new JSONArray();

                JSONObject userAttributes = new JSONObject();
                userAttributes.put("surname", surname);
                userAttributes.put("name", name);
                userAttributes.put("id", Login.getIDcounter());
                userAttributes.put("email", mailAddress);
                userAttributes.put("password", password);


                JSONObject userAttributes2 = new JSONObject();
                userAttributes2.put("users", jsonArray);


                jsonArray.add(userAttributes);


                try (FileWriter file = new FileWriter("users.json")) {
                    file.write(userAttributes2.toString());
                    file.flush();

                } catch(Exception e1){
                    System.out.println(e1);

                }

            }
            
            return true;
        }
        else {
        	
        	return false;
        }

    }
}