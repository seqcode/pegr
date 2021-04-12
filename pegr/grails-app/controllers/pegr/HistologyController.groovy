package pegr

class HistologyController {
    def show(Long id) {
        redirect(controller: "histologyAdmin", action: "show", id: id)
    }
}