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

// fill sample_treatments table
insert into sample_treatments (sample_id, treatment_id) select s.id, treatment_id from cell_source c inner join cell_source_treatments ct on c.id = ct.cell_source_id inner join sample s on s.cell_source_id = c.id 

// fill growth media in sample
update sample s inner join cell_source c on s.cell_source_id = c.id set s.growth_media_id = c.growth_media_id 

// delte reports
delete from summary_report
