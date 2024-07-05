/*
 */
package model;

/**
 *
 * @author minhdq
 * 
 * 
 */
public class Club extends Category{
    private int clubId ;
    private String clubName;
    private String clubDescription;
    private String clubLogo;
    private String clubEmail;

  
    
    public Club(int clubId, String clubName, String clubDescription, String clubEmail, String clubLogo, int categoryId) {
        super(categoryId);
        this.clubId = clubId;
        this.clubName = clubName;
        this.clubDescription = clubDescription;
        this.clubLogo = clubLogo;
        this.clubEmail = clubEmail;
    }
    
    
    public Club(String clubName, String clubDescription, String clubEmail, String clubLogo, int categoryId) {
        super(categoryId);
        this.clubName = clubName;
        this.clubDescription = clubDescription;
        this.clubLogo = clubLogo;
        this.clubEmail = clubEmail;
    }
    

    public Club() {
        super();
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubDescription() {
        return clubDescription;
    }

    public void setClubDescription(String clubDescription) {
        this.clubDescription = clubDescription;
    }

    public String getClubLogo() {
        return clubLogo;
    }

    public void setClubLogo(String clubLogo) {
        this.clubLogo = clubLogo;
    }

    public String getClubEmail() {
        return clubEmail;
    }

    public void setClubEmail(String clubEmail) {
        this.clubEmail = clubEmail;
    }

 
    

    
}
