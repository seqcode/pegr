package pegr

class UrlMappings {

	static mappings = {
        "500"(view:'/error')        
        "404"(view:'/404')
        "403"(view:'/auth/denied')
        
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
		
        "/"{
			controller = "project"
			action = "index"
		}
	}
}
