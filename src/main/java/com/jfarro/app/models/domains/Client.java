package com.jfarro.app.models.domains;

import com.jfarro.app.validators.constraints.LastNamesRegex;
import com.jfarro.app.validators.constraints.NamesRegex;
import com.jfarro.app.validators.constraints.PasswordRegex;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_clientes")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres")
    @NotBlank
    @Size(max = 45)
    @NamesRegex
    private String names;

    @Column(name = "ape_paterno")
    @NotBlank
    @Size(max = 20)
    @LastNamesRegex
    private String apePat;

    @Column(name = "ape_materno")
    @NotBlank
    @Size(max = 20)
    @LastNamesRegex
    private String apeMat;

    @Column(name = "email")
    @NotBlank
    private String email;

    @Column(name = "password")
    @NotBlank
    @PasswordRegex
    private String password;

    @Column(name = "observacion")
    private String observation;

    @Embedded
    @NotNull
    private UserHistory userHistory;

    public Client() {
        this.userHistory = new UserHistory();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getApePat() {
        return apePat;
    }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public UserHistory getUserHistory() {
        return userHistory;
    }

    public void setUserHistory(UserHistory userHistory) {
        this.userHistory = userHistory;
    }
}
