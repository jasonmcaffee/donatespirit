package com.donatespirit.mvc.model;

//http://gerrydevstory.com/2013/06/29/spring-mvc-hibernate-mysql-quick-start-from-scratch/


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
//@Table(name = "users")
public class User {
    @Id @GeneratedValue private long id;
    private String userName;
    private String password;

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