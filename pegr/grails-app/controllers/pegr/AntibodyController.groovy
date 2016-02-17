package pegr

class AntibodyController {
    def list(Integer max) {
        params.max = Math.min(max ?: 15, 100)
        def itemType = ItemType.findByCategory(ItemTypeCategory.ANTIBODY)
        [objectList: Antibody.list(params), objectCount: Antibody.count(), currentType: itemType]
    }
    
    def show(Long id) {
        def antibody = Antibody.get(id)
        if (antibody) {
            [antibody: antibody]
        } else {
            render status: 404
        }
    }

    def edit(Antibody antibody){
        [antibody: antibody]
    }
    
    def update() {
        def antibody = Antibody.get(params.long('id'))
        antibody.properties = params
        try {
            antibodyService.save(antibody)
            flash.message = "Item update!"
            redirect(action: "show", id: params.id)
        }catch(ItemException e) {
            request.message = e.message
            render(view:'edit', model:[antibody: antibody])
        }
    }
    
}