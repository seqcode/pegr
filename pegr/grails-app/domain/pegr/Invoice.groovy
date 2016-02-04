package pegr

class Invoice {
		
    Date date
    String serviceId
    String invoiceNum

    static constraints = {
		serviceId nullable: true, blank: true
        invoiceNum nullable: true, blank: true
        date nullable: true, blank: true
    }
}
