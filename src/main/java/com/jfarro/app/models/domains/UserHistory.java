package com.jfarro.app.models.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Embeddable
public class UserHistory {

    @Column(name = "estado")
    @NotNull
    private Byte state;

    @Column(name = "user_reg")
    @NotBlank
    private String userReg;

    @Column(name = "fecha_reg")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateReg;

    @Column(name = "user_mod")
    @NotBlank
    private String userMod;

    @Column(name = "fecha_mod")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateMod;

    @PrePersist
    public void prePersist() {
        this.state = 1;
        this.dateReg = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dateMod = LocalDate.now();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getUserReg() {
        return userReg;
    }

    public void setUserReg(String userReg) {
        this.userReg = userReg;
    }

    public LocalDate getDateReg() {
        return dateReg;
    }

    public void setDateReg(LocalDate dateReg) {
        this.dateReg = dateReg;
    }

    public String getUserMod() {
        return userMod;
    }

    public void setUserMod(String userMod) {
        this.userMod = userMod;
    }

    public LocalDate getDateMod() {
        return dateMod;
    }

    public void setDateMod(LocalDate dateMod) {
        this.dateMod = dateMod;
    }
}
