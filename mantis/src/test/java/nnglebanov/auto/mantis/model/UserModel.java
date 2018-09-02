package nnglebanov.auto.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")
public class UserModel {
    @Id
    @Column(name = "id")
    private int id;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRealname() {
        return realname;
    }

    public String getEmail() {
        return email;
    }

    @Override

    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id &&
                Objects.equals(username, userModel.username) &&
                Objects.equals(realname, userModel.realname) &&
                Objects.equals(email, userModel.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, realname, email);
    }

    @Column(name = "username")

    private String username;
    @Column(name = "realname")
    private String realname;
    @Column(name = "email")
    private String email;
}
