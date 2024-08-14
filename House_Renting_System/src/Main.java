import java.sql.*;
import java.util.*;
import Operations.*;

class Owner extends Owner_Operations
{
    Scanner get = new Scanner(System.in);
    String name;
    String pass;
    String Address;
    int O_id;
    long O_Mnumber;
    String O_Mail;

    public void check(Connection con)
    {
        System.out.println("");
        System.out.print("\t\t------Enter 1 to LOGIN (or) Enter 2 to CREATE ACCOUNT: ");
        int h = get.nextInt();
        if(h==1)
            owner_login(con);
        else
            create_owner(con);
    }

    public void create_owner(Connection con) {
        try {
            Random rnd = new Random();
            O_id = rnd.nextInt(10000000);

            System.out.println("\t\t\t\t\t\t-------------------------------------------");
            System.out.println("\t\t\t\t\t\t-          Owner Account Creation          -");
            System.out.println("\t\t\t\t\t\t-------------------------------------------");

            System.out.println("");
            System.out.print("\t\t++++Enter your Username: ");
            name = get.next();
            System.out.println("");
            
            do {
                System.out.print("\t\t++++Enter your Password (Strong Password Recommended): ");
                pass = get.next();

                if (!isStrongPassword(pass)) 
                {
                    System.out.println();
                    System.out.println("\t\t------Password is not strong enough. Please try again.");
                    System.out.println();
                }
            } while (!isStrongPassword(pass));
            
            System.out.println("");
            System.out.print("\t\t++++Enter your Address: ");
            get.nextLine();
            Address = get.nextLine();
            System.out.println("");
            System.out.print("\t\t++++Enter your Mobile Number: ");
            O_Mnumber = get.nextLong();
            System.out.println("");
            System.out.print("\t\t++++Enter your Mail Address: ");
            O_Mail = get.next();
            System.out.println("");

            Statement stmt = con.createStatement();
            stmt.execute("INSERT INTO owners (O_Username, O_pass, O_ID, O_address, O_Mnumber, O_Mail) " +
                    "VALUES ('" + name + "', '" + pass + "', " + O_id + ", '" + Address + "', " + O_Mnumber + ", '" + O_Mail + "')");

            System.out.println("\t\t------Account Created Successfully");
            System.out.println("");

            owner_login(con);

        } catch (Exception e) {
            System.out.println(e);
            create_owner(con);
        }
    }

    private boolean isStrongPassword(String password) 
    {
        return password.matches(".*[0-9].*") && password.matches(".*[!@#$%^&*()-+=_].*") && password.matches(".*[a-z].*") && password.matches(".*[A-Z].*");
    }

    public void owner_login(Connection con)
    {
        try
        {
        System.out.println("");
        System.out.println("\t\t\t\t\t\t-------------------------------------------");   
        System.out.println("\t\t\t\t\t\t-               Owner Login               -");
        System.out.println("\t\t\t\t\t\t-------------------------------------------");
        System.out.println("");
        System.out.print("\t\t++++Enter your Username: ");
        name = get.next();
        System.out.println("");
        System.out.print("\t\t++++Enter your Password: ");
        pass = get.next();
        System.out.println("");
        Statement stmt = con.createStatement();
        ResultSet R = stmt.executeQuery("select * from owners");
        while(R.next())
        {
            if(R.getString(1).equals(name) && R.getString(2).equals(pass))
            {
                System. out. print("\033[H\033[2J");
                System. out. flush();
                System.out.println("\n");
                System.out.println("\t\t\t\t\t\t--------------------------------------------------------------------------------");
                System.out.println("\t\t\t\t\t\t-                                                                              -");
                System.out.println("\t\t\t\t\t\t-                    WElCOME TO ONLINE HOUSE RENTING SYSTEM                    -");
                System.out.println("\t\t\t\t\t\t-                                                                              -");
                System.out.println("\t\t\t\t\t\t--------------------------------------------------------------------------------");
                System.out.println("");
                System.out.println("");
                System.out.println("\t\t\t\t\t\t-------------------------------------------------");   
                System.out.println("\t\t\t\t\t\t-         WELCOME "+R.getString(1).toUpperCase()+" ID("+R.getInt(3)+")            -");
                System.out.println("\t\t\t\t\t\t-------------------------------------------------"); 
                System.out.println("");
                O_Mnumber = R.getLong(5);
                O_Inputs(con, name, R.getInt(3), R.getString(4), O_Mnumber, R.getString(6), get);
            }
        }
         if(!R.next())
            {
                throw new Login_Failed("");
            }
    }
        catch(Login_Failed e)
        {
            System.out.println("\t\t--->Invalid Username or Password (Please Try Again) ");
            owner_login(con);
        }
        catch(Exception e)
        { 
            System.out.println(e);
            owner_login(con);
        }
    }

}

class Client extends Client_Operations
{
    Scanner get = new Scanner(System.in);
    String name;
    String pass;
    String Address;
    int C_id;
    long C_Mnumber;
    String C_Mail;

    public void check(Connection con)
    {
        System.out.println("");
        System.out.print("\t\t------Enter 1 to LOGIN (or) Enter 2 to CREATE ACCOUNT: ");
        int h = get.nextInt();
        if(h==1)
            client_login(con);
        else
            create_client(con);
    }
    public void create_client(Connection con)
    {
        try
        {
        Random Rnd = new Random();
        C_id = Rnd.nextInt(10000000);
        System.out.println("");
        System.out.println("\t\t\t\t\t\t-------------------------------------------");   
        System.out.println("\t\t\t\t\t\t-          Client Account Creation        -");
        System.out.println("\t\t\t\t\t\t-------------------------------------------");
        System.out.println("");
        System.out.print("\t\t++++Enter your Username: ");
        name = get.next();
        System.out.println("");
        do {
                System.out.print("\t\t++++Enter your Password (Strong Password Recommended): ");
                pass = get.next();

                if (!isStrongPassword(pass)) 
                {
                    System.out.println();
                    System.out.println("\t\t------Password is not strong enough. Please try again.");
                    System.out.println();
                }
            } while (!isStrongPassword(pass));
        System.out.println("");
        System.out.print("\t\t++++Enter your Address: ");
        get.nextLine();
        Address = get.nextLine();
        System.out.println("");
        System.out.print("\t\t++++Enter your Moblie Number: ");
        C_Mnumber = get.nextLong();
        System.out.println("");
        System.out.print("\t\t++++Enter your Mail Address: ");
        C_Mail = get.next();
        System.out.println("");
        Statement stmt = con.createStatement();
        stmt.execute("INSERT INTO client (C_Username, C_pass, C_ID, C_address, C_Mnumber, C_Mail) " +
             "VALUES ('" + name + "', '" + pass + "', " + C_id + ", '" + Address + "', " + C_Mnumber + ", '" + C_Mail + "')");
        System.out.println("\t\t------Account Created Successfully");
        client_login(con);
        
        }
        catch(Exception e)
        { 
            System.out.println(e);
            create_client(con);
        }

    }
    private boolean isStrongPassword(String password) 
    {
        return password.matches(".*[0-9].*") && password.matches(".*[!@#$%^&*()-+=_].*") && password.matches(".*[a-z].*") && password.matches(".*[A-Z].*");
    }

    public void client_login(Connection con) 
    {
        try
        {
        System.out.println("");
        System.out.println("\t\t\t\t\t\t-------------------------------------------");   
        System.out.println("\t\t\t\t\t\t-               Client Login               -");
        System.out.println("\t\t\t\t\t\t-------------------------------------------");
        System.out.println("");
        System.out.print("\t\t++++Enter your Username: ");
        name = get.next();
        System.out.println("");
        System.out.print("\t\t++++Enter your Password: ");
        pass = get.next();
        System.out.println("");
        Statement stmt = con.createStatement();
        ResultSet R = stmt.executeQuery("select * from client");
        while(R.next())
        {
            if(R.getString(1).equals(name) && R.getString(2).equals(pass))
            {
                System. out. print("\033[H\033[2J");
                System. out. flush();
                System.out.println("\n");
                System.out.println("\t\t\t\t\t\t--------------------------------------------------------------------------------");
                System.out.println("\t\t\t\t\t\t-                                                                              -");
                System.out.println("\t\t\t\t\t\t-                    WElCOME TO ONLINE HOUSE RENTING SYSTEM                    -");
                System.out.println("\t\t\t\t\t\t-                                                                              -");
                System.out.println("\t\t\t\t\t\t--------------------------------------------------------------------------------");
                System.out.println("");
                System.out.println("");
                System.out.println("\t\t\t\t\t\t------------------------------------------------");   
                System.out.println("\t\t\t\t\t\t-        WELCOME "+R.getString(1).toUpperCase()+" ID("+R.getInt(3)+")         -");
                System.out.println("\t\t\t\t\t\t------------------------------------------------"); 
                System.out.println("");
                C_Mnumber = R.getLong(5);
                C_Inputs(con, name, R.getInt(3), R.getString(4), C_Mnumber, R.getString(6), get);
            }
        }
        if(!R.next())
        {
            throw new Login_Failed("");
        }
        }
        catch(Login_Failed e)
        {
            System.out.println("");
            System.out.println("\t\t--->Invalid Username or Password (Please Try Again) ");
            client_login(con);
        }
        catch(Exception e)
        {
            System.out.println(e);
            client_login(con);
        }
    }

}

class Login_Failed extends Exception
{
    public Login_Failed(String msg)
    {
        super(msg);
    }
}

class Main {
    private static Connection con;

    public static void main(String args[]) {
        Client C = new Client();
        Owner O = new Owner();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/house_renting_system",
                    "root",
                    "chill");
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e);
        }
        Scanner get1 = new Scanner(System.in);
        System. out. print("\033[H\033[2J");
        System. out. flush();
        System.out.println("\n");
        System.out.println("\t\t\t\t\t\t--------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t-                                                                              -");
        System.out.println("\t\t\t\t\t\t-                    WElCOME TO ONLINE HOUSE RENTING SYSTEM                    -");
        System.out.println("\t\t\t\t\t\t-                                                                              -");
        System.out.println("\t\t\t\t\t\t--------------------------------------------------------------------------------");
        System.out.println("");
        System.out.print("\t\t------Enter 1 if you're CLIENT (OR) Enter 2 if you're OWNER: ");
        int t = get1.nextInt();
        if(t == 1)
            C.check(con);
        else
            O.check(con);
        get1.close();
    }
}