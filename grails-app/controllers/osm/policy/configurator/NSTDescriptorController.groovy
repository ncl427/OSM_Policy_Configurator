package osm.policy.configurator

import grails.config.Config
import grails.plugins.rest.client.RestResponse
import grails.validation.ValidationException
import groovy.ClientOSM

import static org.springframework.http.HttpStatus.*

class NSTDescriptorController {

    String url
    String username
    String password

    NSTDescriptorService NSTDescriptorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond NSTDescriptorService.list(params), model:[NSTDescriptorCount: NSTDescriptorService.count()]
    }

    def show(Long id) {
        respond NSTDescriptorService.get(id)
    }

    def create() {

        NSTDescriptor nstDescriptor = new NSTDescriptor(params)

        readPropertyFile();
        ClientOSM clientOSM = new ClientOSM(url, username, password)

        if (nstDescriptor.getName().equalsIgnoreCase("eMBB")) {

            RestResponse res = getBearerToken();
            clientOSM.VNFDescriptor(res.getBody().getAt("id").toString())

        } else if (nstDescriptor.getName().equalsIgnoreCase("URLLC")) {

            RestResponse res = getBearerToken();
            clientOSM.VNFDescriptor(res.getBody().getAt("id").toString())

        } else if (nstDescriptor.getName().equalsIgnoreCase("MassiveIoT")) {

            RestResponse res = getBearerToken();
            clientOSM.VNFDescriptor(res.getBody().getAt("id").toString())
        }

        //respond new NSTDescriptor(params)

    }

    def save(NSTDescriptor NSTDescriptor) {
        if (NSTDescriptor == null) {
            notFound()
            return
        }

        try {
            NSTDescriptorService.save(NSTDescriptor)
        } catch (ValidationException e) {
            respond NSTDescriptor.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'NSTDescriptor.label', default: 'NSTDescriptor'), NSTDescriptor.id])
                redirect NSTDescriptor
            }
            '*' { respond NSTDescriptor, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond NSTDescriptorService.get(id)
    }

    def update(NSTDescriptor NSTDescriptor) {
        if (NSTDescriptor == null) {
            notFound()
            return
        }

        try {
            NSTDescriptorService.save(NSTDescriptor)
        } catch (ValidationException e) {
            respond NSTDescriptor.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'NSTDescriptor.label', default: 'NSTDescriptor'), NSTDescriptor.id])
                redirect NSTDescriptor
            }
            '*'{ respond NSTDescriptor, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        NSTDescriptorService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'NSTDescriptor.label', default: 'NSTDescriptor'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'NSTDescriptor.label', default: 'NSTDescriptor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    void readPropertyFile (){

        Config config = grailsApplication.config
        url = config.getProperty('osm.url')
        username = config.getProperty('osm.username')
        password = config.getProperty('osm.password')
    }
}
