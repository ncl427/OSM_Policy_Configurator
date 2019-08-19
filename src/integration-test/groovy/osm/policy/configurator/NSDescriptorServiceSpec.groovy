package osm.policy.configurator

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class NSDescriptorServiceSpec extends Specification {

    NSDescriptorService NSDescriptorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new NSDescriptor(...).save(flush: true, failOnError: true)
        //new NSDescriptor(...).save(flush: true, failOnError: true)
        //NSDescriptor NSDescriptor = new NSDescriptor(...).save(flush: true, failOnError: true)
        //new NSDescriptor(...).save(flush: true, failOnError: true)
        //new NSDescriptor(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //NSDescriptor.id
    }

    void "test get"() {
        setupData()

        expect:
        NSDescriptorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<NSDescriptor> NSDescriptorList = NSDescriptorService.list(max: 2, offset: 2)

        then:
        NSDescriptorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        NSDescriptorService.count() == 5
    }

    void "test delete"() {
        Long NSDescriptorId = setupData()

        expect:
        NSDescriptorService.count() == 5

        when:
        NSDescriptorService.delete(NSDescriptorId)
        sessionFactory.currentSession.flush()

        then:
        NSDescriptorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        NSDescriptor NSDescriptor = new NSDescriptor()
        NSDescriptorService.save(NSDescriptor)

        then:
        NSDescriptor.id != null
    }
}
