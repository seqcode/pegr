package pegr.admin
import pegr.AdminCategory
import pegr.UtilityException
import pegr.UtilityService
import groovy.sql.Sql

class AdminController {
    def utilityService
    def dataSource

    def index() {    
		
		def controllerGroups = [:]
		
		for (AdminCategory v : AdminCategory.values()) {
			controllerGroups[v] = []
		}
		
	    grailsApplication.controllerClasses.each { controller ->
	        def name = controller.name
	        if(name.endsWith('Admin') && name != 'Admin') {
				def key = controller.getStaticPropertyValue('category', AdminCategory) ?: AdminCategory.OTHER
                
				controllerGroups[key] << [
                    key: controller.logicalPropertyName, 
                    value: controller.naturalName.replace('Admin Controller', '')
                ]
	        }
        }
        
        for (AdminCategory v : AdminCategory.values()) {
            controllerGroups[v] = controllerGroups[v].sort { it.key }
		}
        
        [controllerGroups: controllerGroups]
	}
    
    def mergeForm(String table) {
        def tables = ['ab_host', 
                      'growth_media',
                      'histology',
                      'ig_type',
                      'sex',
                      'species',
                      'strain',
                      'target',
                      'target_type',
                      'tissue',
                      'assay'
                     ] 
        [tables: tables, table: table]
    }
    
    def merge(String table, Long fromId, Long toId) {
        if(request.method == "POST") {
            withForm{
                try {
                    utilityService.mergeRowsInDb(table, fromId, toId)
                    flash.message = "Success merging ${table} from ID#" + fromId + " to ID#" + toId + "!"
                } catch (UtilityException e) {
                    flash.message = e.message
                }
            }
        }
        redirect(action: "mergeForm", params: [table: table])
    }
    
    def serviceReport(String fromId, String toId, String source) {
        def sql = new Sql(dataSource)
        def cmd
        def total_sample_count
        def assay_sample_count
        def cohort_count
        def avg_samples_per_run
        def message
        if (request.method == "POST") {
            if (!fromId) {
                message = "Missing sequence run starting ID!"
            } else if (!toId) {
                message = "Missing sequence run end ID!"
            } else {        
                try {  
                    if (source) {
                        cmd = "select count(*) as total_sample_count from sequencing_experiment e join sequence_run r on e.sequence_run_id = r.id join sample s on e.sample_id = s.id where r.id between ${fromId} and ${toId} and source =" + '"' + source + '"'
                        
                        total_sample_count = sql.rows(cmd)
                        
                        cmd = "select a.name as assay, count(*) as assay_sample_count from sequencing_experiment e join sequence_run r on e.sequence_run_id = r.id join sample s on e.sample_id = s.id join assay a on s.assay_id = a.id where r.id between ${fromId} and ${toId} and source =" + '"' + source + '" group by a.name'

                        assay_sample_count = sql.rows(cmd)                        
                        
                        cmd = "select c.run_id, r.run_name, r.date, rc.samples_in_run, p.name as project_name, e.samples_in_project from (" +
                            "select cohort_id, count(*) as samples_in_project from sequencing_experiment e join sample s on e.sample_id = s.id where sequence_run_id between ${fromId} and ${toId} and source=" + '"' + source + '"' + " group by cohort_id) e " +
                            "left join sequencing_cohort c on e.cohort_id = c.id " +
                            "left join sequence_run r on c.run_id = r.id " +
                            "left join (select sequence_run_id, count(*) as samples_in_run from sequencing_experiment where sequence_run_id between ${fromId} and ${toId} group by sequence_run_id) rc on r.id = rc.sequence_run_id " +
                            "left join project p on c.project_id = p.id order by run_id, project_name"
                        
                        cohort_count = sql.rows(cmd)

                        cmd = "select avg(cnt) as average_sample_per_run from (select count(*) as cnt from sequencing_experiment e join sequence_run r on e.sequence_run_id = r.id join sample s on e.sample_id = s.id where r.id between ${fromId} and ${toId} and source =" + '"' + source + '" group by sequence_run_id) as cnt_table'

                        avg_samples_per_run = sql.rows(cmd)                
                    } else {
                        cmd = "select count(*) as total_sample_count from sequencing_experiment e join sequence_run r on e.sequence_run_id = r.id join sample s on e.sample_id = s.id  where r.id between ${fromId} and ${toId}"
                        
                        total_sample_count = sql.rows(cmd)
                         
                        cmd = "select a.name as assay, count(*) as assay_sample_count from sequencing_experiment e join sequence_run r on e.sequence_run_id = r.id join sample s on e.sample_id = s.id  join assay a on s.assay_id = a.id where r.id between ${fromId} and ${toId} group by a.name"

                        assay_sample_count = sql.rows(cmd)

                        cmd = "select c.run_id, r.run_name, r.date, rc.samples_in_run, p.id as project_id, p.name as project_name, e.samples_in_project from (" +
                            "select cohort_id, count(*) as samples_in_project from sequencing_experiment e join sample s on e.sample_id = s.id  where sequence_run_id between ${fromId} and ${toId} group by cohort_id) e " +
                            "left join sequencing_cohort c on e.cohort_id = c.id " +
                            "left join sequence_run r on c.run_id = r.id " +
                            "left join (select sequence_run_id, count(*) as samples_in_run from sequencing_experiment where sequence_run_id between ${fromId} and ${toId} group by sequence_run_id) rc on r.id = rc.sequence_run_id " +
                            "left join project p on c.project_id = p.id order by run_id, project_name"
                        
                        cohort_count = sql.rows(cmd)

                        cmd = "select avg(cnt) as average_sample_per_run from (select count(*) as cnt from sequencing_experiment e join sequence_run r on e.sequence_run_id = r.id join sample s on e.sample_id = s.id  where r.id between ${fromId} and ${toId} group by sequence_run_id) as cnt_table"

                        avg_samples_per_run = sql.rows(cmd)
                    }
                } catch (Exception e) {
                    log.error "${e}"
                    message = "Error querying statistics between Sequence Run ${fromId} and ${toId}!"
                }

                sql.close()
            }
        }
        
        [total_sample_count: total_sample_count, assay_sample_count: assay_sample_count, cohort_count: cohort_count, avg_samples_per_run: avg_samples_per_run, message: message, fromId: fromId, toId: toId, source: source]
    }
}

