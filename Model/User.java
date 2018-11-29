package chenjundongted.com.finalproject.Model;

public class User {
    private String password;
    private String email;
    private String name;

    public User() {
    }

    public User(String setEmail, String setName, String setPassword) {
        email = setEmail;
        name = setName;
        password = setPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }
}
