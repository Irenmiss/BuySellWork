package ru.skypro.homework.entity;

import lombok.Data;
import ru.skypro.homework.Enums.Role;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "image")
    private String image;


    public Integer getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }
}

