How to install Grails on Mac and setup a local instance of PEGR

		Installing the necessary materials

1.	Install SDKMAN!  using this website for directions - http://sdkman.io/install.html

2. In the terminal, run "sdk install grails xxx" where xxx is your desired version number.

------------------------
IMPORTANT NOTE FOR PEGR
------------------------

	2a. If you want to create a local instance of pegr, install grails 2.5.5 AND 2.5.4, Groovy 2.4.4, Tomcat 7.0.70 and JDK 1.7.

	2b. In order to run grails 2.5.5, the one for pegr, you must first use grails 2.5.4.

	2c.  Run "sdk use grails 2.5.4", then run "grails".  This will create all the dependencies needed.

	2d.  Next, switch to grails 2.5.5 with "sdk use grails 2.5.5"

	2e. To install Java 1.7, run "sdk install java 7uXXX"

	2f. Run "sdk install groovy 2.4.4"
---------
END NOTE
---------

3. Download the apachetomcat tar here - https://tomcat.apache.org/download-80.cgi

	3a. Take the extracted folder and rename it to "tomcat"

	3b. To start a tomcat server, goto /Applications/tomcat/bin/ and enter in "./catalina.sh run".  A successful start will have an end message of "Server startup in X ms"

5. Install MYSQL by downloading the .dmg here - https://dev.mysql.com/downloads/mysql/  NOTE: You don't need to make an account, just look near the bottom to download directly.  NOTE: Remember that password they gave you, you will need it

	5a.  You will probably need to add mysql to your path directly, so in your .bash_profile, add in ths line "export PATH=$PATH:/usr/local/mysql/bin"

	5b.  You can also download MYSQL Workbench to help with looking through the database, although it is not mandatory.

