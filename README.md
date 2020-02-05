### Introduction
Platform for Eukaryotic Genomic Regulation (PEGR) is a web service platform that logs metadata for samples and sequencing experiment, manages the data processing workflows, and provides reporting and visualization. PEGR links together people, samples, protocols, sequencer and bioinformatics computation [1].

### Prerequisites

A tested stack of versions is listed below.

1. OpenJDK 1.8.0_212
2. Groovy 2.4.17
3. Grails 3.3.10
4. MariaDB 10.4.6
5. xlsx2csv


### Quick start

1. clone the codes.

```
$ git clone https://github.com/seqcode/pegr.git 
```

2. import the baseline database and set up user previleges to access the database.


3. edit the configurations.


4. run PEGR.

```
$ grails run-app
```

5. go to http://localhost:8080/pegr/and you will see the login page. Login withthe default user name and password. Change the password right away at the user's profile page.

For more information, please visit https://github.com/seqcode/pegr/wiki.

### Reference

[1] Danying Shao, Gretta Kellogg, Shaun Mahony, William Lai, and B. Franklin Pugh. 2020. PEGR: a management platform for ChIP-based next generation sequencing pipelines. In preparation.
