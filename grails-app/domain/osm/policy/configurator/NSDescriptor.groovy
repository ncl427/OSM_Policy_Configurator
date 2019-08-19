package osm.policy.configurator

class NSDescriptor {

    Long id
    String name
    String description
    String refId

    static mapping = {
        id column: "nsDescriptorId", generator: "sequence"
    }

    static constraints = {

        name(blank: false, nullable: false)
        description(blank: true, nullable: true)
        refId(blank: true, nullable: true)
    }
}
