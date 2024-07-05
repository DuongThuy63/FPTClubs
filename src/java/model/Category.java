/*
 */
package model;

import dao.CategoryDAO;

/**
 *
 * @author minhdq
 */
public class Category {
    private int categoryId;
    private String categoryName;

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Category(int categoryId) {
        this.categoryName = CategoryDAO.getCategoryName(categoryId);
        this.categoryId = categoryId;
    }

    public Category(){
        
    }
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
  
}
