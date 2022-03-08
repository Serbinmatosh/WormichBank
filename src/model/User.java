/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
 

/**
 *
 * @author serbinmatosh
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private String sex;
    private String authorization;
    private byte[] photo;

    

    public User() {
    }

    public User(String username, String password, String authorization) {
        this.username = username;
        this.password = password;
        this.authorization = authorization;
    }

    public User(int id, String username, String password, String firstName, String lastName, String email, Date dob, String sex, String authorization, byte[] photo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.sex = sex;
        this.authorization = authorization;
        this.photo = photo;
    }
    
    public User(String username, String password, String firstName, String lastName, String email, Date dob, String sex, String authorization, byte[] photo) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.sex = sex;
        this.authorization = authorization;
        this.photo = photo;
    }

    public int getId() {
        return id;
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
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        Date date = dob;
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String DOB = df.format(date);
        return DOB;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof User))
            return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username +
                ", password=" + password + ", firstName=" + firstName +
                ", lastName=" + lastName + ", email=" + email + ", dob=" +
                dob + ", sex=" + sex + ", authorization=" + 
                authorization + ", photo=" + photo + '}';
    }
    
    public User resultSetUser(ResultSet rs) {
        try {
            return new User(rs.getInt("id"), rs.getString("username"),
                    rs.getString("password"), rs.getString("firstName"),
                    rs.getString("lastName"), rs.getString("email"),
                    rs.getDate("DOB"), rs.getString("sex"), Integer.toString(rs.getInt("emp_authorization")), rs.getBytes("photo"));
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    

    

    
    
}
