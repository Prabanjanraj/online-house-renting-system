package Operations;
import java.util.*;
import java.sql.*;

public class Owner_Operations 
{
    String name;
    String H_Number;
    String H_Address;
    String H_city;
    String H_type;
    String Description;
    int price;
    String Address;
    int O_id;
    long O_Mnumber;
    String O_Mail;
    java.sql.Connection con;
    Scanner get;

    public void O_Inputs(java.sql.Connection con, String name, int O_id, String Address, long O_Mnumber, String O_Mail, Scanner get)
    {
        this.con = con;
        this.name = name;
        this.Address = Address;
        this.O_id = O_id;
        this.O_Mnumber = O_Mnumber;
        this.O_Mail = O_Mail;
        this.get = get;
        Owner_Operation();
    }

    public void Owner_Operation()
    {
        System.out.println("");
        System.out.println("\t\t----1. House");
        System.out.println("");
        System.out.println("\t\t----2. Add House");
        System.out.println("");
        System.out.println("\t\t----3. Remove House");
        System.out.println("");
        System.out.println("\t\t----4. Edit House");
        System.out.println("");
        System.out.println("\t\t----5. House Rental Requests");
        System.out.println("");
         System.out.println("\t\t----6. House Rental Old Requests");
        System.out.println("");
        System.out.print("\t\t");
        int a = get.nextInt();
        switch (a) {
            case 1:
                Houses();
                break;
            case 2:
                AddHouse();
                break;
            case 3:
                RemoveHouse();
                break;
            case 4:
                EditHouse();
                break;
            case 5:
                HRR();
                break;
            case 6:
                OLDHRR();
                break;
            default:
                System.out.println("\t\t----Invalid Option, Please Select the given Option");
                Owner_Operation();
                break;
        }
    }
    public void Houses()
    {
        try
            {
                Statement smt = con.createStatement();
                ResultSet R = smt.executeQuery("select * from houses");
                System.out.println();
                int i=1;
                if (!R.next()) {
                    System.out.println("\t\t----NO HOUSE EXIT");
                } else {
                    // Reset the cursor to the beginning of the result set
                    R.beforeFirst();
                System.out.println("\t\t---------->HOUSES<----------");
                System.out.println();
                while(R.next())
                {
                    if(R.getInt(7) == O_id)
                    {
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
                if (i==1) {
                    System.out.println("\t\t---->NO HOUSE EXIT");
                }
            }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        Owner_Operation();
    }
    public void AddHouse()
    {
        try
        {
        System.out.println("\t\t\t\t\t\t-------------------------------------------");   
        System.out.println("\t\t\t\t\t\t-               Adding House              -");
        System.out.println("\t\t\t\t\t\t-------------------------------------------"); 
        System.out.println("");
        System.out.print("\t\t++++Enter your House Number: ");
        H_Number = get.next();
        System.out.println("");
        System.out.print("\t\t++++Enter your House Address: ");
        get.nextLine();
        H_Address = get.nextLine();
        System.out.println("");
        System.out.print("\t\t++++Enter the City: ");
        H_city = get.nextLine();
        System.out.println("");
        System.out.print("\t\t++++Enter The House Type: ");
        H_type = get.nextLine();
        System.out.println("");
        System.out.print("\t\t++++Enter Description for House: ");
        Description = get.nextLine();
        System.out.println("");
        System.out.print("\t\t++++Enter The Price: ");
        price = get.nextInt();
        System.out.println("");
        Statement stmt = con.createStatement();
        stmt.execute("INSERT INTO houses (H_num, H_address, H_city, H_type, Description, price, O_ID, O_Mnumber) " +
    "VALUES ('" + H_Number + "', '" + H_Address + "', '" + H_city + "', '" + H_type + "', '" + Description + "', " + price + ", " + O_id + ", " + O_Mnumber + ")");

        System.out.println("\t\t---->House Added Successfully");
        System.out.println();
        
        }
        catch(Exception e){ System.out.println(e);}
        Owner_Operation();
    }

    

    public void RemoveHouse()
    {
        try
        {
        System.out.println("\t\t\t\t\t\t-------------------------------------------");   
        System.out.println("\t\t\t\t\t\t-               Remove House              -");
        System.out.println("\t\t\t\t\t\t-------------------------------------------"); 
        System.out.println("");
        System.out.print("\t\t++++Enter your House Number: ");
        H_Number = get.next();
        System.out.println("");
        Statement stmt = con.createStatement();
        ResultSet R = stmt.executeQuery("select * from houses");
        boolean houseFound = false;
        while(R.next())
        {
            if (R.getString("H_num").equals(H_Number) && R.getInt("O_ID") == O_id)
            { 
                houseFound = true;
                int deletedRows = stmt.executeUpdate("DELETE FROM houses WHERE H_num = '" + H_Number + "'");
                if (deletedRows > 0) {
                    System.out.println("\t\t----House Removed successfully.");
                } else {
                    System.out.println("\t\t----Failed to Remove the House.");
                }
            }
        }
        if (!houseFound) {
                    System.out.println();
                    System.out.print("\t\t----YOU HAVE NO HOUSE WITH THAT NUMBER");
                    System.out.println();
                }
        System.out.println();
        
        }
        catch(Exception e){ }
        Owner_Operation();
    } 

    public void EditHouse() {
        try {
            System.out.println("\t\t\t\t\t\t-------------------------------------------");
            System.out.println("\t\t\t\t\t\t-                Edit House               -");
            System.out.println("\t\t\t\t\t\t-------------------------------------------");
            System.out.println("");
            System.out.println("\t\t---->Enter 1 to Edit Address (OR) 2 for City (OR) 3 for Room Type (OR) 4 for Price (OR) 5 to EXIT!!");
            System.out.println("\t\t");
            int a = get.nextInt();
            switch (a) {
                case 1:
                    System.out.print("\t\t++++Enter the House Number: ");
                    H_Number = get.next();
                    System.out.println("");
                    System.out.print("\t\t++++Enter NEW Address: ");
                    get.nextLine();
                    H_Address = get.nextLine();
                    System.out.println("");
                    Statement stmt = con.createStatement();
                    ResultSet R = stmt.executeQuery("select * from houses");
                    while (R.next()) {
                        if (R.getString("H_num").equals(H_Number) && R.getInt("O_ID") == O_id) 
                        {
                            int updatedRows = stmt.executeUpdate("UPDATE houses SET H_address='" + H_Address + "' WHERE H_num='" + H_Number + "'");
                            if (updatedRows > 0) {
                                System.out.println("");
                                System.out.println("\t\t----House Address Edited successfully.");
                                System.out.println("");
                            } else {
                                System.out.println("");
                                System.out.println("\t\t----Failed to Edit the House Address.");
                                System.out.println("");
                            }
                            break;
                        }
                    }
                    System.out.println();
                    Owner_Operation();
                    break;
                case 2:
                    System.out.print("\t\t++++Enter the House Number: ");
                    H_Number = get.next();
                    System.out.println("");
                    System.out.print("\t\t++++Enter the New City Name: ");
                    H_city = get.next();
                    System.out.println("");
                    Statement stmt2 = con.createStatement();
                    ResultSet R2 = stmt2.executeQuery("select * from houses");
                    while (R2.next()) {
                        if (R2.getString("H_num").equals(H_Number) && R2.getInt("O_ID") == O_id) {
                            System.out.println("");
                            int updatedRows = stmt2.executeUpdate("update houses set H_city='" + H_city + "' where H_num='" + H_Number + "'");
                            if (updatedRows > 0) {
                                System.out.println("");
                                System.out.println("\t\t----House City Edited successfully.");
                                System.out.println("");
                            } else {
                                System.out.println("");
                                System.out.println("\t\t----Failed to Edit the House City.");
                                System.out.println("");
                            }
                             // Move inside the loop
                             break;
                        }
                    }
                    System.out.println();
                    Owner_Operation();
                    break;
                case 3:
                    System.out.print("\t\t++++Enter the House Number: ");
                    H_Number = get.next();
                    System.out.println("");
                    System.out.print("\t\t++++Enter the New House's type: ");
                    get.nextLine();
                    H_type = get.nextLine();
                    System.out.println("");
                    Statement stmt4 = con.createStatement();
                    ResultSet R4 = stmt4.executeQuery("select * from houses");
                    while (R4.next()) {
                        if (R4.getString("H_num").equals(H_Number) && R4.getInt("O_ID") == O_id) {
                            System.out.println("");
                            int updatedRows = stmt4.executeUpdate("update houses set H_type='" + H_type + "' where H_num='" + H_Number + "'");
                            if (updatedRows > 0) {
                                System.out.println("");
                                System.out.println("\t\t----House's Type Edited successfully.");
                                System.out.println("");
                            } else {
                                System.out.println("");
                                System.out.println("\t\t----Failed to Edit the House's Type.");
                                System.out.println("");
                            }
                          // Move inside the loop
                          break;
                        }
                    }
                    System.out.println();
                    Owner_Operation();
                    break;
                case 4:
                    System.out.print("\t\t++++Enter the House Number: ");
                    H_Number = get.next();
                    System.out.println("");
                    System.out.print("\t\t++++Enter the New Price: ");
                    price = get.nextInt();
                    System.out.println("");
                    Statement stmt3= con.createStatement();
                    ResultSet R3 = stmt3.executeQuery("select * from houses");
                    while (R3.next()) {
                        if (R3.getString("H_num").equals(H_Number) && R3.getInt("O_ID") == O_id) {
                            System.out.println("");
                            int updatedRows = stmt3.executeUpdate("update houses set price=" + price + " where H_num='" + H_Number + "'");
                            if (updatedRows > 0) {
                                System.out.println("");
                                System.out.println("\t\t----House's Price Edited successfully.");
                                System.out.println("");
                            } else {
                                System.out.println("");
                                System.out.println("\t\t----Failed to Edit the House's Price.");
                                System.out.println("");
                            }
                              // Move inside the loop
                              break;
                        }
                    }
                    System.out.println();
                    Owner_Operation();
                    break;
                default:
                    System.out.println("");
                    Owner_Operation();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    public void HRR() {
        try {
            System.out.println("\t\t\t\t\t\t-------------------------------------------");
            System.out.println("\t\t\t\t\t\t-           House Rental Request          -");
            System.out.println("\t\t\t\t\t\t-------------------------------------------");
            System.out.println("");
            boolean houseFound = false;
    
            Statement stmt = con.createStatement();
            int i = 1;
            ResultSet R = stmt.executeQuery("select * from house_rental_request");
    
            while (R.next()) {
                if (R.getInt("O_ID") == O_id) {
                    houseFound = true;
                    System.out.printf("\t\t----> %-3d) || House No. %-10s || Customer ID: %-20d || Mobile Number: %-15s || Mail: %-15s%n",
                            i,
                            R.getString(1),
                            R.getInt(2),
                            R.getBigDecimal(3),
                            R.getString(4));
                    i++;
                    break;
                }
            }
            if (!houseFound) {
                System.out.println();
                System.out.print("\t\t----NO REQUEST PLACED YET...");
                System.out.println();
            } else {
                System.out.println();
                System.out.println();
                System.out.println("\t\t----Enter 1 to Accept Request(Accept Means you Remove the House from List) (OR) 2 to Denie (OR) 3 TO EXIT");
                System.out.print("\t\t");
                int q = get.nextInt();
                if (q == 1) {
                    System.out.println();
                    System.out.println("\t\t----Enter the Customer ID");
                    System.out.print("\t\t");
                    int C_id = get.nextInt();
                    System.out.println();
                    i = 1;
                    Statement stmt1 = con.createStatement();
                    ResultSet R1 = stmt1.executeQuery("select * from house_rental_request");
                    while (R1.next()) {
                        if (R1.getInt("O_ID") == O_id && R1.getInt("C_id") == C_id) {
                            String H = R1.getString(1);
                            stmt1.execute("insert oldhrr values('" + R1.getString(1) + "'," + C_id + "," + R1.getBigDecimal(3) + ",'" + R1.getString(4) + "', '" + R1.getInt(5) + "', '" + "Accepted" + "')");
                            System.out.println();
                            stmt1.executeUpdate("delete from house_rental_request where C_id = " + C_id + "");
                            stmt1.executeUpdate("delete from houses where H_num = '" + H + "'");
                            System.out.println();
                            System.out.println("\t\t----Requested Accepted Successfully");
                            System.out.println();
                            break;
                        }
                    }
                }
                if (q == 2) {
                    System.out.println();
                    System.out.println("\t\t----Enter the Customer ID");
                    System.out.print("\t\t");
                    int C_id = get.nextInt();
                    System.out.println();
                    Statement stmt2 = con.createStatement();
                    ResultSet R2 = stmt2.executeQuery("select * from house_rental_request");
                    while (R2.next()) {
                         if (R2.getInt("O_ID") == O_id && R2.getInt("C_id") == C_id)
                         {
                            stmt2.execute("insert oldhrr values('" + R2.getString(1) + "'," + R2.getInt(2) + "," + R2.getBigDecimal(3) + ",'" + R2.getString(4) + "', '" + R2.getInt(5) + "', '" + "Denied" + "')");
                            stmt2.executeUpdate("delete from house_rental_request where C_id = " + C_id + " and O_ID = "+O_id+" ");
                            System.out.println();
                            System.out.println("\t\t----Requested Deined Successfully");
                            System.out.println();
                            break;
                         }
                    }
                }
            }
            System.out.println();
    
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Owner_Operation();
        }
    }
    


    public void OLDHRR() {
        try {
            System.out.println("\t\t\t\t\t\t-----------------------------------------------");
            System.out.println("\t\t\t\t\t\t-           House Rental OLD Request          -");
            System.out.println("\t\t\t\t\t\t-----------------------------------------------");
            System.out.println("");
            boolean houseFound = false;
    
            Statement stmt = con.createStatement();
            int i = 1;
            ResultSet R = stmt.executeQuery("select * from oldhrr");

            while (R.next()) {
                if (R.getInt("O_ID") == O_id) {
                    houseFound = true;
                    System.out.printf("\t\t----> %-3d) || House No. %-10s || Customer ID: %-20d || Mobile Number: %-15s || Mail: %-15s || Status: %-15s%n",
                            i,
                            R.getString(1),
                            R.getInt(2),
                            R.getBigDecimal(3),
                            R.getString(4),
                            R.getString(6));
                    i++;
                }
            }
            if (!houseFound) {
                    System.out.println();
                    System.out.print("\t\t----NO REQUEST PLACED YET...");
                    System.out.println();
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        Owner_Operation();
    }
}
