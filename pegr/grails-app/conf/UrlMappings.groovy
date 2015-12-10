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
		
		"/dashboard"{
			controller = "dashboard"
			action = "index"
		}
		
        "/"{
			controller = "auth"
			 action = "form"
		}
        "500"(view:'/error')
	}
}
