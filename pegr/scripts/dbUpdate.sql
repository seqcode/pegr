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