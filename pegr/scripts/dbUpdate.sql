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
