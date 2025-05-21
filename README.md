### Introduction

Platform for Epigenomic and Genomic Research (PEGR) is a web service platform that logs metadata for samples and sequencing experiment, manages the data processing workflows, and provides reporting and visualization. PEGR links together people, samples, protocols, sequencer and bioinformatics computation [1].

### Prerequisites

A tested stack of versions is listed below.

1. Java 21.0.6-zulu
   
   On MacOS, We recommend SDK to install and manage Java 21. SDK command to install and use Java 21:
   
      ```
      $ sdk install <PACKAGE> <VERSION>
      ```
    Also, you can check the available versions for a package, what have been installed, what's the default version:
      ```
      $ sdk list <PACKAGE>
      ```
    To switch the default version:
      ```
      $ sdk default <PACKAGE> <VERSION>
      ```

2. MariaDB 11.7.2
   - Linux: https://mariadb.org/download/
   - MacOS: We recommend Hombrew to install and manage MariaDB. See [link](https://mariadb.com/kb/en/installing-mariadb-on-macos-using-homebrew/) for details.
     ```
     $ brew install mariadb
     ```

3. Groovy 3.0.21 and Grails 6.2.3
   Groovy and Grails are only required if you need to develop and run the code, and they are not needed if you run the war file with Java.

   On MacOS, We recommend SDK to install and manage Groovy and Grails.

### Quick start

1. Turn on the MariaDB

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

<details close>
<summary>pegrDB MariaDB Schema</summary>
<br>

   Alignment schema:
   ![AlignmentTables](https://user-images.githubusercontent.com/18726510/145878749-9148ea49-2ef5-4276-a666-74d76abc8cca.png)
   
   Authorization schema:
   
   ![AuthorizationTables](https://user-images.githubusercontent.com/18726510/145878772-fe397589-9d7c-4700-bfca-9c172f3d9c35.png)
   
   Experiment schema:
   ![ExperimentTables](https://user-images.githubusercontent.com/18726510/145878780-4fe88a96-a141-47be-a0a7-44837fa7da45.png)
   
   Sample schema:
   ![SampleTables](https://user-images.githubusercontent.com/18726510/145878795-2cb55ba7-462e-4ea9-818c-c96f4818a3a2.png)
   
   Sequencing schema:
   ![SequencingTables](https://user-images.githubusercontent.com/18726510/145878801-26749c48-8298-46c9-ac08-62fd4fab9a17.png)
   

</details>

4. Create a config file 'pegr-config.properties' for the environment variables, e.g. the information on database connection, NGS repository connection, email connection and Single Sign On. Set the environment variable
```
$ export SPRING_CONFIG_ADDITIONAL_LOCATION=/Users/dshao/.grails/pegr-config.properties
```

A sample config file 'pegr-config.properties' is inlcuded in the sample_files folder https://github.com/seqcode/pegr/tree/master/sample_files. 

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

    5.1 Start PEGR with war file

      Download the latest release of PEGR at https://github.com/seqcode/pegr/releases.
      
      ```
      $ java -Dgrails.env=prod -jar pegr.war
      ```
      
   5.2 Start PEGR with the codes
      Clone the repository and run   
      ```
      $ git clone https://github.com/seqcode/pegr.git
      $ cd pegr/pegr/
      $ ./gradlew bootRun 
      ```

7. Go to http://localhost:8080/pegr/ in your browser to see the PEGR login page.

Default login is user name "labadmin" and password "labadmin". **Change the password right away at the user's profile page.**

For more information, please visit https://github.com/seqcode/pegr/wiki.

### Reference

[1] Danying Shao, Gretta Kellogg, Shaun Mahony, William Lai, and B. Franklin Pugh. 2020. PEGR: a management platform for ChIP-based next generation sequencing pipelines. In preparation. doi: https://doi.org/10.1101/2021.07.26.453821

