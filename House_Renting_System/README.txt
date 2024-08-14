----ONLINE HOUSE RENTING SYSTEM----

Setup:

--Before Started your System should has VISUAL STUDIO CODE, JAVA AND MYSQL

--You should create a database name "house_renting_system" on your Mysql
  And it should have the following tables with variables.
  
  client
+------------+-------------+------+-----+---------+-------+
| Field      | Type        | Null | Key | Default | Extra |
+------------+-------------+------+-----+---------+-------+
| C_Username | varchar(30) | YES  |     | NULL    |       |
| C_pass     | varchar(20) | YES  |     | NULL    |       |
| C_ID       | int(11)     | YES  |     | NULL    |       |
| C_address  | varchar(50) | YES  |     | NULL    |       |
| C_Mnumber  | bigint(20)  | YES  |     | NULL    |       |
| C_Mail     | varchar(30) | YES  |     | NULL    |       |
+------------+-------------+------+-----+---------+-------+

  Owner
+------------+-------------+------+-----+---------+-------+
| Field      | Type        | Null | Key | Default | Extra |
+------------+-------------+------+-----+---------+-------+
| O_Username | varchar(30) | YES  |     | NULL    |       |
| O_pass     | varchar(20) | YES  |     | NULL    |       |
| O_ID       | int(11)     | YES  |     | NULL    |       |
| O_address  | varchar(50) | YES  |     | NULL    |       |
| O_Mnumber  | bigint(20)  | YES  |     | NULL    |       |
| O_Mail     | varchar(30) | YES  |     | NULL    |       |
+------------+-------------+------+-----+---------+-------+

  houses
+-------------+--------------+------+-----+---------+-------+
| Field       | Type         | Null | Key | Default | Extra |
+-------------+--------------+------+-----+---------+-------+
| H_num       | varchar(50)  | NO   |     | NULL    |       |
| H_address   | varchar(50)  | NO   |     | NULL    |       |
| H_city      | varchar(20)  | NO   |     | NULL    |       |
| H_type      | varchar(30)  | NO   |     | NULL    |       |
| Description | varchar(100) | NO   |     | NULL    |       |
| price       | int(11)      | YES  |     | NULL    |       |
| O_ID        | int(11)      | YES  |     | NULL    |       |
| O_Mnumber   | bigint(20)   | YES  |     | NULL    |       |
+-------------+--------------+------+-----+---------+-------+

  house_rental_request
+-----------+-------------+------+-----+---------+-------+
| Field     | Type        | Null | Key | Default | Extra |
+-----------+-------------+------+-----+---------+-------+
| H_num     | varchar(50) | YES  |     | NULL    |       |
| C_ID      | int(11)     | YES  |     | NULL    |       |
| C_Mnumber | bigint(20)  | YES  |     | NULL    |       |
| C_Mail    | varchar(30) | YES  |     | NULL    |       |
| O_ID      | int(11)     | YES  |     | NULL    |       |
+-----------+-------------+------+-----+---------+-------+

  oldhrr
+-----------+-------------+------+-----+---------+-------+
| Field     | Type        | Null | Key | Default | Extra |
+-----------+-------------+------+-----+---------+-------+
| H_num     | varchar(50) | YES  |     | NULL    |       |
| C_ID      | int(11)     | YES  |     | NULL    |       |
| C_MNumber | bigint(20)  | YES  |     | NULL    |       |
| C_Mail    | varchar(30) | YES  |     | NULL    |       |
| O_ID      | int(11)     | YES  |     | NULL    |       |
| Status    | varchar(30) | YES  |     | NULL    |       |
+-----------+-------------+------+-----+---------+-------+


--Open Visual Studio Code and create a new Java Project and Select the House_Renting_System Folder into that

--We already include mysql-connector in the project file if you found any error on connecction just add the musql-connector.jar file on the java project module on vscode

--Once you finished your setup just Run the JAVA Project... 
