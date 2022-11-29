public class Account {
    private String username;
    private String password;
    private User accountOwner;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getAccountOwner() {
        return this.accountOwner;
    }

    public void setAccountOwner(User accountOwner) {
        this.accountOwner = accountOwner;
    }

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, User accountOwner){
        this(username, password);
        this.accountOwner = accountOwner;
    }
}
