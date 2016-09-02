<html>
<head>
    <meta name="layout" content="main">
</head>
<body>
    <div class="container">
        <h2>PEGR API</h2>
        <h3>Query Data from PEGR</h3>
        <h4>Query Data by Sample properties</h4>
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
    
    // optional, property values to query. "AND".
    "id": long,
    "source": "string",
    "sourceId": "string",
    "species": "string",
    "strain": "string",
    "antibody": "string",    
    "target": "string"
}
        </pre>
        and send a GET request to the url
        <pre>
http://francline.vmhost.psu.edu:8080/pegr/api/fetchSampleData?apiKey=
        </pre> 
        Once the request is authenticated by the user's email and API key, the samples that match all the property values in the query will be returned in the following JSON format.
        <pre>
{ message: "string",
  data: [{
        "id": integer,
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
        "experiments": [{
                "id": integer,
                "runId": integer,
                "oldRunNum": integer,
                "totalReads": long,
                "adapterDimerCount": long,
                "fastqc": {"read1":"url", "read2":"url"},
                "fastq": {"read1":"url", "read2":"url"},
                "alignments": [{ "id": integer,
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
    
        <p>The following is an example in Python.</p>
        <pre>
import requests
url = "http://francline.vmhost.psu.edu:8080/pegr/api/fetchSampleData?apiKey=XXXXXXX"
data = {"userEmail": "xxxx@psu.edu",
        "preferredOnly": "true",
        "target": "CTCF",
        "max": 2,
        "offset": 2,
        "sort": "id",
        "order": "asc"
       }
r = requests.get(url, params=data)
results = r.json()
print(r.status_code)
print(results["message"])
print(len(results["data"]))
        </pre>
    
        <h4>Query Data by Sequence Run</h4>
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
        <p>The following is an example in Python.</p>
        <pre>
import requests
url = "http://francline.vmhost.psu.edu:8080/pegr/api/fetchSequenceRunData?apiKey=XXXXXXX"
query = {"userEmail": "xxxxx@psu.edu", "runId": 212, "preferredOnly": "true"}
r = requests.get(url, params=query)
results = r.json()
        </pre>


    </div>
</body>
</html>