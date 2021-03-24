
package SystemClasses;

import java.util.ArrayList;


public class Users {
    
    private  String useranme , password;
    private static ArrayList<Users>userlist = new ArrayList<>();

    public String getUseranme() {
        return useranme;
    }

    public String getPassword() {
        return password;
    }

    public static ArrayList<Users> getUserlist() {
        return userlist;
    }

    public void setUseranme(String useranme) {
        this.useranme = useranme;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
