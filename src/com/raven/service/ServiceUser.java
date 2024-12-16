package com.raven.service;

import com.raven.connection.DatabaseConnection;
import com.raven.model.ModelLogin;
import com.raven.model.ModelUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ServiceUser {

    private final Connection con;

    public ServiceUser() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public ModelUser login(ModelLogin login) throws SQLException {
        ModelUser userData = null;
        PreparedStatement p = con.prepareStatement("select UserID, UserName, Email, rol, Profile from `user` where BINARY(Email)=? and BINARY(`Password`)=? and `Status`='Verified' limit 1");
        p.setString(1, login.getEmail());
        p.setString(2, login.getPassword());
        ResultSet r = p.executeQuery();
        if (r.next()) {
            int userID = r.getInt(1);
            String userName = r.getString(2);
            String email = r.getString(3);
            String rol = r.getString(4);

            byte[] profileBytes = r.getBytes(5);
            //byte[] profileBytes = r.getBytes("Profile");
            Icon profile = null;

            if (profileBytes != null && profileBytes.length > 0) {
                profile = new ImageIcon(profileBytes);
            } else {
                // Fallback to a default image if no profile image is available
                profile = new ImageIcon(getClass().getResource("/com/raven/icon/user_1.png"));
            }
            //profile = new ImageIcon(r.getBytes("Profile"));
            userData = new ModelUser(userName, userID, email, rol, profile);
        }
        r.close();
        p.close();
        return userData;
    }

    public void insertUser(ModelUser user) throws SQLException {
        // Prepare the statement with RETURN_GENERATED_KEYS to fetch the generated key (userID)
        String query = "INSERT INTO `user` (UserName, Email, `Password`, VerifyCode) VALUES (?, ?, ?, ?)";
        PreparedStatement p = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

        String code = generateVerifyCode();
        System.out.println(code);
        p.setString(1, user.getUserName());
        p.setString(2, user.getEmail());
        p.setString(3, user.getPassword());
        p.setString(4, "100100");

        // Execute the insert query
        p.execute();  // Use executeUpdate() to perform the insert operation

        // Retrieve the generated keys (userID)
        ResultSet r = p.getGeneratedKeys();  // This retrieves the generated keys

        // Check if the result set contains at least one row (generated key)
        if (r.next()) {  // Move cursor to the first row
            //System.out.println(r.next());
            int userID = r.getInt(1);  // The first generated key is the userID
            user.setUserID(userID);    // Set the userID to the user object
        } else {
            // Handle case where no generated keys were returned
            System.out.println("No generated keys returned.");
        }

        // Close the ResultSet and PreparedStatement
        r.close();
        p.close();

        // Set the verification code
        user.setVerifyCode(code);
    }

    private String generateVerifyCode() throws SQLException {
        DecimalFormat df = new DecimalFormat("000000");
        Random ran = new Random();
        String code = df.format(ran.nextInt(1000000));  //  Random from 0 to 999999
        while (checkDuplicateCode(code)) {
            code = df.format(ran.nextInt(1000000));
        }
        return code;
    }

    private boolean checkDuplicateCode(String code) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select UserID from `user` where VerifyCode=? limit 1");
        p.setString(1, code);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public boolean checkDuplicateUser(String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select UserID from `user` where UserName=? and `Status`='Verified' limit 1");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public boolean checkDuplicateEmail(String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select UserID from `user` where Email=? and `Status`='Verified' limit 1");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public void doneVerify(int userID) throws SQLException {
        PreparedStatement p = con.prepareStatement("update `user` set VerifyCode='', `Status`='Verified' where UserID=? limit 1");
        p.setInt(1, userID);
        p.execute();
        p.close();
    }

    public boolean verifyCodeWithUser(int userID, String code) throws SQLException {
        boolean verify = false;
        PreparedStatement p = con.prepareStatement("select UserID from `user` where UserID=? and VerifyCode=? limit 1");
        p.setInt(1, userID);
        p.setString(2, code);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            verify = true;
        }
        r.close();
        p.close();
        return verify;
    }
}
