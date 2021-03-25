### Introduction

Platform for Epigenomic and Genomic Research (PEGR) is a web service platform that logs metadata for samples and sequencing experiment, manages the data processing workflows, and provides reporting and visualization. PEGR links together people, samples, protocols, sequencer and bioinformatics computation [1].

### Prerequisites

A tested stack of versions is listed below.

1. OpenJDK 1.8.0_212
2. MariaDB 10.4.6
3. python and xlsx2csv 0.7.2 (You can download the executable xlsx2csv from https://github.com/seqcode/pegr/releases and put it in /usr/local/bin)

### Quick start

1. Set up database. First, create an empty database with utf8 coding and assign privileges.

```
CREATE DATABASE <DB_NAME> CHARACTER SET utf8 COLLATE utf8_general_ci;
```
And optionally, you can create a database user specifically for the PEGR database. Alternatively, you can choose to use an existing database user.
```
CREATE USER 'some_user'@'localhost' IDENTIFIED BY 'some_pass';
```
And then grant the database access.
```
GRANT ALL PRIVILEGES ON <DB_NAME>.* TO '<USER_NAME>'@'localhost' with grant option;
FLUSH PRIVILEGES;
```

Then import the baseline database.
   
```
$ mysql -u <USER_NAME> -p <DB_NAME> < sample_files/pegr_baseline.sql 
```

And configure the database connection in PEGR. There are two places that you can put the database connection settings. One is in the pegr/grails-app/conf/application.yml file. You can have different connection settings for development, test and production environments.
     
```
environments:
    development:
        dataSource:
            dbCreate: none
            url: jdbc:mysql://localhost/<DB_NAME>?useUnicode=true&characterEncoding=UTF-8
            username: <USER_NAME>
            password: <PASSWORD>
    test:
        ...
    production:
        ...
        
```  

The other way is to create a config file and let PEGR know the config file's location. For example, you can create a groovy file as below. 

```
environments {
    development {
        dataSource {
            url = "jdbc:mysql://localhost/<DB_name>?useUnicode=true&characterEncoding=UTF-8"
            username="USER_NAME"
            password="PASSWORD"
        }
    }
    test {
        ...
    }
    production {
        ...
    }
}
```

And add the file's path to the file pegr/grails/conf/application.groovy.

```
grails.config.locations = [ ...
                            "file:<FILEPATH>"]
```

2. Create a config file for the environment variables and store it in one of the filepaths indicated on the top of file pegr/grails-app/conf/application.groovy. A sample config file is inlcuded in the smple_files folder. 

3. Run PEGR.

```
$ java -Dgrails.env=dev -jar pegr.war
```

5. Go to http://localhost:8080/pegr/ and you will see the login page. Login with the default user name "labadmin" and password "labadmin". Change the password right away at the user's profile page. You may also want to add sequencing platforms and sequence indexes in the Admin page. 

For more information, please visit https://github.com/seqcode/pegr/wiki.

### Reference

[1] Danying Shao, Gretta Kellogg, Shaun Mahony, William Lai, and B. Franklin Pugh. 2020. PEGR: a management platform for ChIP-based next generation sequencing pipelines. In preparation.
