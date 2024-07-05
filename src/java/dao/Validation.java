/*
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import java.sql.SQLException;

/**
 *
 * @author minhdq
 */
public class Validation extends UserDAO implements DBInfo {

    public static boolean checkUniqueUser(String username, String email) {
        new UserDAO();
        String query = """
                        SELECT [Username]
                              ,[Email]
                         FROM [dbo].[User] WHERE Username = ? OR Email = ?""";
        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return false;
            }
            con.close();

        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public static boolean checkIfExistClubMembership(String username, int clubId) {
        new UserDAO();
        String query = null;
        if (clubId != 0) {
             query = "SELECT COUNT(*) AS count FROM ClubMembership WHERE Username = ? AND ClubID = ?";
        } else {
             query = "SELECT COUNT(*) AS count FROM ClubMembership WHERE Username = ?";

        }

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, username);
            if (clubId != 0) preparedStatement.setInt(2, clubId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                if (count > 0) {
                    return true;
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    
    public static boolean isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9]*$";
        return username.matches(regex);
    }

    public static boolean isValidUsernameLength(String username) {
        if (username.length() < 4) {
            return false;
        }
        return true;
    }

    public static boolean isPasswordsMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public static boolean isFullNameEmpty(String fullName) {
        return fullName == null || fullName.trim().isEmpty();
    }

    public static boolean isValidPassword(String password) {
        if (password.contains(" ")) {
            return false;
        }

        return true;
    }

    public static boolean isValidPasswordLength(String password) {
        if (password.length() < 6) {
            return false;
        }

        return true;
    }

    //--------------------------------------------------------------------
    public static boolean checkUniqueClub(String clbName) {
        String query = """
                        SELECT [ClubName]
                         FROM [dbo].[Club] WHERE ClubName = ?""";
        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, clbName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return false;
            }
            con.close();

        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public static boolean isValidClubNameLength(String clubName) {
        if (clubName.length() < 3) {
            return false;
        }

        return true;
    }

}
