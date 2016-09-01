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
