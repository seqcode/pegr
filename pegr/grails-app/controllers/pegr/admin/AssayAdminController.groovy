package pegr.admin
import pegr.AdminCategory
import pegr.Assay

class AssayAdminController {
    static scaffold = Assay
	public static AdminCategory category = AdminCategory.PROTOCOLS
}