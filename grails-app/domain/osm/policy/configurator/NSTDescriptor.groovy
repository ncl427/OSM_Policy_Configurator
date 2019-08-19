package osm.policy.configurator

class NSTDescriptor {

    Long id
    String name
    String description
    String refId

    static mapping = {
        id column: "nstDescriptorId", generator: "sequence"
    }

    static constraints = {

        name(blank: false, nullable: false)
        description(blank: true, nullable: true)
        refId(blank: true, nullable: true)
    }
}
