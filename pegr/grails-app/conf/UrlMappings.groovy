class UrlMappings {

	static mappings = {
        
		"/login/form"{
			controller = "auth"
			 action = "form"
		}
        
        "/login/full"{
			controller = "auth"
			 action = "form"
		}
        
        "/api/stats"(resources:'statsAPI', includes:['save'])
        
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
		
        "/"{
			controller = "project"
			action = "index"
		}
		
        "500"(view:'/error')
        
        "404"(view:'/404')
	}
}
