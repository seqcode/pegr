<html>
    <head>
        <meta name="layout" content="help">
        <style>
            img.guide-image {
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
                margin-bottom: 18px;
                max-width: 95%;
                height: auto;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
          <div class="col-sm-9">
            <div id="project-role">
                <h3>Project Role</h3>
                <table class="table-bordered">
                    <thead>
                        <th></th>
                        <th>Owner</th>
                        <th>Participant</th>
                        <th>Guest</th>
                        <th>None</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td>View</td>
                            <td>&#10003;</td>
                            <td>&#10003;</td>
                            <td>&#10003;</td>
                            <td></td>
                        </tr>                
                        <tr>
                            <td>Add/remove/edit samples</td>
                            <td>&#10003;</td>
                            <td>&#10003;</td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Edit project name, description, funding</td>
                            <td>&#10003;</td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Add/remove user, edit role</td>
                            <td>&#10003;</td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div id="barcode-inventory">
                <h3>Inventory Tracking</h3>
                <p>The inventory has many types, including antibody, chemical, enzyme, reagent, cell stock, biosample, sample pool and others. In PEGR, barcodes are used to track the inventory. Note that the barcodes have to be UNIQUE for each item type. To use the barcode tracking system in PEGR, you need an android phone or tablet with the "Barcode Scanner" app (based on zxing) installed from the Play Store. From PEGR webpages, click on the <span class="glyphicon glyphicon-qrcode"></span> symbol, the barcode sanner will open up automatically. Then scan the barcode on the inventory, and then you will be taken back to the original PEGR webpage with the barcode filled.</p>
                <p>To create a new inventory item in PEGR, first go to the “Inventory” tab.</p>
                <img class="guide-image" src="/pegr/assets/barcode_inventory.png" alt="Inventory" style="width:1050px">
                <p>If the item does not have a barcode yet, you can create a barcode for it in PEGR. Select “Type” and the click “Generate Barcode”. You will see a page showing the item type and barcode. </p>
                <img class="guide-image" src="/pegr/assets/barcode_item.png" alt="Item" style="width:400px">
                <p>Click “Create” and there is a page for you to fill in more information, e.g. name, location and notes.</p>
                <img class="guide-image" src="/pegr/assets/barcode_item_create.png" alt="Create Item" style="width:350px">
                <p>After filling in the information, hit the button “Save” and you will be taken to the page showing the item.</p>
                <img class="guide-image" src="/pegr/assets/barcode_item_show.png" alt="Create Item" style="width:550px">
                <p>You can upload images and print the barcode here. </p>
                <p>If the item already has an attached barcode, you can scan the barcode into PEGR on your mobile device. Select “Type” and then click the barcode icon, you will be taken to the barcode scanner app. Scan the barcode and you will be taken back to the PEGR page. Click the “Search/Create” button, and you will see the page to add information (e. g. name, location and notes) to the item.</p>
              </div>
              <div id="barcode-protocol">
                <h3>Protocol and Protocol Group</h3>
                <p>A protocol is the standard for one experiment step. For example, some chemicals are mixed to create an end product, a cell stock is processed to another state, or a batch of biosamples are pooled. To create a protocol, go to the tab “Protocols” and then “My Protocols”. Click the button “New”.</p>
                <img class="guide-image" src="/pegr/assets/barcode_protocol.png" alt="Protocol" style="width:1050px">
                <p>On the “Create Protocol” page, you can fill in the information about the protocol. The share item types and end product types could be certain types of chemicals and reagents, and you want to barcode and track those items during the experiment. The traced sample may change from one state to another state during an experiment step. Sometimes the sample is transferred from one container to another container, or a partial portion of the sample goes to the next state. In these situations, you might want to have different barcodes for different state of the sample, and you can specify the “Start State” and “End State” of the traced sample. If a pool is created in the step, you can specify “Create Pool” type. And if a pool is processed and transforms to another state, you can specify both “Import Pool” and “Create Pool” types. The “URL” field allows you to link to another webpage, and you can also upload a PDF file of the protocol.</p>
                <img class="guide-image" src="/pegr/assets/barcode_protocol_create.png" alt="CreateProtocol" style="width:500px">
                <p>Users can see the protocols that they created in the “My Protocols” tab. Protocols that are “approved” (often by the lab admin) will also be listed in the “Lab Protocols” tab. In addition, lab admin can view all the protocols in the “All Protocols” tab. </p>
                <p>There is also a tab named “lab Protocol Groups”. A protocol group is an ordered list of protocols that happen in sequence. In practice, some protocols often go together and the protocol group is a convenient tool of creating experiment records. </p>
                <img class="guide-image" src="/pegr/assets/barcode_protocol_group.png" alt="Protocol Group" style="width:550px">
                <p>The protocol group is created in the Admin dashboard. Go to “Admin” tab and find “Protocol Group” in the “Protocol” section.</p>
                <img class="guide-image" src="/pegr/assets/barcode_protocol_group_create.png" alt="Create Protocol Group" style="width:1050px">
            </div>      
            <div id="barcode-experiment">
                <h3>Experiment Tracking</h3>
                <p>An experiment record is a record of one or more steps of experiment. It records the protocols followed and the barcoded items (chemicals, reagents, samples, pools) used during the experiment.</p>
                <p>To create an experiment record, go to the “Experiment” tab and click the “New” button at the top, and you will be taken to the following page. Here you can either select a protocol group or a list of protocols. You can select one or more projects here, and then on the view page of the experiment record, you can further assign each sample to a project.</p>
                <img class="guide-image" src="/pegr/assets/barcode_experiment_create.png" alt="Create Experiment" style="width:350px">
                <p>Once the experiment record is saved, you will be taken to the view page of the record. The left panel lists the protocols as you defined before and the right panel is for you to add samples to the experimennt. Note that on a smaller screen, e.g. on a mobile device, the right panel will be stacked below the left panel.</p>
                <img class="guide-image" src="/pegr/assets/barcode_experiment_summary.png" alt="Create Experiment">
                <p>You will notice that there is a "Start" button next to the first protocol on the left panel. That will take you to the details of that experiment step. But before clicking the “Start” button, you need to add all the samples in the experiment and you will be reminded by a warning message. There are two ways to add samples. The first is to  need to add all the samples before starting the experiment. If not, clicking click the <span class="glyphicon glyphicon-qrcode"></span> symbol and scan the sample's barcode using your mobile device. The other is to click the <span class="glyphicon glyphicon-list-alt"></span> symbol and search by typing the related information, e.g. sample type, name, barcode or location. Once you find your sample in PEGR's database through either method, you will be asked to confirm if you want to "Import Sample(s)" or "Split and Add Sample(s)". The second option applies if you only want to take part of the existing sample for processing and the rest part will be kept for future use.</p>
                <p>Once you have added all the samples to the experiment, click the “Start” button next to the first protocol, and you will see the following page.</p>
                <img class="guide-image" src="/pegr/assets/barcode_experiment_edit.png" alt="Edit Experiment">
                <p>If the protocol has defined types for the shared items, end product, samples' start and end state, and imported or created pool, each of these types will be expanded as a table for you to track the respective items. For example, if the protocol requires a shared item with the type "YPD Media", there will be a "Shared Items" table with a row for the type "YPD Media". You then scan the corresponding item to that row by following the "+" sign. </p>
            </div>
            <div id="sample-submission">
                <h3>Sample Submission</h3>
                <p>To submit samples to a sequencing run, go the "Sequencing Runs" tab and find the "Sample Submission" section. </p>
                <img class="guide-image" src="/pegr/assets/sample_submission.png" alt="Sample Submission">
                <p>There are two ways to submit samples to a sequencing run (see the two links ). One way is to upload an excel file. A template sample submission form is provided <a href="https://github.com/seqcode/pegr/tree/master/sample_files">here</a>. Another way is to follow the "Create Wizard" button and create a sequencing run. </p>
                <img class="guide-image" src="/pegr/assets/sequence_run_create.png" alt="Create Sequence Run" style="width:350px">
                <p>When a sequencing run has just been created, it's in the "PREP" status. And you can edit the information about the sequencinbg run. To add samples to the sequencing run, you can click the "Add Master Pool" button and import samples from that pool. Or you can click the "Add Sample" button and import samples by ID. </p>                
                <img class="guide-image" src="/pegr/assets/sequence_run_edit.png" alt="Edit Sequence Run">
            </div>
            <div id="pipeline-status">
                <h3>Pipeline Status</h3>
                <p>Each step in the pipeline is labeled with one of the following status </p>
                <ul>
                    <li><span class="label label-success"> </span> Success</li>
                    <li><span class="label label-danger"> </span> Error message</li>
                    <li><span class="label label-warning"> </span> Permission denied</li>
                    <li><span class="label label-info"> </span> Empty dataset (e.g. no peaks, no peak-pairs, no motifs, etc.)</li>
                    <li><span class="label label-default"> </span> No data received.</li>
                </ul>
                <p>The status is determined by the messages received from the external bioinformatics workflow and preliminary screening of the data.</p>
                <ol>
                    <li>The error messages from the external bioinformatics workflow are generally labed as <span class="label label-danger"> </span> Error message, except when the message contains "Permission denied", in which case , the step is labeled as <span class="label label-warning"> </span> Permission denied.</li>
                    <li>Data received from the following steps are tested for <span class="label label-info"> </span>Empty dataset
                        <ul>
                            <li>bedtoolsIntersect: no peaks</li>
                            <li>cwpair2: no peak pairs</li>
                            <li>repeatMasker: no sequence in fasta</li>
                            <li>meme: no motif</li>
                        </ul>
                    </li>
                    <li>If the motif count from fimo or tagPileup does not match the motif count in meme, the fimo or tagPileup step will be labeled with error.
                    </li>
                </ol>
                <p>You can learn more about a step with data received by clicking the colored label. A snippet will pop over, including the step's name, error message and notes. An admin can change the step's status by clicking the <span class="glyphicon glyphicon-pencil"></span> inside the snippet and following the instructions.</p>
            </div>
            <div id="search-samples">
                <h3>Search Samples</h3>
                <p>Under the "Samples" tab, you can view all samples and search sampels by ID, species, strain, antibody, etc. You can click an individual sample and view the details for that sample. In adddition, you can select multiple samples, and view the selected sample together and compare.</p>
                <img class="guide-image" src="/pegr/assets/search_sample.png" alt="Search Samples">
            </div>
          </div>
          <nav class="col-sm-3">
            <h4>Menu</h4>
            <ul id="menu" data-spy="affix" data-offset-top="205">
              <li><a href="#project-role">Project Role</a></li>
              <li><a href="#barcode-inventory">Inventory Tracking</a></li>
              <li><a href="#barcode-protocol">Protocol and Protocol Group</a></li>
              <li><a href="#barcode-experiment">Experiment Tracking</a></li>
              <li><a href="#sample-submission">Sample Submission</a></li>
              <li><a href="#pipeline-status">Pipeline Status</a></li>
              <li><a href="#search-samples">Search Samples</a></li>
              <li></li>
              <li></li>
            </ul>
          </nav>
        </div>
         <script>
            $(".help-experiments").addClass("active");
         </script>
    </body>
</html>