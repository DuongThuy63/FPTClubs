/*
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author minhdq
 */
public class ClubDAO implements DBInfo {

    public ClubDAO() {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Club> getAll() {
        return executeGetByQuery("""
                                 SELECT * FROM [dbo].[Club]""", null);
    }

    public static ArrayList<Club> getById(int id) {
        return executeGetByQuery("""
                                 SELECT * FROM [dbo].[Club] WHERE ClubId = ?""", Integer.toString(id));
    }

    public static ArrayList<Club> executeGetByQuery(String query, String parameter) {
        new ClubDAO();
        ArrayList<Club> resultList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                if (parameter != null) {
                    pstmt.setString(1, parameter);
                }
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String description = rs.getString(3);
                    String email = rs.getString(4);
                    String logo = rs.getString(5);
                    int category = rs.getInt(6);

                    Club clb = new Club(id, name, description, email, logo, category);
                    resultList.add(clb);
                }
            }
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (resultList.size() > 0) {
            return resultList;
        } else {
            return null;
        }
    }

    public static Club createClub(Club clb) {
        new ClubDAO();
        String query = """
                       INSERT INTO [dbo].[Club]
                                  ([ClubName]
                                  ,[Description]
                                  ,[ClubEmail]
                                  ,[Logo]
                                  ,[CategoryID])
                            output inserted.ClubId VALUES (?,?,?,?,?)""";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement stmt = con.prepareStatement(query);
            
            if (clb.getClubLogo() == null){
                clb.setClubLogo("https://scontent.fdad3-1.fna.fbcdn.net/v/t39.30808-6/423554706_772067381618923_3608000234596588066_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeFwMufshIV2PN57qo-gFwWqPV41cCY8Hf09XjVwJjwd_Y4JqFWfmMsVzrLKJQ7cJX1uNxil_VtO9Q9VXjGI2lQl&_nc_ohc=s6hljSkDmRAAX8X70tN&_nc_ht=scontent.fdad3-1.fna&oh=00_AfCoCyUpm2vmj1j9EBR160b0OD-s_w5ZKYLZRQcduvLFKw&oe=65FFB4B8");
            }
            stmt.setString(1, clb.getClubName());
            stmt.setString(2, clb.getClubDescription());
            stmt.setString(3, clb.getClubEmail());
            stmt.setString(4, clb.getClubLogo());
            stmt.setInt(5, clb.getCategoryId());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                clb.setClubId(rs.getInt(1));
            } else {
                throw new RuntimeException("Create failed..");
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        return clb;
    }

    public static void addAsAmin(int clubId, String username) {
        new ClubDAO();
        String query = """
                       INSERT INTO [dbo].[ClubMembership]
                                       ([ClubID]
                                       ,[Username]
                                       ,[RoleID])
                                 VALUES (?, ?, ?)""";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, clubId);
            stmt.setString(2, username);
            stmt.setInt(3, 1);
            stmt.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static ArrayList<Integer> getClubIdsFromUser(User user) {
        String query = "SELECT [ClubID] FROM [dbo].[ClubMembership] WHERE Username = ?";
        ArrayList<Integer> clubIds = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, user.getUsername());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int clubId = rs.getInt("ClubID");
                clubIds.add(clubId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }

        return clubIds;
    }

    public static void addMembersToClub(int clubId, String[] selectedUsers) {
        new ClubDAO();

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            String query = """
                           INSERT INTO [dbo].[ClubMembership]
                                      ([ClubID]
                                      ,[Username]
                                      ,[RoleID])
                                VALUES (?, ?, ?)""";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                for (String username : selectedUsers) {
                    pstmt.setInt(1, clubId);
                    pstmt.setString(2, username);
                    pstmt.setInt(3, 4); //role: member
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //------------------------------------------------------
    public static int getClubIdFromAdminClub(User user) {
        new ClubDAO();
        String query = """
                      SELECT [ClubID]
                              FROM [dbo].[ClubMembership]
                              WHERE Username = ? AND (RoleID = 1 OR RoleID = 2);""";

        ArrayList<Club> list = new ArrayList<>();
        int clubId = 0;
        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, user.getUsername());
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    clubId = rs.getInt(1);
                }
                list = getById(clubId);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        return list.get(0).getClubId();
    }

    public static boolean deleteClub(int clubId) {
        boolean deleted = false;
        String[] queries = {
            "DELETE FROM Notification WHERE ClubID = ?",
            "DELETE FROM ClubCreationRequest WHERE ClubID = ?",
            "DELETE FROM ClubMembership WHERE ClubID = ?",
            "DELETE FROM Event WHERE ClubID = ?",
            "DELETE FROM Club WHERE ClubID = ?"
        };

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            int rowsAffected = 0;
            for (String query : queries) {
                try (PreparedStatement pstmt = con.prepareStatement(query)) {
                    pstmt.setInt(1, clubId);
                    rowsAffected = pstmt.executeUpdate();
                }
            }
            deleted = rowsAffected > 0;
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

    // Cập nhật thông tin của club
    public static boolean updateClub(Club club) {
        boolean updated = false;
        String query = """
                       UPDATE [dbo].[Club]
                          SET [ClubName] = ?
                             ,[Description] = ?
                             ,[ClubEmail] = ?
                             ,[Logo] = ?
                             ,[CategoryID] = ?
                        WHERE ClubID = ?""";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, club.getClubName());
            pstmt.setString(2, club.getClubDescription());
            pstmt.setString(3, club.getClubEmail());
            pstmt.setString(4, club.getClubLogo());
            pstmt.setInt(5, club.getCategoryId());
            pstmt.setInt(6, club.getClubId());

            int rowsAffected = pstmt.executeUpdate();
            updated = rowsAffected > 0;
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }
    //------------------------------------------------------

    public static ArrayList<Club> listClub(int categoryID) {
        new ClubDAO();
        String query = "Select * from Club where CategoryID = ?";
        ArrayList<Club> cList = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, categoryID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Club c = new Club();
                c.setClubId(rs.getInt("ClubID"));
                c.setClubName(rs.getString("ClubName"));
                c.setClubDescription(rs.getString("Description"));
                c.setClubLogo(rs.getString("Logo"));
                c.setCategoryId(rs.getInt("CategoryID"));
                cList.add(c);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        return cList;
    }

    //--------------------------------
}
