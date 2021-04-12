package pegr

class OrganizationController {
    def show(Long id) {
        redirect(controller: "organizationAdmin", action: "show", id: id)
    }
}