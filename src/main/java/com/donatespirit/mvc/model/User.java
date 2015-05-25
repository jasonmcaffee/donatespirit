package com.donatespirit.mvc.model;

//http://gerrydevstory.com/2013/06/29/spring-mvc-hibernate-mysql-quick-start-from-scratch/


import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


import javax.persistence.*;

//
//http://stackoverflow.com/questions/9958290/hibernate-simple-jointable-without-using-entity
@Entity
//@SecondaryTable(
//        name="UserRole",
//        pkJoinColumns = @PrimaryKeyJoinColumn(name="userId")
//)
//@Table(name = "users")
public class User implements Serializable {
    @Id @GeneratedValue private long id;
    private String userName;
    private String password;

    public User(){
        if(this.userStatus == null){
            this.userStatus = UserStatus.UNNAPPROVED;
        }
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Column(name="approved")
    @Enumerated(EnumType.ORDINAL)
    private UserStatus userStatus;

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
        if(userInfo == null){
            userInfo = new UserInfo();
        }
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    //@JoinColumn(name="id")
    @JoinTable(
            name="UserRole",
            joinColumns = @JoinColumn(name="userId"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<UserRole> userRoleList;

    @Transient
    public List<UserRoleType> getUserRoleTypes(){
        return userRoleList.stream().map(userRole -> userRole.getRole()).collect(Collectors.toList());
    }

    @Transient
    public boolean isAllowedToAssignUserRoles(){
        List<UserRoleType> roleTypes = getUserRoleTypes();
        return roleTypes.contains(UserRoleType.COLEADER) || roleTypes.contains(UserRoleType.LEADER);
    }
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
    //public boolean isApproved() {return approved;}
    //public void setApproved(boolean approved) {this.approved = approved;}


    @Transient
    public boolean isColeader(){
        return getUserRoleTypes().contains(UserRoleType.COLEADER);
    }

    @Transient
    public boolean isElder(){
        return getUserRoleTypes().contains(UserRoleType.ELDER);
    }

}

//    @Column(columnDefinition = "TINYINT")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
//private boolean approved;