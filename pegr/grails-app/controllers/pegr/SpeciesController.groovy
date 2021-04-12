package pegr

class SpeciesController {
    def show(Long id) {
        redirect(controller: "speciesAdmin", action: "show", id: id)
    }
}