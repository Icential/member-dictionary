// Marco Soekmono
// 3/10/23
// CS145
// Lab 6

// This program will be able to create a binary search tree database
// of members containing attributes that is able to be added, deleted,
// edited, and printed out on all its possible traversals.

// For extra credit, I made sure user input was foolproof and that the
// traversals were printable to another file and that it can read input
// from a file given the correct format


// import libraries
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;


// main class UI
public class UI {

    // print intro
    public static void intro() {
        System.out.println("Welcome to Dictionary. You may add, delete, look up");
        System.out.println("or modify a binary tree of members in either pre-order");
        System.out.println("in-order or post-order traversal.");
    }

    // print options
    public static void options() {
        System.out.println("What would you like to do?");
        System.out.println("Add a member (a)");
        System.out.println("Delete a member (d)");
        System.out.println("Look-up the Dictionary (s)");
        System.out.println("Edit a member (e)");
        System.out.println("See amount of members (t)");
        System.out.println("Read a chosen file to tree (r)");
        System.out.println("Print the tree onto a file (p)");
        System.out.println("Quit the program (q)");
    }

    // create a new member
    public static Member createMember(Scanner scan) {
        String[] memberAttributes = {"First Name", "Last Name", "Street Address", "City", "State", "Zip", "Email", "Phone Number", "Primary Key"};
        String[] inputAttributes = new String[9];

        for (int i = 0; i < memberAttributes.length; i++) {
            System.out.print("Enter a " + memberAttributes[i] + ": ");
            inputAttributes[i] = scan.nextLine();
        }

        Member node = new Member(inputAttributes[0], inputAttributes[1], inputAttributes[2], inputAttributes[3], inputAttributes[4], inputAttributes[5], inputAttributes[6], inputAttributes[7], Integer.parseInt(inputAttributes[8]));
        return node;
    }

    // scan.nextInt() but foolproof
    public static int failproofNextInt(Scanner scan) {
        try {
            int input = scan.nextInt();
            scan.nextLine();
            return input;
        } catch (Exception e) {
            System.out.println("Invalid integer input!");
            return failproofNextInt(scan);
        }
    }

    // edit a member and give the new node
    public static Member editMember(Scanner scan, Member node) {
        System.out.println("What attribute would you like to edit? (type a number)");
        System.out.println("1: First Name, 2: Last Name, 3: Street Address, 4: City, 5: State, 6: Zip, 7: Email, 8: Phone Number, 9: Primary Key");
        int input = failproofNextInt(scan);
        System.out.print("Type in the new value: ");
        String newAttribute = scan.nextLine();
        switch (input) {
            case 1:
                Member edited1 = new Member(newAttribute, node.getLastName(), node.getStreetAddress(), node.getCity(), node.getState(), node.getZip(), node.getEmail(), node.getPhoneNumber(), node.getPrimaryKey());
                return edited1;
            case 2:
                Member edited2 = new Member(node.getFirstName(), newAttribute, node.getStreetAddress(), node.getCity(), node.getState(), node.getZip(), node.getEmail(), node.getPhoneNumber(), node.getPrimaryKey());
                return edited2;
            case 3:
                Member edited3 = new Member(node.getFirstName(), node.getLastName(), newAttribute, node.getCity(), node.getState(), node.getZip(), node.getEmail(), node.getPhoneNumber(), node.getPrimaryKey());
                return edited3;
            case 4:
                Member edited4 = new Member(node.getFirstName(), node.getLastName(), node.getStreetAddress(), newAttribute, node.getState(), node.getZip(), node.getEmail(), node.getPhoneNumber(), node.getPrimaryKey());
                return edited4;
            case 5:
                Member edited5 = new Member(node.getFirstName(), node.getLastName(), node.getStreetAddress(), node.getCity(), newAttribute, node.getZip(), node.getEmail(), node.getPhoneNumber(), node.getPrimaryKey());
                return edited5;
            case 6:
                Member edited6 = new Member(node.getFirstName(), node.getLastName(), node.getStreetAddress(), node.getCity(), node.getState(), newAttribute, node.getEmail(), node.getPhoneNumber(), node.getPrimaryKey());
                return edited6;
            case 7:
                Member edited7 = new Member(node.getFirstName(), node.getLastName(), node.getStreetAddress(), node.getCity(), node.getState(), node.getZip(), newAttribute, node.getPhoneNumber(), node.getPrimaryKey());
                return edited7;
            case 8:
                Member edited8 = new Member(node.getFirstName(), node.getLastName(), node.getStreetAddress(), node.getCity(), node.getState(), node.getZip(), node.getEmail(), newAttribute, node.getPrimaryKey());
                return edited8; 
            case 9:
                Member edited9 = new Member(node.getFirstName(), node.getLastName(), node.getStreetAddress(), node.getCity(), node.getState(), node.getZip(), node.getEmail(), node.getPhoneNumber(), Integer.parseInt(newAttribute));
                return edited9;
            default:
                System.out.println("Out of range!");
                return editMember(scan, node);
        }
    }

    // text name format standardization
    public static String txtNaming(String s) {
        if (s.contains(".txt")) {
            return s;
        } else {
            return s + ".txt";
        }
    }

    // read file and make a new dictionary
    public static Dictionary readFile(Scanner scan) {
        Dictionary dict = new Dictionary();
        System.out.println("Which file would you like to read?");
        String input = scan.nextLine();
        input = txtNaming(input);
        int i = 1;
        String[] attributes = new String[9];
        String newData = "";

        try {
            File f = new File(input);
            Scanner scanFile = new Scanner(f);
            while (scanFile.hasNextLine()) {
                String data = scanFile.nextLine();
                if (data.contains(":") && i != 10) {
                    newData = data.split(":")[1];
                    attributes[i-1] = newData.trim();
                }
                if (i == 10) {
                    Member member = new Member(attributes[0], attributes[1], attributes[2], attributes[3], attributes[4], attributes[5], attributes[6], attributes[7], Integer.parseInt(attributes[8]));
                    dict = dict.add(dict, member);
                    i = 0;
                }
                i++; 
            }
            scanFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when making the file.");
            e.printStackTrace();
        }
        return dict;
    }

    // print a dictionary to a file
    public static void printToFile(Scanner scan, Dictionary dict) {
        System.out.println("What name would you like to name the file?");
        String name = scan.nextLine();
        name = txtNaming(name);
        System.out.println("In what traversal would you like to go in? (type in number)\n1: Pre-order, 2: In-order, 3: Post-order");
        int traversal = failproofNextInt(scan);
        if (traversal != 1 && traversal != 2 && traversal != 3) {
            System.out.println("Out of range!");
            return;
        }
        System.out.println();
        try {
            File f = new File(name);
            if (f.createNewFile()) {
                try {
                    FileWriter writer = new FileWriter(name);
                    writer.write(dict.lookup(dict, traversal));
                    writer.close();
                    System.out.println("File successfully made");
                } catch (IOException e) {
                    System.out.println("An error occured when making the file");
                    e.printStackTrace();
                }
            } else {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            System.out.println("An error occured when making the file");
            e.printStackTrace();
        }
    }

    // main session
    public static void session() {
        Dictionary dict = new Dictionary();
        boolean active = true;
        Scanner scan = new Scanner(System.in);

        System.out.println();

        while (active) {
            
            options();
            
            String input = scan.nextLine();
            
            System.out.println();

            switch (input.toLowerCase().charAt(0)) {

                // add
                case 'a':
                    Member newMember = createMember(scan);
                    dict = dict.add(dict, newMember);
                    System.out.println("Member successfully added!");
                    break;

                // delete
                case 'd':
                    System.out.println("Which member would you like to delete? (enter member primary key)");
                    int deleteNodeInt = failproofNextInt(scan);
                    if (!dict.contains(dict, deleteNodeInt)) {
                        System.out.println("Out of bounds!");
                        break;
                    }
                    System.out.println();
                    Member deleteNode = dict.get(dict, deleteNodeInt);
                    dict = dict.remove(dict, deleteNode);
                    System.out.println("Member successfully deleted!");
                    break;

                // look up
                case 's':
                    System.out.println("In what traversal would you like to go in? (type in number)\n1: Pre-order, 2: In-order, 3: Post-order");
                    int traversal = failproofNextInt(scan);
                    if (traversal != 1 && traversal != 2 && traversal != 3) {
                        System.out.println("Out of range!");
                        break;
                    }
                    System.out.println();
                    System.out.println(dict.lookup(dict, traversal));
                    break;

                // edit
                case 'e':
                    System.out.println("Which member would you like to edit? (enter member primary key)");
                    int editNodeInt = failproofNextInt(scan);
                    if (!dict.contains(dict, editNodeInt)) {
                        System.out.println("Out of bounds!");
                        break;
                    }
                    System.out.println();
                    Member editNode = dict.get(dict, editNodeInt);
                    Member newEditNode = editMember(scan, editNode);
                    dict = dict.edit(dict, editNode, newEditNode);
                    System.out.println("Member successfully edited!");
                    break;

                // total amount of nodes
                case 't':
                    System.out.println("There is a total amount of " + dict.totalNodes(dict) + " member(s)");
                    break;

                // read file 
                case 'r':
                    dict = readFile(scan);
                    System.out.println("File successfully added");
                    break;

                // print file
                case 'p':
                    printToFile(scan, dict);
                    break;

                // quit program
                case 'q':
                    System.out.println("Goodbye thank you for using this program.");
                    active = false;
                    break;

                // unknow command
                default:
                    System.out.println("Unknown command!");
                    break;
            }

            System.out.println();

        }

        scan.close();
    }

    // main
    public static void main(String[] args) {
        intro();
        session();
    }
}
