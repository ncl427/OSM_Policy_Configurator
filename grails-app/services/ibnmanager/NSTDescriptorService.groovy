package ibnmanager

import grails.gorm.services.Service

@Service(NSTDescriptor)
interface NSTDescriptorService {

    NSTDescriptor get(Serializable id)

    List<NSTDescriptor> list(Map args)

    Long count()

    void delete(Serializable id)

    NSTDescriptor save(NSTDescriptor NSTDescriptor)

}