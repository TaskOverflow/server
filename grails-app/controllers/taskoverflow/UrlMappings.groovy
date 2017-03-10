package taskoverflow

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "question", view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
