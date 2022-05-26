package by.liashuk.exercisesProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Schema(description = "Entity of user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login")
    @Schema(description = "Login of user", example = "asd")
    private String login;

    @Column(name = "password")
    @Schema(description = "Password of user", example = "asd")
    private String password;

    @ManyToMany(mappedBy = "userList",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Exercise> runsList;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id.equals(users.id) && Objects.equals(login, users.login) && Objects.equals(password, users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    public Users(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
}
