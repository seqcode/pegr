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
			controllerGroups[v] = [:]
		}
		
	    grailsApplication.controllerClasses.each{ controller ->
	        def name = controller.name
	        if(name.endsWith('Admin') && name != 'Admin') {
				def key = controller.getStaticPropertyValue('category', AdminCategory) ?: AdminCategory.OTHER
				controllerGroups[key][controller.logicalPropertyName] = controller.naturalName.replace('Admin Controller', '')
	        }
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
        def sample_count
        def samples_per_run
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

                        sample_count = sql.rows(cmd)

                        cmd = "select r.id, r.run_name, r.date, count(*) as sample_count from sequencing_experiment e join sequence_run r on e.sequence_run_id = r.id join sample s on e.sample_id = s.id where r.id between ${fromId} and ${toId} and source =" + '"' + source + '" group by r.id'

                        samples_per_run = sql.rows(cmd)

                        cmd = "select avg(cnt) as average_sample_per_run from (select count(*) as cnt from sequencing_experiment e join sequence_run r on e.sequence_run_id = r.id join sample s on e.sample_id = s.id where r.id between ${fromId} and ${toId} and source =" + '"' + source + '" group by sequence_run_id) as cnt_table'

                        avg_samples_per_run = sql.rows(cmd)                
                    } else {
                        cmd = "select count(*) as total_sample_count from sequencing_experiment e join sequence_run r on e.sequence_run_id = r.id where r.id between ${fromId} and ${toId}"

                        sample_count = sql.rows(cmd)

                        cmd = "select r.id, r.run_name, r.date, count(*) as sample_count from sequencing_experiment e join sequence_run r on e.sequence_run_id = r.id where r.id between ${fromId} and ${toId} group by r.id"

                        samples_per_run = sql.rows(cmd)

                        cmd = "select avg(cnt) as average_sample_per_run from (select count(*) as cnt from sequencing_experiment e join sequence_run r on e.sequence_run_id = r.id where r.id between ${fromId} and ${toId} group by sequence_run_id) as cnt_table"

                        avg_samples_per_run = sql.rows(cmd)
                    }
                } catch (Exception e) {
                    log.error "${e}"
                    message = "Error querying statistics between Sequence Run ${fromId} and ${toId}!"
                }

                sql.close()
            }
        }
        
        [sample_count: sample_count, samples_per_run: samples_per_run, avg_samples_per_run: avg_samples_per_run, message: message, fromId: fromId, toId: toId, source: source]
    }
}

