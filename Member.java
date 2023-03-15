// Marco Soekmono
// 3/10/23
// CS145
// Lab 6

// This program will be able to create a binary search tree database
// of members containing attributes that is able to be added, deleted,
// edited, and printed out on all its possible traversals.

// For extra credit, I made sure user input was foolproof and that the
// traversals were printable to another file


// class initiation
public class Member {

    // attributes privated
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String email;
    private String phoneNumber;
    private int primaryKey;

    // constructor
    public Member(String firstName, String lastName, String streetAddress, String city, String state, String zip, String email, String phoneNumber, int primaryKey) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.primaryKey = primaryKey;
    }

    // convert member to a readable string
    public String toString() {
        String desc = 
            "Primary Key: " + primaryKey + "\n" +
            "First Name: " + firstName + "\n" +
            "Last Name: " + lastName + "\n" +
            "Street Address: " + streetAddress + "\n" +
            "City: " + city + "\n" +
            "State: " + state + "\n" +
            "Zip: " + zip + "\n" +
            "Email: " + email + "\n" +
            "Phone Number: " + phoneNumber;

        return desc;
    }

    // everything below are getters and setters 

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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }
    
}
