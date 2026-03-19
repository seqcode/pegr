import requests
import pandas as pd

# This script updates the run type for a list of run IDs in the PEGR database.
# The run IDs and their corresponding correct run types should be provided in 
# a CSV file with columns "run_id" and "run_type". "run_id" is the ID of the 
# sequence run and "run_type" can be either "SR" or "PE". The script will read the CSV 
# file, iterate through each row, and send a POST request to the PEGR API to update 
# the run type for each run ID. 
# 
# Make sure to replace the placeholders for FILEPATH, EMAIL, API_KEY, and HOST
FILEPATH = "your_file_path_here.csv"
EMAIL = "your_email_here"
API_KEY = "your_api_key_here"
HOST = "https://vesta.cac.cornell.edu/pegr/api"

# Construct the API endpoint URL for updating the run type
url = f"{HOST}/updateSequenceRunSummary?apiKey={API_KEY}"

# load the csv file with run ID and correct run type
df = pd.read_csv(FILEPATH)

# iterate the df and update the run type for each run ID
for index, row in df.iterrows():
    run_id = row["run_id"]
    run_type = row["run_type"]
    
    data = {'userEmail': EMAIL, 'runId': run_id, 'srOrPe': run_type}
    
    r = requests.post(url, json=data)
    
    # Check the response status code to ensure the update was successful
    if r.status_code != 200:
        print(f"Failed to update run ID {run_id}. Status code: {r.status_code}, Response: {r.text}")
    else:
        print(f"Run ID {run_id} updated to run type {run_type}.")
