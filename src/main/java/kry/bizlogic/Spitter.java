package kry.bizlogic;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by polansky on 7.12.2017.
 */
public class Spitter {

    private long Id;

    @NotNull
    private String login;
    private String password;

    @NotNull
    private String firstName;

    @Size(min=3, max=20)
    private String lastName;

    public Spitter() {
    }

    public Spitter(long Id, String login, String password, String firstName, String lastName) {
        this.Id = Id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Spitter(String login, String password, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Spitter{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Spitter spitter = (Spitter) o;

        if (getId() != spitter.getId()) return false;
        if (getLogin() != null ? !getLogin().equals(spitter.getLogin()) : spitter.getLogin() != null) return false;
        if (getPassword() != null ? !getPassword().equals(spitter.getPassword()) : spitter.getPassword() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(spitter.getFirstName()) : spitter.getFirstName() != null)
            return false;
        return getLastName() != null ? getLastName().equals(spitter.getLastName()) : spitter.getLastName() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        return result;
    }
}
