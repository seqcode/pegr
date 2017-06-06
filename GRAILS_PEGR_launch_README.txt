Make sure to read GRAILS_PEGR_install_README before progressing further.

-------------------------------------------
HOW TO SET UP A LOCAL INSTANCE OF PEGR
-------------------------------------------

1. Clone the pegr repository here -  https://github.com/seqcode/pegr.git into some directory

2. In a directory of your choice, run "grails create-app pegr"
This will install more dependencies and create a folder "pegr" with grails files inside of it.

3.  If needed, copy and paste all the files from the cloned directory into your own.

4. Navigate to pegr/grails-app/conf/BuildConfig.groovy and edit these lines

grails.project.target.level = 1.6
grails.project.source.level = 1.6

into these lines

grails.project.target.level = 1.7
grails.project.source.level = 1.7
grails.server.port.http = 8081

5. Download the appropiate sql;, located here - https://psu.app.box.com/file/175943271869

6. Rename the sql to something simple, like pegrDB.

7. Go to System Preferences and you will find a MySql Icon.  Click on that and start the SQL server.

8. Connect to your server by typing in "mysql -u root -p", it will prompt you for that password it gave you.

9.  After successfully connecting into mysql, run this line in mysql
"ALTER USER 'root'@'localhost' IDENTIFIED BY 'MyNewPass';" to reset your password

10.  In mysql, run "create database pegr;", then "use pegr;" switch into your new database.

11. To use the sql file you downloaded, you can open up mysql in the directory you downloaded the file in and run this line 
"source pegrDB.sql".  Make sure you are in the pegr database you created when you run this command in mysql.

You cannot login into the local instance of PEGR without a valid non-WebAccess account, so here is one way to modify an existing account to gain login credentials.

11a. Within your pegrDB, locate the user "labadmin" and replace the password value with this value "$2a$10$lofdL9TXFMB9hwI7usyeGezRheRYFRFNtZK9y3UZqsTgFTfIQWUg2".  This can be done with the following sql statement.

UPDATE user
SET password="$2a$10$lofdL9TXFMB9hwI7usyeGezRheRYFRFNtZK9y3UZqsTgFTfIQWUg2" 
WHERE username="labadmin";

11b.  Now you should have the account username "labadmin" with the password of "reb1$8$6q"

12. Within the BuildConfig.groovy, check the plugins block and verify that the build for the tomcat is appropiate to your version AND add these lines within the plugin block

 		compile ":spring-security-core:2.0.0"
        compile "org.grails.plugins:quartz:1.0.1"
        compile "org.grails.plugins:mail:1.0.7"

13.  Also, within the dependencies block, uncomment runtime 'mysql'

14.  Now, within the DataSource.groovy, edit the datasource block to look like this

environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:mysql://localhost:3306/pegr"
			username="root"
			password="YOURPASSWORDHERE"
        }
    }

15.  Now go into the main pegr folder and type "grails run-app".  This successfully start and provide you an url which directs you to the login screen of PEGR.



