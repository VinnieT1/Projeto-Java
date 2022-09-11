public class Account {
    public String username, password;
    public User accountOwner;

    public Account(String username, String password, User accountOwner){
        this.username = username;
        this.password = password;
        this.accountOwner = accountOwner;
    }
}
