package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

//GrantedAuthority определяет класс роли в системе, единственный реализованный метод
//должен возвращать String названия роли в виде «ROLE_ИМЯРОЛИ»
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, name = "roleName")
    private String role;


    public Role() {

    }

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
