#!/usr/bin/python
import sys
import mysql.connector
import json

def getOrCreate(mycursor, table, new):
    # create the new treatment if it does not exist yet
    sql = "select id from %s where name = '%s'" % (table, new)
    mycursor.execute(sql)
    results = mycursor.fetchall()
    if len(results) == 0:
        sql = "insert into %s (version, name) values(0, '%s')" % (table, new)
        mycursor.execute(sql)
        sql = "select id from %s where name = '%s'" % (table, new)
        mycursor.execute(sql)
        results = mycursor.fetchall()
    return results[0][0]
        
def updateTreatment(mycursor, sample_id, old, new):
    # check if the sample_treatment exists
    sql = "SELECT st.id from sample_treatments st join sample s on st.sample_id = s.id join cell_source_treatment t on t.id = st.treatment_id where s.id = %s and t.name = '%s'" % (sample_id, old)
    mycursor.execute(sql)
    results = mycursor.fetchall()
    if len(results) == 0:
        print("No sample with id = %s and treatment = %s is found!" % (sample_id, old))
        raise
    st_id = results[0][0]
    treatment_id = getOrCreate(mycursor, "cell_source_treatment", new)

    sql = "update sample_treatments set treatment_id = %s where id = %s" % (treatment_id, st_id)
    mycursor.execute(sql)

if __name__ == "__main__":
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
            
    if data["field"] == "treatment":
        for entry in data["data"]:
            try:    
                updateTreatment(mycursor, entry["sample_id"], entry["old"], entry["new"])
            except:
                print("Error updating sample %s. Job aborted. All changes reverted." % entry["sample_id"])
                sys.exit()
    mydb.commit()    
    print("Success!")
