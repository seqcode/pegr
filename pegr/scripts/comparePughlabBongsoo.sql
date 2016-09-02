SELECT e.sequence_run_id, s.id, s.source_id, MAPPED_READ_COUNT, mapped_reads FROM pughlab.PughLabSampleStatisticsInfo p
join pegr.sample s on s.source_id = substring(p.UNIQ_ID, 5, 5)
join pegr.sequencing_experiment e ON e.sample_id = s.id
join pegr.sequence_alignment a on a.sequencing_experiment_id = e.id
WHERE p.UNIQ_ID is not null
order by e.sequence_run_id, s.id
INTO OUTFILE 'file.csv'
FIELDS TERMINATED BY ',';

// sudo mv /var/lib/mysql/pegr/file.csv ~