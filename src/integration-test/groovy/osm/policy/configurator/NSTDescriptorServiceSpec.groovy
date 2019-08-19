package osm.policy.configurator

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class NSTDescriptorServiceSpec extends Specification {

    NSTDescriptorService NSTDescriptorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new NSTDescriptor(...).save(flush: true, failOnError: true)
        //new NSTDescriptor(...).save(flush: true, failOnError: true)
        //NSTDescriptor NSTDescriptor = new NSTDescriptor(...).save(flush: true, failOnError: true)
        //new NSTDescriptor(...).save(flush: true, failOnError: true)
        //new NSTDescriptor(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //NSTDescriptor.id
    }

    void "test get"() {
        setupData()

        expect:
        NSTDescriptorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<NSTDescriptor> NSTDescriptorList = NSTDescriptorService.list(max: 2, offset: 2)

        then:
        NSTDescriptorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        NSTDescriptorService.count() == 5
    }

    void "test delete"() {
        Long NSTDescriptorId = setupData()

        expect:
        NSTDescriptorService.count() == 5

        when:
        NSTDescriptorService.delete(NSTDescriptorId)
        sessionFactory.currentSession.flush()

        then:
        NSTDescriptorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        NSTDescriptor NSTDescriptor = new NSTDescriptor()
        NSTDescriptorService.save(NSTDescriptor)

        then:
        NSTDescriptor.id != null
    }
}
