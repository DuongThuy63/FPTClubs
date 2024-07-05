/*
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import static dao.DBInfo.dbPass;
import static dao.DBInfo.dbUrl;
import static dao.DBInfo.dbUser;

/**
 *
 * @author minhdq
 */
public class CategoryDAO implements DBInfo {

    public CategoryDAO() {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static ArrayList<Category> getCategory() {
        new Category();
        ArrayList<Category> catList = new ArrayList<>();
        try ( Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            String query = "SELECT CategoryName from Category";
            PreparedStatement ptm = null;
            ResultSet rs = null;
            ptm = con.prepareStatement(query);
            rs = ptm.executeQuery();
            
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryName(rs.getString("CategoryName"));
                catList.add(c);
            }
        } catch (Exception e) {

        }
        return catList;
    }
    
    public static String getCategoryName(int id) {
        new ClubDAO();
        String query = """
                       SELECT  
                          [CategoryName]
                           FROM [dbo].[Category] WHERE CategoryID = ?""";
        String name = "";
        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, Integer.toString(id));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                name = rs.getString(1);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        return name;
    }
}
