class UrlMappings {

	static mappings = {
		
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

		"/login/form"{
			controller = "auth"
			 action = "form"
		}
		
        "/"{
			controller = "projects"
			action = "index"
		}
		
        "500"(view:'/error')
	}
}
