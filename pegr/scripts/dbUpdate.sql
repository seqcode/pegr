//insert into role (version, authority) values (0, "ROLE_MEMBER");

//drop table sample_in_pool;

//drop table pool;


/*
select * into outfile '/Users/dus73/temp/sampleStats.csv'
fields terminated by ','
ESCAPED BY ""
lines terminated by '\n'
from  pughlab.PughLabSampleStatisticsInfo
where uniq_id is not null;

select * into outfile '/Users/dus73/temp/peakStats.csv'
fields terminated by ','
ESCAPED BY ""
lines terminated by '\n'
from  pughlab.PughLabPeakStatisticsInfo;
*/

/* select count(*) from sequencing_experiment e join sequence_run r on e.sequence_run_id = r.id where r.run_num between 520 and 622

select avg(cnt) from (select count(*) as cnt from sequencing_experiment e join sequence_run r on e.sequence_run_id = r.id where r.run_num between 520 and 622 group by sequence_run_id) as cnt_table;


select avg(cnt) from (select count(*) as cnt from sequencing_experiment as e join project_samples as p on e.sample_id = p.sample_id join sequence_run r on e.sequence_run_id = r.id where r.run_num between 520 and 622 group by sequence_run_id, project_id) as cnt_table;


select avg(cnt) from (select count(*) as cnt from sequencing_experiment as e join project_samples as p on e.sample_id = p.sample_id join sequence_run r on e.sequence_run_id = r.id where r.run_num between 520 and 622 group by r.run_num, project_id) as cnt_table;

select avg(cnt) from (
select cnt_table.run_num, pr.name, cnt_table.cnt from (select r.run_num, project_id, count(*) as cnt from sequencing_experiment as e join project_samples as p on e.sample_id = p.sample_id join sequence_run r on e.sequence_run_id = r.id where r.run_num between 520 and 622 group by r.run_num, project_id) as cnt_table
join project pr on pr.id = cnt_table.project_id order by run_num) as grouped;


select s.id, r.run_num, i.invoice_num from sample s join invoice i on s.invoice_id = i.id join sequencing_experiment e on e.sample_id = s.id join sequence_run r on e.sequence_run_id = r.id where invoice_num like "%p00%" and r.run_num between 520 and 622;
*/

// insert into chores (version, name, value) values (0, "GalaxyAPIKey", "");  

// UPDATE sample,sequencing_experiment SET sample.source_id = sequencing_experiment.seq_id where sample.id = sequencing_experiment.sample_id;

// update sample set sample.source="Peconic" where sample.invoice_id in (select id from invoice where invoice_num like "%p00%");

// update sample set sample.source="CollaboratorX" where sample.invoice_id in (select id from invoice where invoice_num like "%x00%");

// update sample set sample.source="CollaboratorS" where sample.invoice_id in (select id from invoice where invoice_num like "%s00%");

// update sample set source = "PughLab" where source is null;

// update sample_sequence_indices set index_in_set = 2 where mod(id, 2)= 1 and set_id is not null;

// update sample_sequence_indices set index_in_set = 1 where mod(id, 2)= 0 and set_id is not null;

// update sample, protocol_instance_summary, protocol set sample.assay_id = protocol.assay_id where sample.prtcl_inst_summary_id = protocol_instance_summary.id and protocol_instance_summary.protocol_id=protocol.id and protocol.assay_id is not null;

// update protocol, assay set protocol.assay_id = assay.id where protocol.name = assay.name

// insert into organization (version, name, class) values (0, "Pugh Lab", "pegr.Lab");

// update user set affiliation_id = 81 where username = "bfp2"; // check affiliation_id! "labadmin"

/*
insert into read_type (version, short_name, name) values (0, "PE","Paired End");
insert into read_type (version, short_name, name) values (0, "SR","Single Read");

update replicate_set set type="BIOLOGICAL";

update antibody set immunogene = "Unknown" where immunogene is null;

update antibody set catalog_number = "Unknown" where catalog_number is null;

insert into strain (version, name) values(0, "Unknown");
select id from strain where name = "Unknown";
update strain set parent_id = ________ where parent_id is null;

update target_type set name = "Unknown" where name = "unknown";

update sequencing_experiment set read_positions = '{"rd1":["1","40"],"index1":["41","48"],"index2":["49","56"],"rd2":["57","92"]}' where read_positions = '{"rd1":["1","40"],"index":["41,49","48,57"],"rd2":["58","93"]}';

update user set enabled = false;
update user set enabled = true where id in (1,3, 173);
*/

/*
// fill sample_treatments table
insert into sample_treatments (sample_id, treatment_id) select s.id, treatment_id from cell_source c inner join cell_source_treatments ct on c.id = ct.cell_source_id inner join sample s on s.cell_source_id = c.id 

// fill growth media in sample
update sample s inner join cell_source c on s.cell_source_id = c.id set s.growth_media_id = c.growth_media_id 

// delte reports
delete from summary_report
*/

// alter table sequence_run auto_increment = 215;

insert into user (version, account_expired, account_locked, enabled, password, password_expired, username, email, api_key) values (0, false, false, false, "$2a$10$ErWZYV6RfjF0ST8YQ43ImetX6pNEUPXOw1hGA.T092tfSBgT2t2kC",false, "galaxy-cegr", "cegr@psu.edu", "0YG9B2LPLLDPP4B1LQQ0BB1LH75L8A3P");


insert into pipeline (version, workflow_id, name, pipeline_version, steps) values (0, "b266c9aed69b2935","paired", "001", '[["input2","output_fastqRead2"],["input1","output_fastqRead1"],["ad583fa93b5fed87","output_fastqc"],["96dd6dd693e9a033","output_fastqc"],["71175efa22558b3b","output_markDuplicates"],["b868e18d758bba8a","output_samtoolFilter"],["6f4fa8324bf2747b","output_peHistogram"],["89b5528676fe3fae","output_bamToScidx"],["b24b58817eeee721","output_genetrack"],["072cb5142f83033f","output_bedtoolsIntersect"],["f6186cdbed51a4cb","output_cwpair2"],["cdb3593ee534882e","output_extractGenomicDNA"],["d9078eb5a65df1e4","output_extractGenomicDNA"],["96761ccd61321ce3","output_repeatMasker"],["c36cbb3fa155d5b4","output_repeatMasker"],["34caead642390a27","output_meme"],["7c5ed654bd70e4b5","output_fimo"],["59e21c1ec43960de","output_extractGenomicDNA"],["cce41d830f1a2319","output_fourColorPlot"],["10a14f60d05bf206","output_tagPileup"]]');

insert into pipeline (version, workflow_id, name, pipeline_version, steps) values (0, "ed30e974065fb948", "single", "001", '[["input1","output_fastqRead1"],["4907c51e3aa6e860","output_fastqc"],["6cd87e7183cf8adf","output_markDuplicates"],["0aaceadffb66386e","output_samtoolFilter"],["f277bfdf7db1fd92","output_peHistogram"],["c0add05666cfe9e1","output_bamToScidx"],["afcb51f2c322d3e2","output_genetrack"],["5a48aaf4773da987","output_bedtoolsIntersect"],["551cc07dc795d77f","output_cwpair2"],["b1fe447033b4153f","output_extractGenomicDNA"],["63c2821ce20bf810","output_extractGenomicDNA"],["322a184133da80c8","output_repeatMasker"],["3306623a89c4f2b1","output_repeatMasker"],["e326ee6dfab5e747","output_meme"],["21777a7abfcac6bb","output_fimo"],["1a8acd3a033f4266","output_fourColorPlot"],["52e892817bc83e2b","output_tagPileup"]]');

select *  from pipeline;
update sequence_alignment set pipeline_id = 2;
delete from pipeline where name = "Galaxy";

insert into item_type_category (version, name, super_category) values (0, "Chemical", "OTHER");
insert into item_type_category (version, name, super_category) values (0, "Enzyme", "OTHER");
insert into item_type_category (version, name, super_category) values (0, "Reagent", "OTHER");
insert into item_type_category (version, name, super_category) values (0, "Equipment", "OTHER");
insert into item_type_category (version, name, super_category) values (0, "General Supply", "OTHER");
insert into item_type_category (version, name, super_category) values (0, "Cell Stock", "TRACED_SAMPLE");
insert into item_type_category (version, name, super_category) values (0, "Biosample", "TRACED_SAMPLE");
insert into item_type_category (version, name, super_category) values (0, "Sample Pool", "SAMPLE_POOL");

update sequence_run set status = "ANALYZING" where status = "RUN";

delete from chores where name = "PipelineSteps";

delete from report_alignments;
delete from summary_report;
                                                                       