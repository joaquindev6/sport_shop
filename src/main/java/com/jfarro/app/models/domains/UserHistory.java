package com.jfarro.app.models.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class UserHistory {

    @Column(name = "estado")
    private Byte state;

    @Column(name = "user_reg")
    private String userReg;

    @Column(name = "fecha_reg")
    private LocalDate dateReg;

    @Column(name = "user_mod")
    private String userMod;

    @Column(name = "fecha_mod")
    private LocalDate dateMod;

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
