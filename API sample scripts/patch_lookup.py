#!/usr/bin/python
import sys
import mysql.connector
import json

if __name__ == "__main__":
    """
    update the field "name" in lookup tables. To run the script, 
    python patch_lookup.py <filename>
    """
    try:
        with open(sys.argv[1]) as patch_file:
            data = json.load(patch_file)
    except:
        print("Error openning the file!")
        sys.exit()
    
    try: 
        mydb = mysql.connector.connect(
          host=data["mysql"]["host"],
          user=data["mysql"]["user"],
          passwd=data["mysql"]["passwd"],
          database=data["mysql"]["database"]
        )
        mycursor = mydb.cursor()
    except:
        print("Error connecting to the database!")
        sys.exit()
    
    for table, changes in data["changes"].items():     
        for old, new in changes.items():
            # continue if old and new are the same
            if old == new:
                continue
            try: 
                sql = "UPDATE %s SET name = '%s' WHERE name = '%s'" % (table, new, old)
                mycursor.execute(sql)
            except: 
                try:
                    sql = "select id from %s where name = '%s'" % (table, new)
                    mycursor.execute(sql)
                    newId = mycursor.fetchone()[0]

                    sql = "select id from %s where name = '%s'" % (table, old)
                    mycursor.execute(sql)
                    oldId = mycursor.fetchone()[0]

                    sql = "select kcu.table_name, kcu.column_name from information_schema.referential_constraints rc inner join information_schema.key_column_usage kcu on rc.constraint_name = kcu.constraint_name and rc.constraint_schema = kcu.constraint_schema where kcu.constraint_schema = 'pegr' AND kcu.REFERENCED_TABLE_NAME = '%s'" % table
                    mycursor.execute(sql)
                    affectedTables = mycursor.fetchall()

                    for affectedTableName, columnName in affectedTables:
                        try:                     
                            sql = "update %s set %s = %d where %s = %d" % (affectedTableName, columnName, newId, columnName, oldId)
                            mycursor.execute(sql)
                        except e:
                            sql = "delete from %s where %s = %d" % (affectedTableName, columnName, oldId)
                            mycursor.execute(sql)
   
                    sql = "delete from %s where name = '%s'" % (table, old)
                    mycursor.execute(sql)
                except e:
                    print(e)
                    print("Error updating table %s, old name %s and new name %s. Job aborted. All changes reverted." % (table, old, new))
                    sys.exit()

    mydb.commit()    
    print("Success!")