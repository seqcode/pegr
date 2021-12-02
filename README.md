### Introduction

Platform for Epigenomic and Genomic Research (PEGR) is a web service platform that logs metadata for samples and sequencing experiment, manages the data processing workflows, and provides reporting and visualization. PEGR links together people, samples, protocols, sequencer and bioinformatics computation [1].

### Prerequisites

A tested stack of versions is listed below.

1. OpenJDK 11.0.12
   - MacOS: We recommend Homebrew to install and manage Java 11
      - Homebrew command to install and use Java 11:
      ```
      brew tap adoptopenjdk/openjdk
      brew install --cask adoptopenjdk8
      ```
      - Java 11 executable is now located here:
      ```
      /Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home/bin/java
      ```
2. MariaDB 10.5.5
   - Linux: https://mariadb.org/download/
   - MacOS: We recommend Hombrew to install and manage MariaDB
   - https://mariadb.com/kb/en/installing-mariadb-on-macos-using-homebrew/
   ```
   $ brew install mariadb
   ```

### Quick start

1. Download the latest releasse of PEGR
   - https://github.com/seqcode/pegr/releases/tag/v0.1-beta

1.1 (MacOS only) Turn on MariaDB server
   ```
   $ mysql.server start
   ``` 

   (Optional) To make MariaDB start automatically on system startup 
   ```
   $ brew services start mariadb
   ```

2. Set up database. Create an empty database with utf8 coding, a database user, and assigning privileges.
```
$ mysql
> CREATE DATABASE <DB_NAME> CHARACTER SET utf8 COLLATE utf8_general_ci;
> CREATE USER '<USER_NAME>'@'localhost' IDENTIFIED BY '<USER_PASS>';
> GRANT ALL PRIVILEGES ON <DB_NAME>.* TO '<USER_NAME>'@'localhost' with grant option;
> FLUSH PRIVILEGES;
> exit
```
<DB_NAME> : Name assigned to PEGR database

<USER_NAME> : User ID that will access PEGR database

<USER_PASS> : Password used by User ID to access PEGR database


Example mySQL code provided here:
```
$ mysql
> CREATE DATABASE pegrDB CHARACTER SET utf8 COLLATE utf8_general_ci;
> CREATE USER 'pegruser'@'localhost' IDENTIFIED BY 'password';
> GRANT ALL PRIVILEGES ON pegrDB.* TO 'pegruser'@'localhost' with grant option;
> FLUSH PRIVILEGES;
> exit
```

3. Import the baseline database and copy over the PDF files for the pre-defined protocols.
   
Generic code:
```
$ mysql -u <USER_NAME> -p <DB_NAME> < sample_files/pegr_baseline.sql 
```
Code following example above:
```
$ mysql -u pegruser -p pegrDB < sample_files/pegr_baseline.sql
```
The baseline database has pre-defined item types and protocols. The PDF files for the  pre-defined protocols are provided in the folder "sample_files/protocols". Copy the "protocols" folder into your selected folder that stores uploaded data:

Generic code:
```
$ cp -r sample_files/protocols /path/to/store/uploaded/data
```
Code following example above:
```
$ cp -r sample_files/protocols /tmp/
```

4. Create a config file 'pegr-config.properties' in the folder {userHome}/.grails/ for the environment variables, e.g. the information on database connection, NGS repository connection, email connection and Single Sign On.

A sample config file 'pegr-config.properties' is inlcuded in the sample_files folder https://github.com/seqcode/pegr/tree/master/sample_files. 
```
$ mkdir ~/.grails/
$ cp sample_files/pegr-config.properties ~/.grails/
```

Not all pegr-config.properites need to be filled out for PEGR to function. Below are the minimum properties needed to be set.
Generic 'pegr-config.properties':
```
dataSource.url= jdbc:mysql://localhost/<DB_NAME>?useUnicode=true&characterEncoding=UTF-8
dataSource.username=<USER_NAME>
dataSource.password=<USER_PASS>
sso.url=none
sso.type=none
sso.principle=none
filesroot=/path/to/store/uploaded/data
```

Sample 'pegr-config.properties' following example above:
```
dataSource.url= jdbc:mysql://localhost/pegrDB?useUnicode=true&characterEncoding=UTF-8
dataSource.username=pegruser
dataSource.password=password
sso.url=none
sso.type=none
sso.principle=none
filesroot=/tmp
```
More detailed instructions on the config file are provided at https://github.com/seqcode/pegr/wiki/Configurations.

5. Start PEGR.

```
$ java -Dgrails.env=prod -jar pegr.war
```

6. Go to http://localhost:8080/pegr/ in your browser to see the PEGR login page.

Default login is user name "labadmin" and password "labadmin". **Change the password right away at the user's profile page.**

For more information, please visit https://github.com/seqcode/pegr/wiki.

### Reference

[1] Danying Shao, Gretta Kellogg, Shaun Mahony, William Lai, and B. Franklin Pugh. 2020. PEGR: a management platform for ChIP-based next generation sequencing pipelines. In preparation.
