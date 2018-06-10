package entities;

/**
 * Created by Rustem.
 */
public class AppUser {
    private int id;
    private String login;
    private String hashedPassword;
    private String name;
    private String email;
    private int deposit;

    private AppRole appRole;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public AppRole getAppRole() {
        return appRole;
    }

    public void setAppRole(AppRole appRole) {
        this.appRole = appRole;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", deposit=" + deposit +
                ", appRole=" + appRole +
                '}';
    }
}
