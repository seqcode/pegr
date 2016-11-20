class UrlMappings {

	static mappings = {
        "500"(view:'/error')        
        "404"(view:'/404')
        "403"(view:'/auth/denied')
        
		"/login/form"{
			controller = "auth"
			 action = "form"
		}
        
        "/login/auth"{
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
		

	}
}
