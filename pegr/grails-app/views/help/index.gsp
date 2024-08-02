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
            <div id="what">
              <h3>What is PEGR</h3>  
              <p>Platform for Epigenomic and Genomic Research (PEGR) is a web service platform that logs metadata for samples and sequencing experiment, manages the data processing workflows, and provides reporting and visualization. PEGR links together people, samples, protocols, sequencer and bioinformatics computation. The development home of PEGR is at <a href="https://github.com/seqcode/pegr">https://github.com/seqcode/pegr</a>, where you can find a quick-start guide and more information at <a href="https://github.com/seqcode/pegr/wiki">wiki</a>.</p>
            </div>
            <div id="role">
                <h3>User Group</h3>
                <p>There are three user groups.</p>
                <ol>
                    <li>Admin: admins have almost all the authorizations (read and write access) except for the access to personal password and API keys.</li>
                    <li>Member: members have read access to all the projects, inventory, lab protocols, experiment records, sequencing run reports and samples. They will have additional write access based on their <a href="#project-role">project roles</a> and their ownership to specific inventory, protocols and experiment records. </li>
                    <li>Guest: guests are only able to see their personal information and the projects they have been assigned to (see <a href="#project-role">Project Roles</a>).</li>
                </ol>
                Permissions on the PEGR platform are defined at multiple levels, including group based permissions and fine-grained permissions at the object level.
                <table class="table-bordered">
                    <thead>
                        <tr>
                            <th>Object</th>
                            <th>Action</th>
                            <th>Permission</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td rowspan="2">User</td>
                            <td>login</td>
                            <td>all</td>
                        </tr>
                        <tr>
                            <td>logout/profile/update</td>
                            <td>all authenticated users</td>
                        </tr>
                        <tr>
                            <td>Antibody</td>
                            <td>update/delete</td>
                            <td>admin or antibody's user</td>
                        </tr>
                        <tr>
                            <td>Cell source</td>
                            <td>update/delete</td>
                            <td>admin or cell source's user</td>
                        </tr>
                        <tr>
                            <td>Inventory</td>
                            <td>update/delete</td>
                            <td>admin or inventory item's user</td>
                        </tr>
                        <tr>
                            <td rowspan="5">Project</td>
                            <td>all/search/create</td>
                            <td>admin or member</td>
                        </tr>
                        <tr>
                            <td>show</td>
                            <td>admin, member or any projectRole*</td>
                        </tr>
                        <tr>
                            <td>update/remove user</td>
                            <td>admin or project owner*</td>
                        </tr>
                        <tr>
                            <td>add/remove sample</td>
                            <td>admin or project owner/parcitipant*</td>
                        </tr>
                        <tr>
                            <td>merge projects</td>
                            <td>admin or project owner*</td>
                        </tr>
                        <tr>
                            <td rowspan="2">Protocol</td>
                            <td>update/delete</td>
                            <td>admin or protocol's user</td>
                        </tr>
                        <tr>
                            <td>show</td>
                            <td>admin or member</td>
                        </tr>
                        <tr>
                            <td rowspan="5">Experiment</td>
                            <td>create/show</td>
                            <td>admin or member</td>
                        </tr>
                        <tr>
                            <td>reopen/delete</td>
                            <td>admin</td>
                        </tr>
                        <tr>
                            <td>add/remove sample</td>
                            <td>admin or member before processing starts</td>
                        </tr>
                        <tr>
                            <td>update</td>
                            <td>admin or member before processing completes</td>
                        </tr>
                        <tr>
                            <td>update each step</td>
                            <td>admin or step's user</td>
                        </tr>
                        <tr>
                            <td rowspan="3">Report</td>
                            <td>show</td>
                            <td>admin, member or any projectRole*</td>
                        </tr>
                        <tr>
                            <td>show unknown index/analysis status/
                                notes/run status</td>
                            <td>admin or member</td>
                        </tr>
                        <tr>
                            <td>create/delete/update cohort report/
                            delete sequence alignment/
                            update run status/analysis notes</td>
                            <td>admin</td>
                        </tr>
                        <tr>
                            <td rowspan="3">Sample</td>
                            <td>show</td>
                            <td>admin, member or any projectRole*</td>
                        </tr>
                        <tr>
                            <td>update</td>
                            <td>admin or project owner/participant*</td>
                        </tr>
                        <tr>
                            <td>all/search</td>
                            <td>admin or member</td>
                        </tr>
                        <tr>
                            <td rowspan="3">Sequence Run</td>
                            <td>update</td>
                            <td>admin or sequence run's user before run completes</td>
                        </tr>
                        <tr>
                            <td>show</td>
                            <td>admin or member</td>
                        </tr> 
                        <tr>
                            <td>create/delete/upload/updateQueue</td>
                            <td>admin</td>
                        </tr>
                        <tr>
                            <td>Admin dashboard</td>
                            <td>all</td>
                            <td>admin</td>
                        </tr> 
                    </tbody>
                </table>
                <p>*: project roles(see below).</p>
            </div>
            <div id="project">
                <h3>Project</h3>
                <p>PEGR provides a "Project" interface that organizes samples by scientific projects.</p>
                <img class="guide-image" src="/pegr/assets/project_list.png" alt="project list" style="width:1050px">
                <p>Users can create their own projects by clicking the "+Add Project" button. </p>
                <img class="guide-image" src="/pegr/assets/project_create.png" alt="project create" style="width:250px">
                <p>Once they create a new project, they will see the dashboard of the newly  created project.</p>
                <img class="guide-image" src="/pegr/assets/project_show.png" alt="project show" style="width:1050px">
                <p>They (and admin users) can then add more users to the project and assign project roles to the added users. By default, the user who  created the  project will be an owner of the project.</p>
                <h4 id="project-role" class="text-center">Project Roles</h4>
                <table class="table-bordered">
                    <thead>
                        <th>Project Role</th>
                        <th>View</th>
                        <th>Add/remove/edit samples</th>
                        <th>Edit project name, description, funding</th>
                        <th>Add/remove user, edit role</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Owner</td>
                            <td>&#10003;</td>
                            <td>&#10003;</td>
                            <td>&#10003;</td>
                            <td>&#10003;</td>
                        </tr>                
                        <tr>
                            <td>Participant</td>
                            <td>&#10003;</td>
                            <td>&#10003;</td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Guest</td>
                            <td>&#10003;</td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>None</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <p>Note that: </p>
                <ol>
                    <li>These permissions are per project, i.e. an "Owner" in one project may be a participant in another.</li>
                    <li>Platform-wide admins also have all the authorizations to any project (the same access level as a project owner), and platform-wide members have all the read access to any project (the same access level as a project guest).</li>
                </ol>
                    
            </div>
            <div id="admin-console">
                <h3>Admin Console</h3>
                <p>The Admin Console, which is  accessible to admins only, hosts the lookup tables for many elementory components in PEGR, including</p>
                <ul>
                    <li><b>Biological Specifications</b>: Cell source treatment, sex, IG type, species, histology, AB host, tissue, target, target type, strain and growth media;</li>
                    <li><b>Protocol</b>: assay, protocol group, sequence index, item type, item type category;</li>
                    <li><b>Alignment and analysis</b>: pipeline, reference feature, align type, aligner, sequence platform, genome and read type;</li>
                    <li><b>Other</b>: role, role group, funding, organization, user, definition.</li>
                </ul>
                <img class="guide-image" src="/pegr/assets/admin_console.png" alt="admin console" style="width:1050px">
                <p>These lookup tables facilitate the standardization of the inputs. Once a PEGR instance is set up, it's usually the first thing for the admin to visit the Admin Console and update the lookup tables. Each lookup table follows the CRUD (Create, Read, Update and Delete) convention. Take the "Item  Type" lookup table for example. First, click the "Item Type" tab on the Admin Console and it will take you to the list of existing item types. On the top-right corner, there is a search  widget, where you can search a item type by its fields such as name and category. </p>
                <img class="guide-image" src="/pegr/assets/admin_create.png" alt="admin create list">
                <p>If you want to create a new item type entry, click "New ItemType" button on the top of the list page. Input the values and click "Create" to save the new item type. Note that there is a field named "Status", which can be used to indicate if the entry is being actively used or obsolete. If there are errors in your inputs, e.g. missing value or a unique value already exists in the database, those errors will display  on the  top of the page. When all the input values are valid, the new instance will be saved to the database and you will be taken to the page that shows the newly created item type. At the bottom of the page, there are buttons that take you to edit and delete the item  type. </p> 
                
                <h4>Merge</h4>
                <p>There could be errors during data entry and sometime both the correct entry and an erroneous entry coexist in the database. In this case, you may want to merge the erroneous entry into the correct entry. PEGR provides a "Merge" button at the top-right corner of the Admin Console's home page. This widget applies for  entries in AB host, growth media, IG type, sex, species, strain, target, target type, tissue and assay, and when the admin knows the ID of the entries to be merged. Once the form is submitted, all objects linked to the erroneous entry (from) will be linked to the correct entry (to), and then the erroneous entry will be deleted. </p>
                <img class="guide-image" src="/pegr/assets/admin_merge.png" alt="admin merge" style="width:1050px">
                <p>PEGR provides additional merge widgets in the individual lookup  tables ofuser, strain, species, genome, tissue, target type, growth media, cell source treatment, IG type and AB host. In these widgets, you can merge multiple entries by name. </p>
                <img class="guide-image" src="/pegr/assets/admin_merge2.png" alt="admin merge" style="width:500px">
                <h4>User and user groups</h4>
                <p>From the home page  of the Admin Console, click the  "User" tab and you  will see a table of existing users. On the upper-right corner of the table is a search bar that you can search a user by any field in the table, e.g. name, username, email, affiliation or group. And there is a side bar to the right of the table where you can filter users by group, affiliation and status. </p>
                <img class="guide-image" src="/pegr/assets/user_list.png" alt="user list" style="width:1050px">
                <p>Users can login PEGR through two ways: either password enabled login and single sign-on (see <a href="https://github.com/seqcode/pegr/wiki/Configurations">here</a> for how to configure single sign-on). Catering for the two login modes, PEGR provides two ways to create a user. </p>
                <ol>
                    <li>
                        <p>If the new user will login through single sign-on, the admin can simple add the user without sending out an email. To add a new user, input the new user's email, select the user's group, uncheck the "Send Email" box, and click "Add User." The newly created user will have a username the same as their email address and a randomly assigned password (since the password won't be needed during single sing-on).</p>
                        <img class="guide-image" src="/pegr/assets/user_create_sso.png" alt="create user - sso" style="width:1050px">
                        Once a user is created, the admin can further edit the user's information, such as name and affiliation.
                        <img class="guide-image" src="/pegr/assets/user_edit.png" alt="edit user" style="width:500px">
                    </li>
                    <li>
                        <p>If the new user will login by password, the admin needs to check the "Send Email" box when creating the user (see <a href="https://github.com/seqcode/pegr/wiki/Configurations">here</a> for how to configure the email server). Once the admin clicks the "Add User" button, an email as below will be sent to the new user. </p>
                        <img class="guide-image" src="/pegr/assets/user_email.png" alt="email user" style="width:1050px">
                        <p>The user then follows the link in the email to register at PEGR.</p>
                        <img class="guide-image" src="/pegr/assets/user_register.png" alt="register user" style="width:300px">
                    </li>
                </ol>
            </div>
            
            <div id="barcode-inventory">
                <h3>Inventory Tracking</h3>
                <p>The inventory has many types, including antibody, chemical, enzyme, cell stock, biosample, sample pool and others. In PEGR, barcodes are used to track the inventory. Note that the barcodes have to be UNIQUE for each item type. To use the barcode tracking system in PEGR, you need an android phone or tablet with the "Barcode Scanner" app (based on zxing) installed from the Play Store. </p>
                <p>To create a new inventory item in PEGR, first go to the “Inventory” tab and find the top banner. If the item does not have a barcode yet, select type at the right, and click the  "New instance with PEGR  generated  barcode" button.  </p>
                <img class="guide-image" src="/pegr/assets/inventory.png" alt="Inventory" style="width:1050px">                
                <p>You will see a page showing the item type and barcode. </p>
                <img class="guide-image" src="/pegr/assets/barcode_item.png" alt="Item" style="width:400px">
                <p>Click “Create” and there is a page for you to fill in more information, e.g. name, location and notes.</p>
                <img class="guide-image" src="/pegr/assets/barcode_item_create.png" alt="Create Item" style="width:350px">
                <p>After filling in the information, hit the button “Save” and you will be taken to the page showing the item.</p>
                <img class="guide-image" src="/pegr/assets/barcode_item_show.png" alt="Create Item" style="width:550px">
                <p>You can further upload images and print the barcode here. </p>
                <p>If the item already has an attached barcode, you can scan the barcode into PEGR on your mobile device. Select “Type” and then click the barcode icon <span class="glyphicon glyphicon-qrcode"></span>.</p>
                <img class="guide-image" src="/pegr/assets/barcode_scan.png" alt="Scan Item" style="width:1050px">
                <p>You will be taken to the barcode scanner app. Scan the barcode and you will be taken back to the PEGR page. Then click the “Search/Create Instance with existing barcode” button. If the scanned barcode already exists in the database, the item with the barcode  will show up; otherwise, you will see a page to add the scanned item to the database. First, you need to select a type. And then you can add more information (e. g. name, location and notes) to the item.</p>
                <img class="guide-image" src="/pegr/assets/barcode_add_item.png" alt="Add scanned Item" style="width:250px">
                <img class="guide-image" src="/pegr/assets/barcode_add_item2.png" alt="Add scanned Item" style="width:350px">
              </div>
              <div id="barcode-protocol">
                <h3>Protocol and Protocol Group</h3>
                <p>A protocol is the standard for one experiment step. For example, some chemicals are mixed to create an end product, a cell stock is processed to another state, or a batch of biosamples are pooled. To create a protocol, go to the tab “Protocols” and then “My Protocols”. You can either import a protocol from a CSV file by clicking the "Import CSV" or create a protocol at the user interface by clicking the button “New”.</p>
                <img class="guide-image" src="/pegr/assets/barcode_protocol.png" alt="Protocol" style="width:1050px">
                <p>On the “Create Protocol” page, you can fill in the information about the protocol. The shared item types and end product types could be certain types of chemicals and reagents, and you want to barcode and track those items during the experiment. The traced sample may change from one state to another state during an experiment step. Sometimes the sample is transferred from one container to another container, or a partial portion of the sample goes to the next state. In these situations, you might want to have different barcodes for different states of the sample, and you can specify the “Start State” and “End State” of the traced sample. If a pool is created in the step, you can specify “Create Pool” type. And if a pool is processed and transforms to another state, you can specify both “Import Pool” and “Create Pool” types. The “URL” field allows you to link to another webpage, and you can also upload a PDF file of the protocol.</p>
                <img class="guide-image" src="/pegr/assets/barcode_protocol_create.png" alt="CreateProtocol" style="width:500px">
                <p>Users can see the protocols that they created in the “My Protocols” tab. Protocols that are “approved” (often by the lab admin) will also be listed in the “Lab Protocols” tab. In addition, lab admin can view all the protocols in the “All Protocols” tab. </p>
                <p>There is also a tab named “lab Protocol Groups”. A protocol group is an ordered list of protocols that happen in sequence. In practice, some protocols often go together and the protocol group is a convenient tool of creating experiment records. </p>
                <img class="guide-image" src="/pegr/assets/barcode_protocol_group.png" alt="Protocol Group" style="width:550px">
                <p>The protocol group is created in the Admin Console. Go to the “Admin” tab and find “Protocol Group” in the “Protocol” section. Follow the  "New ProtocolGroup" link to create a protocol group.</p>
                <img class="guide-image" src="/pegr/assets/barcode_protocol_group_create.png" alt="Create Protocol Group" style="width:1050px">
            </div>      
            <div id="barcode-experiment">
                <h3>Experiment Tracking</h3>
                <p>An experiment record is a record of one or more steps of experiment. It records the protocols followed and the barcoded items (chemicals, reagents, samples, pools) used during the experiment.</p>
                <img class="guide-image" src="/pegr/assets/experiment_list.png" alt=" Experiment Home" style="width:1050px">
                <p>To create an experiment record, go to the “Experiment” tab and click the “New” button at the top, and you will be taken to the following page. Here you can either select a protocol group or a list of protocols. You can select one or more projects here, and then on the view page of the experiment record, you can further assign each sample to a project.</p>
                <img class="guide-image" src="/pegr/assets/barcode_experiment_create.png" alt="Create Experiment" style="width:350px">
                <p>Once the experiment record is saved, you will be taken to the view page of the record. The left panel lists the protocols as you defined before and the right panel is for you to add samples to the experimennt. Note that on a smaller screen, e.g. on a mobile device, the right panel will be stacked below the left panel.</p>
                <img class="guide-image" src="/pegr/assets/barcode_experiment_summary.png" alt="Create Experiment">
                <p>You will notice that there is a "Start" button next to the first protocol on the left panel. That will take you to the details of that experiment step. But before clicking the “Start” button, you need to add all the samples in the experiment and you will be reminded by a warning message. There are two ways to add samples. The first is to click the <span class="glyphicon glyphicon-qrcode"></span> symbol and scan the sample's barcode using your mobile device. </p>
                <img class="guide-image" src="/pegr/assets/experiment_scan_sample.png" alt="Edit Experiment">
                <p>Once you have scanned and found the sample, you will be asked to confirm if you want to "Import Sample(s)" or "Split and Add Sample(s)". The second option applies if you only want to take part of the existing sample for processing and the rest part will be kept for future use. If the second option is chosen, a new barcode will be created for the portion that will be used in the experiment. </p>
                <img class="guide-image" src="/pegr/assets/experiment_import_sample.png" alt="Edit Experiment">
                <p>The other way to add samples is to click the <span class="glyphicon glyphicon-list-alt"></span> symbol and search by typing the related information, e.g. sample type, name, barcode or location.</p>
                <img class="guide-image" src="/pegr/assets/experiment_search_sample.png" alt="Edit Experiment">
                <p>Once you find your sample in  the left table, click the checkbox and the sample will be added to the right table. Click the  "Add selected samples" button and the samples in the right table will be populated in the next page where  you can choose either to "Import Sample(s)" or "Split and Add Sample(s)" as above.</p>
                <img class="guide-image" src="/pegr/assets/experiment_search_sample2.png" alt="Edit Experiment">
                <p>When all the needed samples have been added to the experiment, you can  click the green "Start" button and start tracing the experiment.</p>
                <img class="guide-image" src="/pegr/assets/experiment_sample_added.png" alt="Edit Experiment">                
                <p>Once you have added all the samples to the experiment, click the “Start” button next to the first protocol, and you will see the following page.</p>
                <img class="guide-image" src="/pegr/assets/barcode_experiment_edit.png" alt="Edit Experiment">
                <p>If the protocol has defined types for the shared items, end product, samples' start and end state, and imported or created pool, each of these types will be expanded as a table for you to track the respective items. For example, if the protocol requires a shared item with the type "YPD Media", there will be a "Shared Items" table with a row for the type "YPD Media". You then scan the corresponding item to that row by following the "+" sign. </p>
                <img class="guide-image" src="/pegr/assets/experiment_add_item.png" alt="Edit Experiment" style="display:block;">
                <img class="guide-image" src="/pegr/assets/experiment_add_item2.png" alt="Edit Experiment" style="display:block;">
                <p>Once all the required fields are filled in, the grey "Processing" button at the bottom will turn into a green "Complete" button. </p>
                <img class="guide-image" src="/pegr/assets/experiment_edit_complete.png" alt="Edit Experiment">
                <p> Click the green button to close this step and return to the experiment's summary page. You will notice that the first protocol is now labeled as "Completed" and a green "Start" button appears next to the second protocol.</p>
                <img class="guide-image" src="/pegr/assets/experiment_summary2.png" alt="Edit Experiment">
                <p>Complete all the protocols in sequence, and a green "Compleete" button will show up at the bottom of the summary page. Click the green button to indicate that the entire experiment has completed. </p>
                <img class="guide-image" src="/pegr/assets/experiment_summary3.png" alt="Edit Experiment">
                <p>Note that no change can be made to the experiment record afterwards unless an admin reopens the experiment record.</p>
                <img class="guide-image" src="/pegr/assets/experiment_summary4.png" alt="Edit Experiment">
            </div>
            <div id="sequence-run">
                <h3>Sequence Run</h3>
                <p>The dashboard of Sequence Runs features a table for all the sequence runs, a search  bar on  the top-right corner, and a side bar on the right for widgets to initiate new sequence runs and manage settings. </p>
                <img class="guide-image" src="/pegr/assets/run_list.png" alt="Sequence Run">
                <h4>Sample Submission</h4>
                <p>There are two ways to initiate a sequence run. One way is to upload a sample submission form (run file). A template sample submission  form  is provided <a href="https://github.com/seqcode/pegr/tree/master/sample_files">here</a>. The sample submission form is an excel file, and it contains two sheets: sample sheet  and lane sheet. In the  "Upload Run File" widget, you need to specify the lines of samples that you want to import in the sample sheet and the line in thee lane sheet that contains the information about the sequence run.</p>
                <img class="guide-image" src="/pegr/assets/sample_submission.png" alt="Sequence create" style="width:550px">
                <p>Another way  to initiate a sequence run is to use the "Create Wizard". First create a sequence run by inputing the sequencing platform  and run name along  with other  information if available.</p>
                <img class="guide-image" src="/pegr/assets/run_create.png" alt="Sequence create">
                <p>Once a sequence run is created, add samples related projects to the sequence run. Note that samples from multiple projects could be pooled together and go into the  samplee sequence run. The group of samples that belong to the same sample project and sequencing run is defined as a "sequencing cohort". </p>
                <img class="guide-image" src="/pegr/assets/run_show.png" alt="Sequence Run">
                <p>There are two ways to import samples to the sequence run. One is to follow the "Add Sample" button and input the sample ID's. The other way is to follow the "Add Master Pool" button and scan the master pool's barcode. And all the samples in the master pool will be imported to the sequence run. The sample's project can be  changed in the sample table. Just click the project text, a form will show up in the same cell. Select the project and click the "Save" button.</p>
                <img class="guide-image" src="/pegr/assets/run_sample_project.png" alt="Sequence Run">
                <p>You can also remove samples from the sequence run. Select the samples and click the <span class="glyphicon glyphicon-remove"></span> icon to only remove the samples from the sequence run and the  samples themselves will be preserved. Click the <span class="glyphicon glyphicon-trash"></span> icon and the samples will be deleted from the database altogether.</p>
                <p>When a sequencing run has just been created, it's in the "PREP" status and there is a green "Submit" button at the bottom of the page. Once you click the "Submit" button, the status of the sequence run will changee into "QUEUE" and the information about the sequence run and included  samples will be compiled into a set of files and those files will be deposited to the designated NGS repository. See the "ngsRepo" section of the <a href="https://github.com/seqcode/pegr/wiki/Configurations">wiki</a> for instructions on how to configure the connection  to the  NGS repository.</p>
                <h4>Connect to External Data Analysis Pipelines</h4>
                <p>PEGR can work with external data analysis pipelines such as Galaxy. The external data analysis pipeline needs to take over the run information files prepared by PEGR and send the analysis results back to PEGR via API's. Detailed instructions and scripts on how to connect to Galaxy can  be  found  in the  following links:</p>
                <ol>
                    <li>Retrieve and Preprocess raw data: <a href="https://github.com/CEGRcode/pegr-ngs_pipeline">https://github.com/CEGRcode/pegr-ngs_pipeline</a></li>
                    <li>Send analysis results (metadata) back to  PEGR: <a href="https://github.com/CEGRcode/pegr-galaxy_tools">https://github.com/CEGRcode/pegr-galaxy_tools</a></li>
                </ol>
                <h4>Analysis Pipeline Status</h4>
                Once the external analysis pipeline takes over the run information from  PEGR, the  sequence run's status  will change into "ANALYZING". From the "Sequencing Runs" dashboard, click the "Details" link in the "Run Analysis Status" column, you can further view each analysis step's status of individual samples.
                <img class="guide-image" src="/pegr/assets/pipeline_status.png" alt="Sequence Run">
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
                Once all the data analysis is returned to PEGR, you can scan through the "Quality Control" pannel to verify the results. If no critical problem is found, you can change the status  of the sequence run to "COMPLETED". At the  bottom  of the analysis status page, you  can  generate a report for each sequencing cohort by clicking the  "Generate Report" button.
                <img class="guide-image" src="/pegr/assets/generate_cohort_reports.png" alt="Search Samples">
                When you  return  to  the sequence run  dashboard, the links  to the cohort reports will show up in the "Summary Report" column.
                <img class="guide-image" src="/pegr/assets/run_cohort_reports.png" alt="Search Samples">
            </div>
            <div id="search-samples">
                <h3>Samples</h3>
                <p>Under the "Samples" tab, you can view all the samples and search sampels by ID, species, strain, antibody, etc. You can click an individual sample and view the details for that sample. In adddition, you can select multiple samples, and view the selected sample together and compare.</p>
                <img class="guide-image" src="/pegr/assets/search_sample.png" alt="Search Samples">
            </div>
          </div>
          <nav class="col-sm-3">
            <h4>Menu</h4>
            <ul id="menu" data-spy="affix" data-offset-top="205">
              <li><a href="#what">What is PEGR</a></li>
              <li><a href="#role">User Group</a></li>
              <li><a href="#project">Project</a></li>
              <li><a href="#admin-console">Admin Console</a></li>
              <li><a href="#barcode-inventory">Inventory Tracking</a></li>
              <li><a href="#barcode-protocol">Protocol and Protocol Group</a></li>
              <li><a href="#barcode-experiment">Experiment Tracking</a></li>
              <li><a href="#sequence-run">Sequence Run</a></li>
              <li><a href="#search-samples">Samples</a></li>
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