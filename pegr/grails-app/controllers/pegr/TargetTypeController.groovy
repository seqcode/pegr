package pegr

class TargetTypeController {
    def show(Long id) {
        redirect(controller: "targetTypeAdmin", action: "show", id: id)
    }
}