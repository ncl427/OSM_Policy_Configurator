package osm.policy.configurator

class NSTDescriptor {

    Long id
    String name
    String description
    String refId
    String json
    NSDescriptor nsDescriptorId

    static mapping = {
        id column: "nstDescriptorId", generator: "sequence"
    }

    static constraints = {

        name(blank: false, nullable: false)
        description(blank: true, nullable: true)
        refId(blank: true, nullable: true)
        json(blank: true, nullable: true)
        nsDescriptorId(blank: true, nullable: true)
    }
}
