/*
 */
package model;

import dao.UserDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author minhdq
 */
public class User {
    private String username;
    private String password;
    private String fullName;
    private char gender;
    private String email;
    private String phone;
    private Date dob;
    private String avatar;
    

    public User(String username, String password, String fullName, char gender, String email, String phone, Date dob, String avatar) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.avatar = avatar;
    }

    public User(String username, String password, String fullName, String gender, String email, String dob) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender.charAt(0);
        this.email = email;
        setDob(dob);
//        this.avatar = avatar;
    }
    public User(String username, String email){
        this.username = username;
        this.email = email;

    }
    public User(){
        
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        switch(gender){
            case 'M':return "Male"; 
            case 'F':return "Female"; 
            case 'O':return "Others";
            default: return "NA";
        }
    }

    public void setGender(String gender) {
        this.gender = gender.charAt(0);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dob);
    }

    public void setDob(String dob) {
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.dob = sdf.parse(dob);
        } catch (ParseException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

 

    public static User login(String username, String inputPass) {
        User us = UserDAO.getByUsername(username).get(0);
//        if (us == null) throw new RuntimeException("No Username found...");
        if(us.password.equals(inputPass)) return us;
        
        return null;
    }

    @Override
    public String toString() {
        return "User: " + username + " - "+ fullName;
    }

    
    
}
