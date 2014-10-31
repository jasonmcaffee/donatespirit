package com.donatespirit.mvc.model;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//
//
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue private long id;
    private String userName;
    private double password;

//    @OneToMany(mappedBy = "pizza", fetch = FetchType.LAZY)
//    private List<Topping> toppings;

    /**********************************************************************/

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String name) {
        this.userName = name;
    }
    public double getPassword() {
        return password;
    }
    public void setPassword(double password) {
        this.password = password;
    }


}