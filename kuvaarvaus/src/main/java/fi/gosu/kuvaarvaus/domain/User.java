package fi.gosu.kuvaarvaus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(columnDefinition = "VARCHAR(250) COLLATE latin1_general_ci", unique = true)
    private String username;
    private String password;
    @JsonIgnore
    private String salt;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "Image_User", unique = false)
    private List<Image> images;

    public User() {
        this.images = new ArrayList<>();
    }

    public Long getId() {
        return userId;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, this.salt);
    }


    public List<Image> getImages() {
        images.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
        return images;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.userId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.userId, other.userId);
    }

}
