<html>
<head>
    <meta name="layout" content="help">
</head>
<body>
    <div class="row">
        <div class="col-sm-9">
        <h2>PEGR API</h2>
        To use PEGR APIs, you need a registered email and API key at PEGR. Please set up the information in your <g:link controller="user" action="profile">Profile</g:link>.
        <div class="chapter">
        <h3 id="query">Query Sample Metadata from PEGR</h3>
        <h4 id="query-sample">Query Data by Sample Properties</h4>
        <div>
            <p>If you want to query data by sample properties, e.g. ID, source and source ID, species, strain, antibody, target, format your query in a JSON dictionary as follows
            <pre>
{    
    // required, combined with API key to authenticate user.
    "userEmail": "string", 
    
    // optional, default is false
    "preferredOnly": "true/false", 
    
    // optional, parameters to specify which batch of data to return. "max" is limited to 1000.
    "max": integer, 
    "offset": integer,
    "sort": "string",
    "order": "string",
    
    // optional, property values to query.
    "id": long,
    "ids": [long, long, ...],  // if this field is provided, no other filter criteria will be applied
    "source": "string",
    "sourceId": "string",
    "species": "string",
    "strain": "string",
    "antibody": "string",    
    "target": "string",
    "assay": "string",
    "sendDataTo": "string",
    "treatment": "string"
}
            </pre>
            and send a POST request to the url
            <pre>
https://thanos.vmhost.psu.edu/pegr/api/fetchSampleData?apiKey=
            </pre> 
            Once the request is authenticated by the user's email and API key, the samples that match all the property values in the query will be returned in the following JSON format.
            <pre>
{ message: "string",
  data: [{
        "id": long,
        "source": "string",
        "sourceId": "string",
        "target": "string",
        "nTermTag": "string",
        "cTermTag": "string",
        "antibody": "string",
        "strain": "string",
        "geneticModification": "string",
        "growthMedia": "string",
        "treatments": "string",
        "assay": "string",
        "alignmentCount": integer,
        "recommend":"string",
        "histories": ["historyId", "historyId", ...],
        "experiments": [{
                "id": long,
                "runId": integer,
                "runName": "string",
                "totalReads": long,
                "adapterDimerCount": long,
                "fastqc": {"read1":"url", "read2":"url"},
                "fastq": {"read1":"url", "read2":"url"},
                "alignments": [{ "id": long,
                    "genome": "string",
                    "bam": "url",
                    "mappedReads": long,
                    "uniquelyMappedReads": long,
                    "dedupUniquelyMappedReads": long,
                    "mappedReadPct": float,
                    "uniquelyMappedPct": float,
                    "deduplicatedPct": float,
                    "avgInsertSize": integer,
                    "stdInsertSize": float,
                    "genomeCoverage": float,
                    "peakCallingParam": "string",
                    "peaks": long,
                    "singletons": long,
                    "peakPairsParam": "string",
                    "scidx": "url",
                    "cwpairFile": "url",
                    "peakPairs": long,
                    "nonPairedPeaks": long,
                    "memeFile": "url", 
                    "memeFig": "url",
                    "peHistogram": "url",
                    "fourColor": ["url", "url", ...],
                    "composite": ["url", "url", ...]
                    },
                    // other alignments
                ] 
             },              
             // other sequencing experiments
        ],
    },

    // other samples
  ]
}
            </pre>
            The max number of samples returned is limit to 1000. You can also provide <i>max, offset, sort, order</i> to specify which batch of data you want. If you set <i>preferredOnly</i> to be true, then only the results that have passed the lab quality control will be returned.</p>
    
            <p>The API can be simply called through curl</p>
            <pre>
curl -X POST -H "Content-Type: application/json" -d '{"id": 11690, "userEmail": "xxxx@psu.edu"}' https://thanos.vmhost.psu.edu/pegr/api/fetchSampleData?apiKey=XXXXXXX -o output
            </pre>

            <p>The following is an example in Python.</p>
            <pre>
import requests
url = "https://thanos.vmhost.psu.edu/pegr/api/fetchSampleData?apiKey=XXXXXXX"
data = {"userEmail": "xxxx@psu.edu",
        "preferredOnly": "true",
        "target": "CTCF",
        "max": 2,
        "offset": 2,
        "sort": "id",
        "order": "asc"
       }
r = requests.post(url, json=data)
results = r.json()
print(r.status_code)
print(results["message"])
print(len(results["data"]))
            </pre>
            <p>Here is a java example</p>
            <pre>
package apacheHppt;

import java.io.StringReader;

import javax.json.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class FetchSampleDataFromPegr {
    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {          
            // create a http post request
            HttpPost httpPost = new HttpPost("https://thanos.vmhost.psu.edu/pegr/api/fetchSampleData?apiKey=XXXXXXXXX);
            
            // construct the data in JSON format 
            JsonObject object = Json.createObjectBuilder()
                    .add("userEmail", "xxxx@psu.edu")
                    .add("id", 11690)
                    .build();

            System.out.println(object.toString());
            StringEntity params = new StringEntity(object.toString(),"UTF-8");

            // specify json type in header
            httpPost.addHeader("content-type", "application/json");

            // add data to http post request
            httpPost.setEntity(params);

            // send the http post request
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
            	// consume the results
                System.out.println(response.getStatusLine());
                
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);
                
                // parse json
                JsonReader reader = Json.createReader(new StringReader(content));
                JsonObject returnObject = reader.readObject();
                 
                System.out.println("message: " + returnObject.getString("message"));
                System.out.println("data: " + returnObject.getJsonArray("data"));
                reader.close();
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
}
            </pre>    
        </div>
        
        <h4 id="query-run">Query Data by Sequence Run</h4>
        <div>
            <p>You can also query data from PEGR by the sequence run ID. The request JSON should be in the following format</p>
            <pre>
{
    // required, combined with API key to authenticate user.
    "userEmail": "string", 
    
    // optional, default is false
    "preferredOnly": "true/false", 
    
    // required
    "runId": long
}
            </pre>
            <p>PEGR will return the results in the same format as above.</p>
            <p>The API can be simply called through curl</p>
            <pre>
curl  -X POST -H "Content-Type: application/json" -d '{"runId": 215, "userEmail": "xxxx@psu.edu"}' https://thanos.vmhost.psu.edu/pegr/api/fetchSequenceRunData?apiKey=XXXXXXX -o output
            </pre>
            <p>The following is an example in Python.</p>
            <pre>
import requests
url = "https://thanos.vmhost.psu.edu/pegr/api/fetchSequenceRunData?apiKey=XXXXXXX"
query = {"userEmail": "xxxxx@psu.edu", "runId": 212, "preferredOnly": "true"}
r = requests.post(url, json=query)
results = r.json()
            </pre>
            <p>And here is a Java example</p>
            <pre>
package apacheHppt;

import java.io.StringReader;

import javax.json.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class FetchSequenceRunDataFromPegr {
	public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // create a http post request
            HttpPost httpPost = new HttpPost("https://thanos.vmhost.psu.edu/pegr/api/fetchSequenceRunData?apiKey=XXXXXXX");
                        
            // construct the data in JSON format 
            JsonObject object = Json.createObjectBuilder()
                    .add("userEmail", "xxxx@psu.edu")
                    .add("runId", 212)
                    .build();

            System.out.println(object.toString());
            StringEntity params = new StringEntity(object.toString(),"UTF-8");

            // specify json type in header
            httpPost.addHeader("content-type", "application/json");

            // add data to http post request
            httpPost.setEntity(params);

            // send the http post request
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
            	// consume the results
                System.out.println(response.getStatusLine());
                
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);
                
                // parse json
                JsonReader reader = Json.createReader(new StringReader(content));
                JsonObject returnObject = reader.readObject();
                 
                System.out.println("message: " + returnObject.getString("message"));
                System.out.println("data: " + returnObject.getJsonArray("data"));
                reader.close();
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
}
            </pre>
        </div>
            
        <h4 id="query-project">Query Data by Project</h4>
        <div>
            <p>You can also query data from PEGR by the project ID. The request JSON should be in the following format</p>
            <pre>
{
    // required, combined with API key to authenticate user.
    "userEmail": "string", 
    
    // optional, default is false
    "preferredOnly": "true/false", 
    
    // required
    "projectId": long
}
            </pre>
            <p>PEGR will return the results in the same format as above.</p>
            <p>The API can be simply called through curl</p>
            <pre>
curl  -X POST -H "Content-Type: application/json" -d '{"projectId": 215, "userEmail": "xxxx@psu.edu"}' https://thanos.vmhost.psu.edu/pegr/api/fetchProjectData?apiKey=XXXXXXX -o output
            </pre>
            <p>The following is an example in Python.</p>
            <pre>
import requests
url = "https://thanos.vmhost.psu.edu/pegr/api/fetchProjectData?apiKey=XXXXXXX"
query = {"userEmail": "xxxxx@psu.edu", "projectId": 212, "preferredOnly": "true"}
r = requests.post(url, json=query)
results = r.json()
            </pre>
            <p>And here is a Java example</p>
            <pre>
package apacheHppt;

import java.io.StringReader;

import javax.json.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class FetchProjectDataFromPegr {
	public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // create a http post request
            HttpPost httpPost = new HttpPost("https://thanos.vmhost.psu.edu/pegr/api/fetchProjectData?apiKey=XXXXXXX");
                        
            // construct the data in JSON format 
            JsonObject object = Json.createObjectBuilder()
                    .add("userEmail", "xxxx@psu.edu")
                    .add("projectId", 212)
                    .build();

            System.out.println(object.toString());
            StringEntity params = new StringEntity(object.toString(),"UTF-8");

            // specify json type in header
            httpPost.addHeader("content-type", "application/json");

            // add data to http post request
            httpPost.setEntity(params);

            // send the http post request
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
            	// consume the results
                System.out.println(response.getStatusLine());
                
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);
                
                // parse json
                JsonReader reader = Json.createReader(new StringReader(content));
                JsonObject returnObject = reader.readObject();
                 
                System.out.println("message: " + returnObject.getString("message"));
                System.out.println("data: " + returnObject.getJsonArray("data"));
                reader.close();
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
}
            </pre>
        </div>
        </div>
        <div class="chapter">
          <h3 id="update-sample">Update Sample Metadata in PEGR</h3>
          <div>
            <p>PEGR accepts POST request at </p>
            <pre>
https://thanos.vmhost.psu.edu/pegr/api/updateSampleData?apiKey=
            </pre>
            <p>The data sent to PEGR should be in the following JSON format:</p>
            <pre>
{
    // required, combined with API key to authenticate user 
    "userEmail": "xxx@psu.edu", 
    // a list of samples to update
    "sampleList":[
        {
            "sampleID": long,
            "field": "string",
            "newValue": "string",
        },
        ......
    ]
}
            </pre>
            <p>Current accepted fields include </p>
            <ol>
              <li>geneticModification</li>
              <li>antibodyCatalog</li>
              <li>target</li>
              <li>strain</li>
              <li>growthMedia</li>
              <li>treatments (the "newValue" can take in multiple treatments, and it should be in the format of a list of strings enclosed in single quotation marks, e.g. '["treatment1", "treatment2"]')</li>
              <li>assay</li>
              <li>naturalId</li>
            </ol>
              
            <p>After a request is posted, PEGR will return the status code and a message as below</p>
            <pre>
{
    // "Success!" or error message
    "message":"string",
    
    // HTTP status code, e.g. 200 for success, 401 not authorized, 500 server side error.
    "response_code":"200"
}
            </pre>
            <p>The API can be simply called through curl</p>
            <pre>
curl  -X POST -H "Content-Type: application/json" -d '{"userEmail": "xxxx@psu.edu", "sampleList":[{"sampleID":31308,"field":"geneticModification","newValue":"Gcn5-Dtag"},{"sampleID": 31309,"field":"geneticModification","newValue":"Spt20-D-tag"}]}' https://thanos.vmhost.psu.edu/pegr/api/updateSampleData?apiKey=XXXXXX
            </pre>
            <p>Here is a Python example</p>
            <pre>
import requests

url = "https://thanos.vmhost.psu.edu/pegr/api/updateSampleData?apiKey=XXXXXX"
data = {
            "userEmail": "xxxx@psu.edu", 
            "sampleList":[
                {
                    "sampleID": 31308,
                    "field": "geneticModification",
                    "newValue": "Gcn5-Dtag"
                },{
                    "sampleID": 31309,
                    "field": "geneticModification",
                    "newValue": "Spt20-D-tag"
                }
            ]
       }
r = requests.post(url, json=data)
results = r.json()
print(r.status_code)
print(results["message"])
            </pre>
          </div>
        </div>
        <div class="chapter">
          <h3 id="sample-note">Sample's Note</h3>
          <h4 id="get-sample-note">Retrieve Sample's Note</h4>
          <div>
            <p>PEGR accepts GET request at </p>
            <pre>
https://thanos.vmhost.psu.edu/pegr/api/getSampleNote?apiKey=
            </pre>
            <p>The data sent to PEGR should be in the following JSON format:</p>
            <pre>
{
    // required, combined with API key to authenticate user 
    "userEmail": "xxx@psu.edu", 
    // sample ID
    "sampleID": integer
}
            </pre>
            <p>After a request is posted, PEGR will return the result on success</p>
            <pre>
{
    "id": integer,
    "note": "string"
}
            </pre>
            <p>It the request failed, PEGR will return the error message</p>
            <pre>
{
    "message": "string"
}
            </pre>
            <p>The API can be simply called through curl</p>
            <pre>
curl  -X POST -H "Content-Type: application/json" -d '{"userEmail": "xxxx@psu.edu", "sampleID":321}' https://thanos.vmhost.psu.edu/pegr/api/getSampleNote?apiKey=XXXXXX
            </pre>
            <p>Here is a Python example</p>
            <pre>
import requests

url = "https://thanos.vmhost.psu.edu/pegr/api/updateSampleData?apiKey=XXXXXX"
data = {
            "userEmail": "xxxx@psu.edu", 
            "sampleID": 321
       }
r = requests.post(url, json=data)
results = r.json()
print(r.status_code)
print(results)
            </pre>
          </div>
          <h4 id="update-sample-note">Update Sample's Note</h4>
          <div>
            <p>PEGR accepts POST request at </p>
            <pre>
https://thanos.vmhost.psu.edu/pegr/api/updateSampleNote?apiKey=
            </pre>
            <p>The data sent to PEGR should be in the following JSON format:</p>
            <pre>
{
    // required, combined with API key to authenticate user 
    "userEmail": "xxx@psu.edu", 
    // sample ID
    "sampleID": integer,
    // new sample's note
    "note": "string"
}
            </pre>
            <p>After a request is posted, PEGR will return the status code and a message as below</p>
            <pre>
{
    // "Success!" or error message
    "message":"string",
}
            </pre>
            <p>The API can be simply called through curl</p>
            <pre>
curl  -X POST -H "Content-Type: application/json" -d '{"userEmail": "xxxx@psu.edu", "sampleID": 321, "note: "string"}' https://thanos.vmhost.psu.edu/pegr/api/updateSampleNote?apiKey=XXXXXX
            </pre>
            <p>Here is a Python example</p>
            <pre>
import requests

url = "https://thanos.vmhost.psu.edu/pegr/api/updateSampleNote?apiKey=XXXXXX"
data = {
            "userEmail": "xxxx@psu.edu", 
            "sampleID": 321
            "note": "string"
       }
r = requests.post(url, json=data)
results = r.json()
print(r.status_code)
print(results)
            </pre>
          </div>
        </div>
        <div class="chapter">
        <h3 id="accept">Send Analysis Results to PEGR</h3>
        <div>
            <p>PEGR accepts POST request at </p>
            <pre>
https://thanos.vmhost.psu.edu/pegr/api/stats?apiKey=
            </pre>
            <p>The data sent to PEGR should be in the following JSON format:</p>
            <pre>
{
    // required, combined with API key to authenticate user 
    "userEmail": "xxx@psu.edu", 
    
    // if this is from a new alignment, "historyId" is required;
    // else either "alingmentId" or "historyId" must be included.
    "alignmentId": long,
    "historyId": "string", 
    "history_url": "string", 
    
    // required if this is from a new alignment
    // used to identify the sequence sample/experiment/alignment in PEGR 
    "run": long, 
    "sample": long, 
    "genome": "string",
    "workflowId": "string", 

    // required
    "toolCategory": "string", 
    "toolId": "string", 
    "statsToolId": "string",
    
    // optional
    "workflowStepId": "string", 

    // optional
    "statistics": [{
            "read": 1 or 2, // optional
            "statName": "statValue",
            // other statistics
        },
        // other reads
    ], 
    
    // optional
    "parameters": {
        "paramName": "paramValue", 
        // other parameters
    }, 
        
    // optional
    "datasets": [{
            "type": "string",
            "id": "string",
            "uri": "uri",
        },
        // other datasets
    ],
    
    // optional, error message if an error occurs
    "toolStderr": "string"
}
            </pre>
            <p>After a request is posted, PEGR will return the status code and a message as below</p>
            <pre>
{
    // "Success!" or error message
    "message":"string",
    
    // HTTP status code, e.g. 200 for success, 401 not authorized, 500 server side error.
    "response_code":"200"
}
            </pre>
            <p>The API can be simply called through curl</p>
            <pre>
curl  -X POST -H "Content-Type: application/json" -d '{"run": 1, "sample": 1, "genome": "sacCer3_cegr", "workflowId": "b266c9aed69b2935", "historyId": "58d3202e3", "history_url": "https://somepath/hisotry?id=58d3202e3", "toolCategory": "output_tagPileup", "statsToolId": "tag_pileup_frequency_output_stats", "workflowStepId": "10a140b06", "userEmail": "xxxx@psu.edu", "statistics": [{}, {}], "parameters": {}, "toolId": "sometool", "datasets": [{"type": "tabular", "id": "e4f3485fe716bd91", "uri": "someuri"}, {"type": "tabular", "id": "55f6655ba0a1f0ca", "uri": "someuri"}]}' https://thanos.vmhost.psu.edu/pegr/api/stats?apiKey=XXXXXX
            </pre>
            <p>Here is a Python example</p>
            <pre>
import requests

url = "https://thanos.vmhost.psu.edu/pegr/api/stats?apiKey=XXXXXX"
data = {"userEmail": "xxxx@psu.edu", 
        "run": 1, 
        "sample": 1, 
        "genome": "sacCer3_cegr", 
        "workflowId": "b266c9aed69b2935", 
        "historyId": "58d3202e3", 
        "history_url": "https://somepath/history?id=58d3202e3", 
        "toolCategory": "output_tagPileup", 
        "statsToolId": "tag_pileup_frequency_output_stats",
        "workflowStepId": "10a140b06", 
        "statistics": [{}, {}], 
        "parameters": {}, 
        "toolId": "toolshed.g2.bx.psu.edu/repos/iuc/tag_pileup_frequency/tag_pileup_frequency/1.0.0", 
        "datasets": [{"type": "tabular", 
                      "id": "e4f3485fe716bd91", 
                      "uri": "https://xxx.xxx.xxx"}, 
                     {"type": "tabular", 
                      "id": "4f409d81b442bdb9", 
                      "uri": "https://xxx.xxx.xxx"}], 
       }
r = requests.post(url, json=data)
results = r.json()
print(r.status_code)
print(results["message"])
            </pre>
            <p>And here is a Java example</p>
            <pre>
package apacheHppt;

import java.io.StringReader;
import javax.json.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class PostDataToPegr {
	public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {            
            // create a http post request
            HttpPost httpPost = new HttpPost("https://thanos.vmhost.psu.edu/pegr/api/stats?apiKey=XXXXXXX");
            
            // construct the data in JSON format 
            JsonObject object = Json.createObjectBuilder()
            		.add("userEmail", "xxxx@psu.edu")
            		.add("run", 1)
            		.add("sample", 1)
            		.add("genome", "sacCer3_cegr")
            		.add("workflowId", "b266c9aed69b2935")
            		.add("historyId", "abcd1234")
                    .add("history_url", "https://somepath/history?id=abcd1234")
            		.add("toolCategory", "output_tagPileup")
                    .add("statsToolId", "tag_pileup_frequency_output_stats")
            		.add("workflowStepId", "abcdefg")
            		.add("toolId", "toolXXXX")
            		.add("datasets", Json.createArrayBuilder()
            			.add(Json.createObjectBuilder()
            				.add("type", "tabular")
            				.add("id", "id1234")
            				.add("uri", "https://xxx.xxx.xxx")))
            		.build();

            System.out.println(object.toString());
            StringEntity params = new StringEntity(object.toString(),"UTF-8");
            		
            // specify json type in header
            httpPost.addHeader("content-type", "application/json");
            
            // add data to http post request
            httpPost.setEntity(params);
            
            // send the http post request
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
            	// consume the results
                System.out.println(response.getStatusLine());
                
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);
                System.out.println(content);
                
                // parse json
                JsonReader reader = Json.createReader(new StringReader(content));
                JsonObject returnObject = reader.readObject();
                 
                System.out.println("message: " + returnObject.getString("message"));
                reader.close();
            } finally {
                response.close();
            }
            
        } finally {
            httpclient.close();
        }
    }
}

            </pre>
        </div>
        </div>
        <div class="chapter">
        <h3 id="del-samples">Delete Samples (Admin Only)</h3>
        <div>
            <p>Only Admins are allowed to use this API. To delete samples, format your query in a JSON dictionary as follows
            <pre>
{    
    // required, combined with API key to authenticate user.
    "userEmail": "string", 
    
    // required
    "sampleIds": [123, 456]
}
            </pre>
            and send a POST request to the url
            <pre>
https://thanos.vmhost.psu.edu/pegr/api/deleteSampleList?apiKey=
            </pre> 
    
            <p>The API can be simply called through curl</p>
            <pre>
curl -X POST -H "Content-Type: application/json" -d '{"sampleIds": [123, 456], "userEmail": "xxxx@psu.edu"}' https://thanos.vmhost.psu.edu/pegr/api/deleteSampleList?apiKey=XXXXXXX -o output
            </pre>

            <p>The following is an example in Python.</p>
            <pre>
import requests
url = "https://thanos.vmhost.psu.edu/pegr/api/deleteSampleList?apiKey=XXXXXXX"
data = {"userEmail": "xxxx@psu.edu",
        "sampleIds": [123, 456]
       }
r = requests.post(url, json=data)
results = r.json()
print(r.status_code)
print(results["message"])
            </pre>
 
        </div>
        </div>
        <div class="chapter">
        <h3 id="del-histories">Delete Analysis Histories (Admin Only)</h3>
        <div>
            <p>Only Admins are allowed to use this API. To delete analysis histories, format your query in a JSON dictionary as follows
            <pre>
{    
    // required, combined with API key to authenticate user.
    "userEmail": "string", 
    
    // required
    "historyIds": ["abc123", "def456"]
}
            </pre>
            and send a POST request to the url
            <pre>
https://thanos.vmhost.psu.edu/pegr/api/deleteAnalysisHistories?apiKey=
            </pre> 
    
            <p>The API can be simply called through curl</p>
            <pre>
curl -X POST -H "Content-Type: application/json" -d '{"historyIds": ["abc123", "def456"], "userEmail": "xxxx@psu.edu"}' https://thanos.vmhost.psu.edu/pegr/api/deleteAnalysisHistories?apiKey=XXXXXXX -o output
            </pre>

            <p>The following is an example in Python.</p>
            <pre>
import requests
url = "https://thanos.vmhost.psu.edu/pegr/api/deleteAnalysisHistories?apiKey=XXXXXXX"
data = {"userEmail": "xxxx@psu.edu",
        "historyIds": ["abc123", "def456"]
       }
r = requests.post(url, json=data)
results = r.json()
print(r.status_code)
print(results["message"])
            </pre>
 
        </div>
        </div>
        <div class="chapter">
        <h3 id="fetch-run-info">Fetch Sequence Run Info Files</h3>
        <div>
            <p>To fetch the sequence run info files, format your query in a JSON dictionary as follows
            <pre>
{    
    // required, combined with API key to authenticate user.
    "userEmail": "string", 
    
    // either runId or directory needs to be provided
    "runId": 518,
    "directory": "xxxxxx", 
    
    // required
    "remoteRoot": "/some/path/"
}
            </pre>
            and send a POST request to the url
            <pre>
https://thanos.vmhost.psu.edu/pegr/api/fetchSequenceRunInfo?apiKey=
            </pre> 
    
            <p>The API can be simply called through curl</p>
            <pre>
curl -X POST -H "Content-Type: application/json" -d '{"runId": 518, "directory": "xxxxxx", "remoteRoot":"/some/path/", "userEmail": "xxx@xxx.xxx"}' --output FILENAME https://thanos.vmhost.psu.edu/pegr/api/fetchSequenceRunInfo?apiKey=XXXXXXX
            </pre>

            <p>The following is an example in Python.</p>
            <pre>
import requests
url = "https://thanos.vmhost.psu.edu/pegr/api/fetchSequenceRunInfo?apiKey=XXXXXXX"
data = {
        "runId": 518, 
        "directory": "xxxxxx",
        "remoteRoot":"/some/path/", 
        "userEmail": "xxx@xxx.xxx"
       }
r = requests.post(url, json=data)
f = open('file.tar.gz', 'wb')
for chunk in r.iter_content(chunk_size=512 * 1024): 
    if chunk: # filter out keep-alive new chunks
        f.write(chunk)
f.close()
            </pre>
 
        </div>
        </div>
        <div class="chapter">
        <h3 id="get-run-status">Get Sequence Run Status</h3>
        <div>
            <p>To get a sequence run's status, format your query in a JSON dictionary as follows
            <pre>
{    
    // required, combined with API key to authenticate user.
    "userEmail": "string", 
    
    // either runId or directory needs to be provided
    "runId": 518,
    "directory": "xxxxxx", 
}
            </pre>
            and send a POST request to the url
            <pre>
https://thanos.vmhost.psu.edu/pegr/api/getSequenceRunStatus?apiKey=
            </pre> 
    
            <p>The API can be simply called through curl</p>
            <pre>
curl -X POST -H "Content-Type: application/json" -d '{"runId": 518, "directory": "xxxxxx", "userEmail": "xxx@xxx.xxx"}' https://thanos.vmhost.psu.edu/pegr/api/getSequenceRunStatus?apiKey=XXXXXXX
            </pre>

            <p>The following is an example in Python.</p>
            <pre>
import requests
url = "https://thanos.vmhost.psu.edu/pegr/api/getSequenceRunStatus?apiKey=XXXXXXX"
data = {
        "runId": 518, 
        "directory": "xxxxxx",
        "userEmail": "xxx@xxx.xxx"
       }
r = requests.post(url, json=data)
results = r.json()
print(results["status"])
            </pre>
        </div>
        </div>
        <div class="chapter">
        <h3 id="set-run-status">Set Sequence Run Status (Admin Only)</h3>
        <div>
            <p>To set a sequence run's status, format your query in a JSON dictionary as follows
            <pre>
{    
    // required, combined with API key to authenticate user.
    "userEmail": "string", 
    
    // either runId or directory needs to be provided
    "runId": 518,
    "directory": "xxxxxx", 
    
    // required
    "status": "string"
}
            </pre>
            and send a POST request to the url
            <pre>
https://thanos.vmhost.psu.edu/pegr/api/setSequenceRunStatus?apiKey=
            </pre> 
    
            <p>The API can be simply called through curl</p>
            <pre>
curl -X POST -H "Content-Type: application/json" -d '{"runId": 518, "directory": "xxxxxx", "userEmail": "xxx@xxx.xxx", "status": "COMPLETED"}' https://thanos.vmhost.psu.edu/pegr/api/setSequenceRunStatus?apiKey=XXXXXXX
            </pre>

            <p>The following is an example in Python.</p>
            <pre>
import requests
url = "https://thanos.vmhost.psu.edu/pegr/api/setSequenceRunStatus?apiKey=XXXXXXX"
data = {
        "runId": 518, 
        "directory": "xxxxxx", 
        "userEmail": "xxx@xxx.xxx",
        "status": "COMPLETED"
       }
r = requests.post(url, json=data)
results = r.json()
print(results["message"])
            </pre>
        </div>
        </div>
        <div class="chapter">
        <h3 id="fetch-run-summary">Fetch Sequence Run Summary Information</h3>
        <div>
            <p>To get a sequence run's summary information, format your query in a JSON dictionary as follows
            <pre>
{    
    // required, combined with API key to authenticate user.
    "userEmail": "string", 
    
    // either runId or directory needs to be provided
    "runId": 518,
    "directory": "xxxxxx", 
}
            </pre>
            and send a POST request to the url
            <pre>
https://thanos.vmhost.psu.edu/pegr/api/fetchSequenceRunSummary?apiKey=
            </pre> 
    
            <p>The API can be simply called through curl</p>
            <pre>
curl -X POST -H "Content-Type: application/json" -d '{"runId": 518, "directory": "xxxxxx", "userEmail": "xxx@xxx.xxx"}' https://thanos.vmhost.psu.edu/pegr/api/fetchSequenceRunSummary?apiKey=XXXXXXX
            </pre>

            <p>The following is an example in Python.</p>
            <pre>
import requests
url = "https://thanos.vmhost.psu.edu/pegr/api/fetchSequenceRunSummary?apiKey=XXXXXXX"
data = {
        "runId": 518, 
        "directory": "xxxxxx",
        "userEmail": "xxx@xxx.xxx"
       }
r = requests.post(url, json=data)
results = r.json()
            </pre>
        </div>
        </div>
        <div class="chapter">
        <h3 id="update-run-summary">Update Sequence Run Summary Information (Admin Only)</h3>
        <div>
            <p>To update a sequence run's summary information, format your query in a JSON dictionary as follows
            <pre>
{    
    // required, combined with API key to authenticate user.
    "userEmail": "string", 
    
    // required
    "runId": 518,
    
    // the following fields are optional
    "platformName": "string",
    "date": "2021-10-10",
    "runName": "string",
    "directoryName": "string",
    "fcId": "string",
    "lane": 1,
    "note": "string",
    "libraryVolume": 1.2,
    "libraryStock": 1.2,
    "libraryStdDev": 0.3,
    "pctLibraryStdDev": 0.1,
    "cycles": "string",
    "srOrPe": "string",
    "seqCtrl": "string",
    "pcrCycles": 1,
    "qubitConc": 1.2,
    "qPcrConc": 1.2,
    "libraryLoadedPm": 1.2,
    "phiXLoaded": 1.2,
    "libraryLoadedFmol": 1.2,
    "clusterNum": 1.2,
    "readPf": 1.2,
    "pctPf": 1.2,
    "pctQ30": 1.2,
    "qidx": 1.2,
    "totalReads": 1.2,
    "unmatchedIndices": 1.2,
    "pctUnmatchedIndices": 1.2,
    "pctAlignedToPhiX": 1.2,
}
            </pre>
            and send a POST request to the url
            <pre>
https://thanos.vmhost.psu.edu/pegr/api/updateSequenceRunSummary?apiKey=
            </pre> 
    
            <p>The API can be simply called through curl</p>
            <pre>
curl -X POST -H "Content-Type: application/json" -d '{"userEmail": "xxx@xxx.xxx", "runId": 518, "directoryName": "xxxxxx", "fcId": "xxx"}' https://thanos.vmhost.psu.edu/pegr/api/updateSequenceRunSummary?apiKey=XXXXXXX
            </pre>

            <p>The following is an example in Python.</p>
            <pre>
import requests
url = "https://thanos.vmhost.psu.edu/pegr/api/updateSequenceRunSummary?apiKey=XXXXXXX"
data = {"userEmail": "xxx@xxx.xxx",
        "runId": 518, 
        "directoryName": "xxxxxx", 
        "fcId": "xxx"
       }
r = requests.post(url, json=data)
results = r.json()
print(results["message"])
            </pre>
        </div>
        </div>
        </div>
        <nav class="col-sm-3">
            <h4>Menu</h4>
            <ul id="menu" class="nav nav-pills nav-stacked" data-spy="affix" data-offset-top="205"></ul>
        </nav>  
    </div>
    <script>
        $(function(){
            $(".help-api").addClass("active");
            
            $(".chapter").each(function(){
                var h3 = $(this).find("h3");
                var title = $(h3).text();
                var id = $(h3).attr("id");
                var cat = "<li><h5><a href='#" + id + "'>"+ title +"</a></h5><ul>";
                $(this).find("h4").each(function(i, sub){
                    var subTitle = $(sub).text();
                    var subId = $(sub).attr("id");
                    cat += "<li><a href='#" + subId + "'>"+ subTitle +"</a></li>"; 
                });
                
                cat += "</ul></li>";
                $("#menu").append(cat);
            })
        })
        
    </script>
</body>
</html>
