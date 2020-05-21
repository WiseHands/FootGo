package ua.lviv.footgo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String imageUrl;
    private Integer number;

    private Boolean isActive;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Team team;

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

    public Long getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String formatNumber() {
        if(this.number == null) {
            return "";
        }
        return  "" + this.number;
    }

    public boolean isTwoDigits() {
        if(this.number == null) return false;
        return this.number > 9;
    }

    public String getPlayerFirstName() {
        if(this.firstName != null) {
            return  this.firstName;
        }
        return "";
    }

    public String getPlayerLastName() {
        if(this.lastName != null) {
            return  this.lastName;
        }
        return "";
    }

    public static String transliterate(String message){
        char[] abcCyr =   {' ','а','б','в','г','д','е','є','ж','з','и','і','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ь','ю','я','А','Б','В','Г','Д','Е','Є','Ж','З','И','І','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х','Ц','Ч','Ш','Щ','Ь','Ю','Я','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String[] abcLat = {" ","a","b","v","g","d","e","ye","zh","z","y","i","j","k","l","m","n","o","p","r","s","t","u","f","h","ts","ch","sh","sch","","ju","ja","A","B","V","G","D","E","Ye","Zh","Z","Y","I","J","K","L","M","N","O","P","R","S","T","U","F","H","Ts","Ch","Sh","Sch","","Ju","Ja","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            for (int x = 0; x < abcCyr.length; x++ ) {
                if (message.charAt(i) == abcCyr[x]) {
                    builder.append(abcLat[x]);
                }
            }
        }
        return builder.toString();
    }

}
