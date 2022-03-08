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
public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private String street;
    private String city;
    private String zip;
    private String sex;
    private byte[] clientPhoto;
    private Account account;

    
    public Client(){}

   

    public Client(int id, String firstName, String lastName,
                    String email, Date dob, String street, String city,
                    String zip, String sex, byte[] clientPhoto, Account account) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.sex = sex;
        this.clientPhoto = clientPhoto;
        this.account = account;
    }
    
    public int getId() {
        return id;
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


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    
    public byte[] getClientPhoto() {
        return clientPhoto;
    }

    public void setClientPhoto(byte[] clientPhoto) {
        this.clientPhoto = clientPhoto;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", firstName=" + firstName + 
                ", lastName=" + lastName + ", email=" + email + ", dob=" + dob +
                ", street=" + street + ", city=" + city + ", zip=" + zip + 
                ", sex=" + sex + ", clientPhoto=" + clientPhoto + ", account=" + account + '}';
    }

    
    public Client resultSetClient(ResultSet rs, Account account) {
        try {
            return new Client(rs.getInt("id"), rs.getString("firstName"),
                    rs.getString("lastName"), rs.getString("email"),
                    rs.getDate("DOB"), rs.getString("street"), rs.getString("city"),
                    rs.getString("zip"), rs.getString("sex"), rs.getBytes("photo"), account);
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

    
    
    
    
    
}
