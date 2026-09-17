package library;

// This class represents one Member (a person who borrows books)..
public class Member {

    private int memberId;
    private String name;
    private String email;

    public Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return "Member ID: " + memberId + " | Name: " + name + " | Email: " + email;
    }

    // Used to save member details into data/members.txt
    public String toFileString() {
        return memberId + "|" + name + "|" + email;
    }
}
