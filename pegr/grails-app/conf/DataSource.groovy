dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
	dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    // singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "none" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = ""
			username=""
			password=""
        }
    }
    test {
        dataSource {
            dbCreate = "none"
            url = ""
			username=""
			password=""
        }
    }
    production {
        dataSource {
            dbCreate = "none"
			// use JNDI datasource with Tomcat
            // jndiName="java:comp/env/pegrDatasource"
            
            url = ""
			username=""
			password=""
        }
    }
}
