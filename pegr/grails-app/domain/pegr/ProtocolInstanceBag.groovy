package pegr

class ProtocolInstanceBag {

    PrtclInstBagType type
    ProtocolGroup protocolGroup
    List protocolInstances
    Date dateCreated
	Date lastUpdated
	ProtocolStatus status
    
    String getProtocolsDisplay() {
        if(protocolGroup){
            return protocolGroup
        }else {
            String s=""
            protocolInstances.each{
                s.append(it.name + "+")
            }
            if(s.size>0) {
                s = s[0..-1]
            } 
            return s
        }
	}
    
    static hasMany = [protocolInstances: ProtocolInstance]
    
    static constraints = {
        protocolGroup nullable: true
    }
}
