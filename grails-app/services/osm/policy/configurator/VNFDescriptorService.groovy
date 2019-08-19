package osm.policy.configurator

import grails.gorm.services.Service

@Service(VNFDescriptor)
interface VNFDescriptorService {

    VNFDescriptor get(Serializable id)

    List<VNFDescriptor> list(Map args)

    Long count()

    void delete(Serializable id)

    VNFDescriptor save(VNFDescriptor VNFDescriptor)

}