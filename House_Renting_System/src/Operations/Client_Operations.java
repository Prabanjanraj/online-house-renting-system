package Operations;
import java.sql.*;
import java.util.Scanner;

// Class for client operations
public class Client_Operations {
    String name;
    String Address;
    int C_id;
    long C_Mnumber;
    String C_Mail;
    java.sql.Connection con;
    Scanner get;

    // Method to initialize client inputs and start client operations
    public void C_Inputs(java.sql.Connection con, String name, int C_id, String Address, long C_Mnumber, String C_Mail, Scanner get) {
        this.con = con;
        this.name = name;
        this.Address = Address;
        this.C_id = C_id;
        this.C_Mnumber = C_Mnumber;
        this.C_Mail = C_Mail;
        this.get = get;
        Client_Operation();
    }

    // Method to display client operation options
    public void Options() {
        System.out.println();
        System.out.println("\t\t----Enter 1 to RENT HOUSE ");
        System.out.println("\t\t----Enter 2 to search by CITY ");
        System.out.println("\t\t----Enter 3 to Search by TYPE ");
        System.out.println("\t\t----Enter 4 to Sort PRICE");
        System.out.println("\t\t----Enter 5 To Search by all");
        System.out.println("\t\t----Enter 6 TO EXIT");
        System.out.println();
        System.out.print("\t\t");
        int a = get.nextInt();
        switch (a) {
            case 1:
                HouseRent();
                break;
            case 2:
                SearchCity();
                break;
            case 3:
                SearchType();
                break;
            case 4:
                SortPrice();
                break;
            case 5:
                Searchbyall();
                break;
            case 6:
                Client_Operation();
                break;
            default:
                System.out.print("\t\t----Enter a Valid Option and try again... ");
                System.out.println();
                Client_Operation();
                break;
        }
    }

    // Method to handle various client operations
    public void Client_Operation() {
        System.out.println();
        System.out.println("\t\t----1. Houses");
        System.out.println();
        System.out.println("\t\t----2. Owners Details");
        System.out.println();
        System.out.println("\t\t----3. Requested House Status");
        System.out.println();
        System.out.print("\t\t");
        int a = get.nextInt();
        if (a == 1)
            Houses();
        else if (a == 2) {
            try {
                // Displaying owners' details
                Statement smt = con.createStatement();
                ResultSet R = smt.executeQuery("select * from owners");
                System.out.println();
                int i = 1;
                System.out.println("\t\t---------->OWNERS DETAILS<----------");
                System.out.println();
                while (R.next()) {
                    System.out.printf("\t\t----> %-3d) Owner's Name: %-20s Mobile Number: %-15s Mail: %-30s%n",
                            i,
                            R.getString(1),
                            R.getBigDecimal(5),
                            R.getString(6));
                    System.out.println();
                    i++;
                }
            } catch (Exception e) {
                System.out.println(e);
                Client_Operation();
            }
            Client_Operation();
        } else if (a == 3) {
            try {
                // Displaying requested house status
                System.out.println("\t\t\t\t\t\t-----------------------------------------------");
                System.out.println("\t\t\t\t\t\t-           House Rental OLD Request          -");
                System.out.println("\t\t\t\t\t\t-----------------------------------------------");
                System.out.println("");
                boolean houseFound = false;

                Statement stmt = con.createStatement();
                int i = 1;
                ResultSet R = stmt.executeQuery("select * from oldhrr");

                while (R.next()) {
                    if (R.getInt("C_ID") == C_id) {
                        houseFound = true;
                        System.out.printf("\t\t----> %-3d) || House No. %-10s || Owner ID: %-20d || Status: %-15s%n",
                                i,
                                R.getString(1),
                                R.getInt(5),
                                R.getString(6));
                        i++;
                    }
                }
                if(houseFound == false)
                {
                    System.out.println("\t\t---->NO OLD REQUEST FOUND");
                }
            } catch (Exception e) {
                System.out.println(e);
                Client_Operation();
            }
            System.out.println();
            Client_Operation();
        } else {
            System.out.println("\t\t----Invalid Option, Please Select 1 (OR) 2 (OR) 3");
            System.out.println();
            Client_Operation();
        }
    }

    // Method to display available houses
    public void Houses() {
        try {
            Statement smt = con.createStatement();
            ResultSet R = smt.executeQuery("select * from houses");
            System.out.println();
            int i = 1;
            if (!R.next()) {
                System.out.println("\t\t---->NO HOUSE EXIT");
            } else {
                // Reset the cursor to the beginning of the result set
                R.beforeFirst();
                System.out.println("\t\t---------->HOUSES<----------");
                System.out.println();
                while (R.next()) {
                    System.out.printf("\t\t----> %-3d) || House No. %-10s || Address: %-20s || City: %-15s || Type: %-15s || Description: %-25s || Price: %-10d || Owner's Number: %-15s%n",
                            i,
                            R.getString(1),
                            R.getString(2),
                            R.getString(3),
                            R.getString(4),
                            R.getString(5),
                            R.getInt(6),
                            R.getBigDecimal(8));
                    System.out.println();
                    i++;
                }
            }
            Options();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Method to search houses by city
    public void SearchCity() {
        try {
            System.out.println();
            System.out.print("\t\t----Enter the City Name: ");
            String C = get.next();
            Statement smt = con.createStatement();
            ResultSet R = smt.executeQuery("select * from houses where H_city = '" + C + "'");
            System.out.println();
            int i = 1;
            if (!R.next()) {
                System.out.println("\t\t----NO HOUSE EXIT");
            } else {
                // Reset the cursor to the beginning of the result set
                R.beforeFirst();
                System.out.println("\t\t---------->FOUNDED HOUSES<----------");
                System.out.println();
                while (R.next()) {
                    System.out.printf("\t\t----> %-3d) || House No. %-10s || Address: %-20s || City: %-15s || Type: %-15s || Description: %-25s || Price: %-10d || Owner's Number: %-15s%n",
                            i,
                            R.getString(1),
                            R.getString(2),
                            R.getString(3),
                            R.getString(4),
                            R.getString(5),
                            R.getInt(6),
                            R.getBigDecimal(8));
                    System.out.println();
                    i++;
                }
            }
            Options();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Method to search houses by type
    public void SearchType() {
        try {
            System.out.println();
            System.out.print("\t\t----Enter the Room Type: ");
            get.nextLine();  // Consume the newline character
            String roomType = get.nextLine();
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("SELECT * FROM houses WHERE H_type = '" + roomType + "'");
            System.out.println();
            int i = 1;
            if (!rs.next()) {
                System.out.println("\t\t----NO HOUSE EXIT");
            } else {
                // Reset the cursor to the beginning of the result set
                rs.beforeFirst();
                System.out.println("\t\t---------->FOUNDED HOUSES<----------");
                System.out.println();
                while (rs.next()) {
                    System.out.printf("\t\t----> %-3d) || House No. %-10s || Address: %-20s || City: %-15s || Type: %-15s || Description: %-25s || Price: %-10d || Owner's Number: %-15s%n",
                            i,
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getInt(6),
                            rs.getBigDecimal(8));
                    System.out.println();
                    i++;
                }
            }
            Options();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Method to sort houses by price
    public void SortPrice() {
        try {
            System.out.println();
            System.out.print("\t\t----Enter the Price Amount to Filter From to TO: ");
            int from = get.nextInt();
            System.out.print("\t\t");
            int to = get.nextInt();
            get.nextLine();
            Statement smt = con.createStatement();
            ResultSet R = smt.executeQuery("SELECT * FROM houses WHERE price >= " + from + " AND price <= " + to);
            System.out.println();
            int i = 1;
            if (!R.next()) {
                System.out.println("\t\t----NO HOUSE EXIT");
            } else {
                System.out.println("\t\t---------->FOUNDED HOUSES<----------");
                System.out.println();
                // Reset the cursor to the beginning of the result set
                R.beforeFirst();
                while (R.next()) {
                    System.out.printf("\t\t----> %-3d) || House No. %-10s || Address: %-20s || City: %-15s || Type: %-15s || Description: %-25s || Price: %-10d || Owner's Number: %-15s%n",
                            i,
                            R.getString(1),
                            R.getString(2),
                            R.getString(3),
                            R.getString(4),
                            R.getString(5),
                            R.getInt(6),
                            R.getBigDecimal(8));
                    System.out.println();
                    i++;
                }
            }
            Options();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Method to search houses by city, type, and price
    public void Searchbyall() {
        try {
            System.out.println();
            System.out.print("\t\t----Enter the City: ");
            String C = get.next();
            System.out.println();
            System.out.print("\t\t----Enter the Room Type (Single bedroom, Double bedroom): ");
            get.nextLine();
            String roomType = get.nextLine();
            System.out.println();
            System.out.print("\t\t----Enter the Price Amount to Filter From to TO: ");
            int from = get.nextInt();
            int to = get.nextInt();
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("SELECT * FROM houses WHERE H_city = '" + C + "' and H_type = '" + roomType + "'  and price >= " + from + " AND price <= " + to);
            System.out.println();
            int i = 1;
            if (!rs.next()) {
                System.out.println("\t\t----NO HOUSE EXIT");
            } else {
                // Reset the cursor to the beginning of the result set
                rs.beforeFirst();
                System.out.println("\t\t---------->FOUNDED HOUSES<----------");
                System.out.println();
                while (rs.next()) {
                    System.out.printf("\t\t----> %-3d) || House No. %-10s || Address: %-20s || City: %-15s || Type: %-15s || Description: %-25s || Price: %-10d || Owner's Number: %-15s%n",
                            i,
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getInt(6),
                            rs.getBigDecimal(8));
                    System.out.println();
                    i++;
                }
            }
            Options();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Method to handle house rental
    public void HouseRent() {
        try {
            System.out.println();
            System.out.print("\t\t--->Enter the Number of the House that you want to Rent: ");
            String H_num = get.next();
            Statement smt = con.createStatement();
            ResultSet R = smt.executeQuery("select * from houses");

            boolean houseFound = false;  // Flag to check if the house is found

            while (R.next()) {
                if (R.getString(1).equals(H_num)) {
                    houseFound = true;  // Set the flag to true
                    // Store relevant data from the ResultSet before it is closed
                    String ownerName = R.getString(7);

                    // Insert into house_rental_request using the stored data
                    smt.execute("insert into house_rental_request values('" + H_num + "'," + C_id + "," + C_Mnumber + ",'" + C_Mail + "', '" + ownerName + "')");
                    System.out.println();
                    System.out.println("\t\t--->Your Request Successfully Placed. The owner will reach you shortly...");
                    System.out.println();
                    break;  // Exit the loop since the house is found
                }
            }

            if (!houseFound) {
                System.out.println();
                System.out.print("\t\t----NO HOUSE FOUND");
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Client_Operation();
        }
    }
}
