package com.shifterandworker.Models;

import java.util.Date;

public class UserDetails {

    String FirstName, LastName, Phone, Email, Password, About, ImageUrl;
    Date LastLogin, CreateDate, DateOfBirth;
    int UserId, RoleId, StoreId, DepartmentId, SupervisorId;
    boolean IsActive;

    public UserDetails(String mEmail, String mPass) {

    }

    public UserDetails(String firstName, String lastName, String phone, String email, String password, String about, String imageUrl, Date lastLogin, Date createDate, Date dateOfBirth, int userId, int roleId, int storeId, int departmentId, int supervisorId, boolean isActive) {
        FirstName = firstName;
        LastName = lastName;
        Phone = phone;
        Email = email;
        Password = password;
        About = about;
        ImageUrl = imageUrl;
        LastLogin = lastLogin;
        CreateDate = createDate;
        DateOfBirth = dateOfBirth;
        UserId = userId;
        RoleId = roleId;
        StoreId = storeId;
        DepartmentId = departmentId;
        SupervisorId = supervisorId;
        IsActive = isActive;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public Date getLastLogin() {
        return LastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        LastLogin = lastLogin;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public int getStoreId() {
        return StoreId;
    }

    public void setStoreId(int storeId) {
        StoreId = storeId;
    }

    public int getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(int departmentId) {
        DepartmentId = departmentId;
    }

    public int getSupervisorId() {
        return SupervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        SupervisorId = supervisorId;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }
}