# MySQL Setup (Database Connectivity)

1. Install MySQL Server + MySQL Workbench.
2. Run `database/mysql_setup.sql` (creates DB `reservation`, 4 tables, sample data).
3. Put the MySQL driver jar in `WebContent/WEB-INF/lib/`
   (download `mysql-connector-j-8.0.33.jar`, or build with Maven: `clean install`, pom already has the dependency).
4. Edit `src/application.properties` -> set your MySQL password:
   ```
   username=root
   password=YOUR_MYSQL_PASSWORD
   driverName=com.mysql.cj.jdbc.Driver
   connectionString=jdbc:mysql://localhost:3306/reservation?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
   ```
5. Run on Tomcat. Console must show: `Database Connection Success!!`
6. Login: user `shashi@demo.com / shashi`, admin `admin@demo.com / admin`.
