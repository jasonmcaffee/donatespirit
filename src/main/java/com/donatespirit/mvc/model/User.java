package com.donatespirit.mvc.model;

//http://gerrydevstory.com/2013/06/29/spring-mvc-hibernate-mysql-quick-start-from-scratch/


import java.io.Serializable;
import java.util.List;


import javax.persistence.*;

import com.donatespirit.mvc.model.UserInfo;
//
//
@Entity
//@Table(name = "users")
public class User implements Serializable {
    @Id @GeneratedValue private long id;
    private String userName;
    private String password;

    @Transient
    public boolean isSignedIn() {
        return isSignedIn;
    }

    @Transient
    public void setSignedIn(boolean signedIn) {
        isSignedIn = signedIn;
    }

    @Transient
    private boolean isSignedIn;

    @OneToOne
    @JoinColumn(name="id")
    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}