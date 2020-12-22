<html>
    <head>
        <meta name="layout" content="help">
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
            
            <div id="sample-submission">
                <h3>Sample Submission</h3>
                <ol>
                    <li>
                        <h4>Assay</h4>
                        <p>Each assay has a pre-defined template. All samples in the sample submission form have the same assay. Samples using different assays should be submitted in separate forms.</p>
                    </li>
                    <li>
                        <h4>Sections</h4>
                        <p>The sample submission form is separated into three sections: "Sample", "Antibody" and "Target". The header buttons will help you to navigate to the corresponding sections.</p>
                    </li>
                    <li>
                        <h4>Select and Type</h4>
                        <p>Click on the fields with placeholder "Select...", a drop-down menu will show up. You may select a choice directly, or type in some characters to narrow down the selections. Fields with placeholder "Select or type..." will allow you to type in new words in addition to the provided selections. Fields without placeholder accept free text. </p>
                    </li>
                    <li>
                        <h4>Multiple selections</h4>
                        <p>Some fields, such as "Treatments" and "Reference Genomes", accept multiple selections. Once you enter the first selection, that selection will be wrapped up and prefixed with a remove sign. You may continue to choose additional selections.</p>
                    </li> 
                    <li>
                        <h4>Cascade select</h4>
                        <p>When you open up a sample submission form, some fields are disabled (grey) temporarilly. These fields often depend on previous fields. For example, the selection of "Genus" will narrow down the range of "Species". When "Genus" is selected, the field "Species" will be active and a list of species in that Genus will be provided. Likewise, "Species" narrows down "Parent Strain", and then "Strain". The dependencies are list below.</p>
                        <p>Sample</p>
                        <ul>
                            <li>Genus -> Species -> Parent Strain -> Strain -> Genotype -> mutation </li>
                            <li>Species -> Growth Media</li>
                            <li>Species -> Reference Genomes</li>
                        </ul>
                        <p>Antibody</p>
                        <ul>
                            <li>Catalog -> Host, Immunogene, Clonal, Ig Type and Concentration. </li>
                            <li>Catalog -> Target Type, Target, C-Term and N-Term</li>
                        </ul>
                    </li>            
                    <li>
                        <h4>Keyboard navigation</h4>
                        <p>When drop-down menu is closed</p>
                        <ul>
                            <li><button>Tab</button>: navigate to the next field.</li>
                            <li><button>Shift</button> + <button>Tab</button>: navigate to the previous field.</li>
                            <li><button>&darr;</button>: open drop-down menu.</li>
                        </ul>
                        <p>When drop-down menu is open</p>
                        <ul>
                            <li><button>&uarr;</button>, <button>&darr;</button>: navigate the list of choices.</li>
                            <li><button>Enter</button>: select.</li>
                            <li><button>Esc</button>: close the drop-down menu.</li>
                        </ul>
                    </li>            
                    <li>
                        <h4>Add Row</h4>
                        <p>Click the <button>Add Row</button> button, a clone (including all the data and selections) of the last row will be appended to the bottom of the form. Then you may make changes to the new row.</p>
                    </li>            
                    <li>
                        <h4>Remove</h4>
                        <p>A row may be removed by clicking the <span class="glyphicon glyphicon-trash"></span> symbol at the beginning of that row.</p>
                    </li>            
                    <li>
                        <h4>Validations</h4>
                        <p>Validations of all the fields will occur when the form is submitted (some simple input fields will be validated whenever the fields are changed). Error messages will appear in red around the corresponding fields.</p>
                        <ul>
                            <li>Required Fields: Genus, Species, Parent Strain, Strain, Growth Media, Genomes, Catalog, Target.</li>
                            <li>Fields where only alphanumeric characers and hyphen (e.g. 0-9, a-z, A-Z and -) are accepted: Strain, Mutation, Growth Media, Treatments, Catalog, Target.</li>
                            <li>Fields that accept numbers: Chrom.(ug), Cell#(M), Volume(ul), Requested Tags(M), Conc.(ug/ul), Volume Sent(ul), Usage Per ChIP(ug), Usage Per ChIP(ul)</li>
                        </ul>
                    </li>    
                </ol>
            </div>
            <div id="bag">
                <h3>Work Bench</h3>
                <ol>
                    <li>
                        <h4>Barcode</h4>
                        <p>Barcodes are used to trace samples, antibodies and other items used during the process. The Barcodes have to be UNIQUE for each item type.</p>
                        <p>You may use mobile devices to scan the barcode. PEGR is very easy to use on android phones and tablets. The Android devices need to have the "Barcode Scanner" app (based on zxing) installed from the Play Store. From PEGR webpages, click on the <span class="glyphicon glyphic-qrcode"></span> symbol next to the barcode input fields. The barcode sanner will open up automatically. Then scan the barcode, and you will be taken back to the original PEGR webpage with the barcode filled. If you are using iPhone/iPad, you may download  "Qrafter" from the App Store. Scan the barcode, click "Copy to clipboard", and paste the barcode text back to the PEGR webpage. </p>
                    </li>
                    <li>
                        <h4>Protocol Instance Bag</h4>
                        The "Bag" is defined by a set of samples and a list of protocols to be performed on these samples.
                        <asset:image src="bag.png" height="400px"/>
                    </li>
                    <li id="addSampleToBag">
                        <h4>Import bag</h4>
                        <p>Scan any sample in the previouly finished bag. You will be asked whether you want to add only that particular sample or the entire bag. If you choose to import the entire bag, all samples in that bag will be added to the new bag.</p>
                    </li>
                    <li>
                        <h4>Split the sample</h4>
                        <asset:image src="split-sample.png" height="400px"/>
                        <h5>Import vs. Split and Add</h5>
                        <p>If the traced sample is linked to other bags, you can choose to either "Import" or "Split and Add". "Import" means that you will continue working on the sample(s). For example, the traced sample has been through the protocols in the latest bag, and will continue being processed in this bag. "Split and Add" means that you will create a new sample off the scaned traced sample. You may choose this option when you find out that the traced sample has been associated with another bag. </p>
                    </li>
                    <li>
                        <h4>Sample Pool</h4>
                        <ul>
                            <li>
                                <h5>Create a pool from a set of samples</h5>
                                <p><i>Admin:</i> in the Admin page, define the "Item Type" for the pool and choose "Sample Pool" as the category. When creating the protocol that creates the sample pool, select the type for "End Pool". </p>
                                <p><i>Lab member:</i>in the related protocol instances, create and barcode the new pool as an end pool.</p>
                            </li>
                            <li>
                                <h5>Merge pools</h5>
                                <p><i>Admin:</i> in the Admin page, define the "Item Type" for the starting pool and ending pool if they have not been created. Make sure to choose "Sample Pool" as the category. When creating the protocol that creates the sample pool, select the types for "Start Pool" and "End Pool". </p>
                                <p><i>Lab member:</i> inside the related protocol instances, scan the starting pools' barcode to import them. And after the new pool is created, barcode the new pool as an end pool.</p>
                            </li>
                        </ul>
                    </li>
                </ol>
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
          </div>
          <nav class="col-sm-3">
            <h4>Menu</h4>
            <ul id="menu" data-spy="affix" data-offset-top="205">
              <li><a href="#project-role">Project Role</a></li>
              <li><a href="#pipeline-status">Pipeline Status</a></li>
              <li></li>
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