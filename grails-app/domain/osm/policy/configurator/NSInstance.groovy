package osm.policy.configurator

class NSInstance {

    Long id
    String name
    String description
    String refId
    String json
    NSTDescriptor nstDescriptorId

    static mapping = {
        id column: "nsInstanceId", generator: "sequence"
    }

    static constraints = {

        name(blank: false, nullable: false)
        description(blank: true, nullable: true)
        refId(blank: true, nullable: true)
        json(blank: true, nullable: true)
        nstDescriptorId(blank: true, nullable: true)
    }
}
