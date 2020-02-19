package osm.policy.configurator

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import grails.gorm.transactions.Transactional
import grails.plugins.rest.client.RestResponse
import groovy.ClientOSM

@Transactional
class PolicyConfiguratorService implements GrailsConfigurationAware{

    String url
    String username
    String password

//    VNFDescriptorService vnfDescriptorService
//    NSDescriptorService NSDescriptorService
//    NSTDescriptorService nstDescriptorService
    NSInstanceService nsInstanceService

    def serviceMethod(NSTDescriptor nstDescriptor) {

        ClientOSM clientOSM = new ClientOSM(url, username, password)
        RestResponse res = clientOSM.getBearerToken();

        if (nstDescriptor.getName().equalsIgnoreCase("eMBB")) {

            RestResponse response = clientOSM.VNFDescriptor(res.getBody().getAt("id").toString())

            VNFDescriptor vnfDescriptor = new VNFDescriptor()
            vnfDescriptor.setJson(response.getBody().toString())

            if (response.status == 200){

                response = clientOSM.NSDescriptor(res.getBody().getAt("id").toString())

                NSDescriptor nsDescriptor = new NSDescriptor()
                nsDescriptor.setJson(response.getBody().toString())

                if (response.status == 200){

                    response = clientOSM.NSTDescriptor(res.getBody().getAt("id").toString())
                    nstDescriptor.setJson(response.getBody().toString())

                    if (response.status == 200){
                        response = clientOSM.NSTInstantiate(response.getBody().getAt("id").toString())

                        NSInstance nsInstance = new NSInstance()
                        nsInstance.setJson(response.getBody().toString())
                        nsInstance.setNstDescriptorId(nstDescriptor)
                        nsInstance.getNstDescriptorId().setNsDescriptorId(nsDescriptor)
                        nsInstance.getNstDescriptorId().getNsDescriptorId().setVnfDescriptorId()

                        nsInstanceService.save(nsInstance)
                    }
                }
            }

        } else if (nstDescriptor.getName().equalsIgnoreCase("URLLC")) {

            RestResponse response = clientOSM.VNFDescriptor(res.getBody().getAt("id").toString())

            VNFDescriptor vnfDescriptor = new VNFDescriptor()
            vnfDescriptor.setJson(response.getBody().toString())

            if (response.status == 200){

                response = clientOSM.NSDescriptor(res.getBody().getAt("id").toString())

                NSDescriptor nsDescriptor = new NSDescriptor()
                nsDescriptor.setJson(response.getBody().toString())

                if (response.status == 200){

                    response = clientOSM.NSTDescriptor(res.getBody().getAt("id").toString())
                    nstDescriptor.setJson(response.getBody().toString())

                    if (response.status == 200){
                        response = clientOSM.NSTInstantiate(response.getBody().getAt("id").toString())

                        NSInstance nsInstance = new NSInstance()
                        nsInstance.setJson(response.getBody().toString())
                        nsInstance.setNstDescriptorId(nstDescriptor)
                        nsInstance.getNstDescriptorId().setNsDescriptorId(nsDescriptor)
                        nsInstance.getNstDescriptorId().getNsDescriptorId().setVnfDescriptorId()

                        nsInstanceService.save(nsInstance)
                    }
                }
            }

        } else if (nstDescriptor.getName().equalsIgnoreCase("MassiveIoT")) {

            RestResponse response = clientOSM.VNFDescriptor(res.getBody().getAt("id").toString())

            VNFDescriptor vnfDescriptor = new VNFDescriptor()
            vnfDescriptor.setJson(response.getBody().toString())

            if (response.status == 200){

                response = clientOSM.NSDescriptor(res.getBody().getAt("id").toString())

                NSDescriptor nsDescriptor = new NSDescriptor()
                nsDescriptor.setJson(response.getBody().toString())

                if (response.status == 200){

                    response = clientOSM.NSTDescriptor(res.getBody().getAt("id").toString())
                    nstDescriptor.setJson(response.getBody().toString())

                    if (response.status == 200){
                        response = clientOSM.NSTInstantiate(response.getBody().getAt("id").toString())

                        NSInstance nsInstance = new NSInstance()
                        nsInstance.setJson(response.getBody().toString())
                        nsInstance.setNstDescriptorId(nstDescriptor)
                        nsInstance.getNstDescriptorId().setNsDescriptorId(nsDescriptor)
                        nsInstance.getNstDescriptorId().getNsDescriptorId().setVnfDescriptorId()

                        nsInstanceService.save(nsInstance)
                    }
                }
            }
        }

    }

    @Override
    void setConfiguration(Config config) {

        url = config.getProperty('osm.url')
        username = config.getProperty('osm.username')
        password = config.getProperty('osm.password')
    }
}
