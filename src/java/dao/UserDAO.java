/*
 */
package dao;

import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author minhdq
 */
public class UserDAO implements DBInfo {

    public UserDAO() {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<User> getByUsername(String username) {
        return executeGetByQuery("""
                                 SELECT [Username]
                                       ,[Password]
                                       ,[FullName]
                                       ,[Gender]
                                       ,[Email]
                                       ,[Phone]
                                       ,[Dob]
                                       ,[Avatar]
                                   FROM [dbo].[User] WHERE Username = ?""", username);
    }

    public static ArrayList<User> searchByUsername(String partialUsername) {
    return executeGetByQuery("""
                             SELECT [Username]
                                   ,[Password]
                                   ,[FullName]
                                   ,[Gender]
                                   ,[Email]
                                   ,[Phone]
                                   ,[Dob]
                                   ,[Avatar]
                               FROM [dbo].[User] WHERE Username LIKE ?""", "%" + partialUsername + "%");
}
      public static ArrayList<User> getAllAccount() {
          return executeGetByQuery("select * from [User]", null);
      }
      
      

    public static ArrayList<User> executeGetByQuery(String query, String parameter) {
        new UserDAO();
        ArrayList<User> resultList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                if (parameter != null) {
                    pstmt.setString(1, parameter);
                }
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    String usn = rs.getString(1);
                    String password = rs.getString(2);
                    String fullName = rs.getString(3);
                    char gender = rs.getString(4).charAt(0);
                    String email = rs.getString(5);
                    String phone = rs.getString(6);
                    Date dob = rs.getDate(7);
                    String avatar = rs.getString(8);

                    User us = new User(usn, password, fullName, gender, email, phone, dob, avatar);
                    resultList.add(us);
                }
            }
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultList;
    }

    public static User register(User us) {
        new UserDAO();
        String query = """
                       INSERT INTO [dbo].[User]
                                  ([Username]
                                  ,[Password]
                                  ,[FullName]
                                  ,[Gender]
                                  ,[Email]
                                  ,[Phone]
                                  ,[DOB]
                                  ,[Avatar])
                                  output inserted.Username values (?, ?, ?, ?, ?, ?, ?, ?)""";
        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, us.getUsername());
            stmt.setString(2, us.getPassword());
            stmt.setString(3, us.getFullName());
            stmt.setString(4, "" + us.getGender().charAt(0));
            stmt.setString(5, us.getEmail());
            stmt.setString(6, us.getPhone());
            stmt.setString(7, us.getDob());
            stmt.setString(8, us.getAvatar());

            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                us.setId(rs.getInt(1));
//            } else {
//                throw new RuntimeException("Register failed..");
//            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        return us;
    }

    //-------------------------------------
    public static ArrayList<User> getAllMembersByClubId(int clubId) {
          new UserDAO();
        ArrayList<User> members = new ArrayList<>();
        String query = "SELECT u.* FROM [User] u JOIN ClubMembership cm ON u.Username = cm.Username WHERE cm.ClubID = ?";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, clubId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String usn = rs.getString(1);
                String password = rs.getString(2);
                String fullName = rs.getString(3);
                char gender = rs.getString(4).charAt(0);
                String email = rs.getString(5);
                String phone = rs.getString(6);
                Date dob = rs.getDate(7);
                String avatar = rs.getString(8);

                User member = new User(usn, password, fullName, gender, email, phone, dob, avatar);
                members.add(member);
            }
        } catch (SQLException ex) {
            // Handle SQL exception
            ex.printStackTrace();
        }

        return members;
    }
    
    //------------------------------------------
    
    public static boolean updateUserProfile(User userProfile) {
         new UserDAO();
        String query = """
                       UPDATE [dbo].[User]
                          SET                            
                             [Fullname] = ?
                             ,[Password] = ?
                             ,[Gender] = ?
                             ,[Email] = ?
                             ,[Phone] = ?
                             ,[DOB] = ?
                             ,[Avatar] = ?
                        WHERE Username = ?""";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, userProfile.getFullName());
            statement.setString(2, userProfile.getPassword());
            statement.setString(3, "" + userProfile.getGender().charAt(0));
            statement.setString(4, userProfile.getEmail());
            statement.setString(5, userProfile.getPhone());
            statement.setString(6, userProfile.getDob());
            statement.setString(7, userProfile.getAvatar());
            statement.setString(8, userProfile.getUsername());
            statement.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteUserProfile(String username) {
        String query = "DELETE FROM User WHERE username = ?";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, username);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    
    public static boolean deleteProfileInfo(String username, String field) {
        String query = "UPDATE User SET " + field + " = NULL WHERE username = ?";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, username);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static User getUserProfile(String username) {
        String query = "SELECT * FROM User WHERE username = ?";
        User userProfile = null;

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                userProfile.setUsername(resultSet.getString("username"));
                userProfile.setFullName(resultSet.getString("fullName"));
                userProfile.setGender(resultSet.getString("gender"));
                userProfile.setEmail(resultSet.getString("email"));
                userProfile.setPhone(resultSet.getString("phone"));
                userProfile.setDob(resultSet.getString("dob"));
                userProfile.setAvatar(resultSet.getString("avatar"));
  
            }
        } catch (SQLException e) {
              e.printStackTrace();
        }

        return userProfile;
    }

}
