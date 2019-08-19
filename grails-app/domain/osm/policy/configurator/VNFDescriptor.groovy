package osm.policy.configurator

class VNFDescriptor {

    Long id
    String name
    String description
    String refId

    static mapping = {
        id column: "vnfDescriptorId", generator: "sequence"
    }

    static constraints = {

        name(blank: false, nullable: false)
        description(blank: true, nullable: true)
        refId(blank: true, nullable: true)
    }
}
