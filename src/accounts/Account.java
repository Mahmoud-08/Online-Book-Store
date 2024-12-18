package accounts;

public class Account {
    private String username;
    private String password;
    private String address;
    private String phone;
    private Role role;

    public Account(String username, String password, String address, String phone, Role role) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void updateDetails(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Username: " + username + ", Role: " + role + ", Address: " + address + ", Phone: " + phone;
    }
}
