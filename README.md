PEGR is a web platform for sequencing metadata management.

Grails 2.5.5

Some notes on adding an new tab

How to add a new tab
Copy paste the if(qcSettings.xxx) block in ReportController and replace xxx with your desired tab name (e.g. test)
Modify the Map QC_SETTINGS in ReportService and follow the format            test': "TEST_QC_SETTINGS"
In runStatus, add one of these in nav-tabs <li><a data-toggle="tab" href="#test">test</a></li> 
Then, add one of these in tab-content  <div id="test" class="tab-pane fade">  <g:render template="testQc" model="[runStatusMap:it]"></g:render>       </div>
Make a _testQc.gsp and add it under report
