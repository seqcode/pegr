### Introduction

Platform for Epigenomic and Genomic Research (PEGR) is a web service platform that logs metadata for samples and sequencing experiment, manages the data processing workflows, and provides reporting and visualization. PEGR links together people, samples, protocols, sequencer and bioinformatics computation [1].

### Prerequisites

A tested stack of versions is listed below.

1. OpenJDK 1.8.0_212
2. Groovy 2.4.17
3. Grails 3.3.10
4. MariaDB 10.4.6
5. xlsx2csv 0.7.2

### Quick start

1. Clone the codes.

```
git clone https://github.com/seqcode/pegr.git 
cd pegr
```

2. Set up database.
   First, create an empty database with utf8 coding and assign privileges.

```
CREATE DATABASE <DB_NAME> CHARACTER SET utf8 COLLATE utf8_general_ci;
GRANT ALL PRIVILEGES ON <DB_NAME>.* TO '<USER_NAME>'@'localhost' with grant option;
FLUSH PRIVILEGES;
```
   Then import the baseline database.
   
```
mysql -u <USER_NAME> -p <DB_NAME> < database/pegr_baseline.sql 
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

3. Run PEGR.

```
cd pegr
$ grails run-app
```

4. go to http://localhost:8080/pegr/ and you will see the login page. Login with the default user name "labadmin" and password "labadmin". Change the password right away at the user's profile page.

Now you are ready to use PEGR. For more information, please visit https://github.com/seqcode/pegr/wiki.

### Reference

[1] Danying Shao, Gretta Kellogg, Shaun Mahony, William Lai, and B. Franklin Pugh. 2020. PEGR: a management platform for ChIP-based next generation sequencing pipelines. In preparation.
