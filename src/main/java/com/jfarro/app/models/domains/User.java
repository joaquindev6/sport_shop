package com.jfarro.app.models.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jfarro.app.utils.PasswordEncoderByCrypt;
import com.jfarro.app.validators.constraints.LastNamesRegex;
import com.jfarro.app.validators.constraints.NamesRegex;
import com.jfarro.app.validators.constraints.NroDocumentRegex;
import com.jfarro.app.validators.constraints.PasswordRegex;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_usuarios")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_documento")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //Omitimos el cache, por eso aveces da error de serializable
    @NotNull
    private DocumentType documentType;

    @Column(name = "nro_documento")
    @NotBlank
    @NroDocumentRegex
    private String nroDocu;

    @Column(name = "email")
    @NotBlank
    private String email;

    @Column(name = "username")
    @NotBlank
    private String username;

    @Column(name = "password")
    @NotBlank
    private String password;

    @Column(name = "observacion")
    private String observation;

    @Column(name = "foto")
    private String photo;

    @Embedded
    @NotNull
    private UserHistory userHistory;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tbl_usuarios_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = {@JoinColumn(name = "id_rol")},
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"id_usuario", "id_rol"})
            })
    @NotEmpty
    private List<Role> roles;

    public User() {
        this.roles = new ArrayList<>();
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

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getNroDocu() {
        return nroDocu;
    }

    public void setNroDocu(String nroDocu) {
        this.nroDocu = nroDocu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PasswordEncoderByCrypt.encoderByCryp(password);
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", names='" + names + '\'' +
                ", apePat='" + apePat + '\'' +
                ", apeMat='" + apeMat + '\'' +
                ", documentType=" + documentType +
                ", nroDocu='" + nroDocu + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", observation='" + observation + '\'' +
                ", photo='" + photo + '\'' +
                ", userHistory=" + userHistory +
                ", roles=" + roles +
                '}';
    }
}
