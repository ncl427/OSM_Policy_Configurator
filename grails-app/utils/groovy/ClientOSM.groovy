package groovy

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import org.springframework.web.client.RestClientException

class ClientOSM {

    RestBuilder restBuilder

    String url
    String username
    String password

    ClientOSM(String osmUrl, String osmUsername, String osmPassword) {

        restBuilder = new RestBuilder()
        url = osmUrl
        username = osmUsername
        password = osmPassword
    }

    def sliceSelector(String sliceServiceType) {

        if (sliceServiceType.trim().equalsIgnoreCase("eMBB")) {

            RestResponse res = getBearerToken();
            VNFDescriptor(res.getBody().getAt("id").toString())

        } else if (sliceServiceType.trim().equalsIgnoreCase("URLLC")) {

            RestResponse res = getBearerToken();
            VNFDescriptor(res.getBody().getAt("id").toString())

        } else if (sliceServiceType.trim().equalsIgnoreCase("MassiveIoT")) {

            RestResponse res = getBearerToken();
            VNFDescriptor(res.getBody().getAt("id").toString())
        }
    }

    RestResponse getBearerToken() {

        try {

            def res = restBuilder.get("$url/osm/admin/v1/tokens") {
                auth("$username", "$password")
                accept("application/json")
                contentType("application/json")
            }

            return res

        } catch (RestClientException error) {

            println("RestClientException" + error)
            return null
        }
    }

    RestResponse VNFDescriptor(String bearer) {

        try {

            def res = restBuilder.post("$url/osm/vnfpkgm/v1/vnf_packages") {
                contentType("application/json")
                accept("application/json")
                header("Bearer", $bearer)

                json("""{
                    "vnfd-catalog": {
                        "vnfd": [
                            {
                                "id": "slice_vnfd",
                                "name": "slice_vnfd",
                                "short-name": "slice_vnfd",
                                "description": "Simple VNF example with a ubuntu 16.04 and 2 interfaces (mgmt and data)",
                                "vendor": "OSM",
                                "version": "1.0",
                                "mgmt-interface": {
                                    "cp": "eth0"
                                },
                                "vdu": [
                                    {
                                        "id": "slice_vnfd-VM",
                                        "name": "slice_vnfd-VM",
                                        "description": "slice_vnfd-VM",
                                        "count": 1,
                                        "vm-flavor": {
                                            "vcpu-count": 1,
                                                "memory-mb": 1024,
                                                "storage-gb": 10
                                            },
                                        "image": "US1604",
                                        "interface": [
                                            {
                                                "name": "eth0",
                                                "type": "EXTERNAL",
                                                "virtual-interface": {
                                                    "type": "VIRTIO",
                                                    "bandwidth": "0",
                                                    "vpci": "0000:00:0a.0"
                                                },
                                                "external-connection-point-ref": "eth0"
                                            },
                                            {
                                                "name": "eth1",
                                                "type": "EXTERNAL",
                                                "external-connection-point-ref": "eth1",
                                                "virtual-interface": {
                                                    "type": "VIRTIO",
                                                    "bandwidth": "0",
                                                    "vpci": "0000:00:0a.0"
                                                }
                                            }
                                        ]
                                    }
                                ],
                                "connection-point": [
                                    {
                                        "name": "eth0",
                                        "type": "VPORT"
                                    },
                                    {
                                        "name": "eth1",
                                        "type": "VPORT"
                                    }
                                ]
                            }
                        ]
                    }
                }"""
                )
            }

            return res

        } catch (RestClientException error) {

            println("RestClientException" + error)
            return null
        }
    }

    RestResponse NSDescriptor(String sliceServiceType) {

        try {

            def res = restBuilder.post("$url/osm/nsd/v1/ns_descriptors") {
                contentType("application/json")
                accept("application/json")
                header("Bearer", $bearer)

                json("""{
                    "nsd-catalog": {
                        "nsd": [
                            {
                                "id": "slice_nsd",
                                "name": "slice_nsd",
                                "short-name": "slice_ns",
                                "description": "NetServiceDescriptor with 2 vnfs and 2 vld (mgmt and data networks)",
                                "vendor": "OSM",
                                "version": "1.0",
                                "constituent-vnfd": [
                                    {
                                        "member-vnf-index": 1,
                                        "vnfd-id-ref": "slice_hackfest_vnfd"
                                    },
                                    {
                                        "member-vnf-index": 2,
                                        "vnfd-id-ref": "slice_hackfest_vnfd"
                                    }
                                ],
                                "connection-point": [
                                    {
                                        "name": "nsd_cp_mgmt",
                                        vld-id-ref": "nsd_vnfd_vld_mgmt"
                                    },
                                    {
                                        "name": "nsd_cp_data",
                                        "vld-id-ref": "nsd_vnfd_vld_data"
                                    }
                                ],
                                "vld": [
                                    {
                                        "id": "nsd_vnfd_vld_mgmt",
                                        "name": "nsd_vnfd_vld_mgmt",
                                        "short-name": "nsd_vnfd_vld_mgmt",
                                        "type": "ELAN",
                                        "mgmt-network": "true",
                                        "vnfd-connection-point-ref": [
                                            {
                                                "member-vnf-index-ref": 1,
                                                "vnfd-id-ref": "slice_hackfest_vnfd",
                                                "vnfd-connection-point-ref": "eth0"
                                            },
                                            {
                                                "member-vnf-index-ref": 2,
                                                "vnfd-id-ref": "slice_hackfest_vnfd",
                                                "vnfd-connection-point-ref": "eth0"
                                            }
                                        ]
                                    },
                                    {
                                        "id": "nsd_vnfd_vld_data",
                                        "name": "nsd_vnfd_vld_data",
                                        "short-name": "nsd_vnfd_vld_data",
                                        "type": "ELAN",
                                        "mgmt-network": "false",
                                        "vnfd-connection-point-ref": [
                                            {
                                                "member-vnf-index-ref": 1,
                                                "vnfd-id-ref": "slice_hackfest_vnfd",
                                                "vnfd-connection-point-ref": "eth1"
                                            },
                                            {
                                                "member-vnf-index-ref": 2,
                                                "vnfd-id-ref": "slice_hackfest_vnfd",
                                                "vnfd-connection-point-ref": "eth1"
                                            }
                                        ]
                                    }
                                ]
                            }
                        ]
                    }
                }"""
                )
            }

            return res

        } catch (RestClientException error) {

            println("RestClientException" + error)
            return null
        }
    }

    RestResponse NSTDescriptor(String sliceServiceType) {

        try {

            def res = restBuilder.post("$url/osm/nst/v1/netslice_templates") {
                contentType("application/json")
                accept("application/json")
                header("Bearer", $bearer)

                json("""{
                    "nst": [
                            {
                                "id": "slice_hackfest_nst",
                                "name": "slice_hackfest_nst",
                                "SNSSAI-identifier": {
                                    "slice-service-type": "eMBB"
                                },
                                "quality-of-service": {
                                    "id": 1
                                },
                                "netslice-subnet": [
                                    {
                                        "id": "slice_hackfest_nsd_1",
                                        "is-shared-nss": "false",
                                        "description": "NetSlice Subnet (service) composed by 2 vnfs and 4 cp (2 mgmt and 2 data)",
                                        "nsd-ref": "slice_hackfest_nsd"
                                    },
                                    {
                                        "id": "slice_hackfest_nsd_2",
                                        "is-shared-nss": "false",
                                        "description": "NetSlice Subnet (service) composed by 2 vnfs and 4 cp (2 mgmt and 2 data)",
                                        "nsd-ref": "slice_hackfest_nsd"
                                    }
                                ],
                                "netslice-vld": [
                                    {
                                        "id": "slice_hackfest_vld_mgmt",
                                        "name": "slice_hackfest_vld_mgmt",
                                        "type": "ELAN",
                                        "mgmt-network": "true",
                                        "nss-connection-point-ref": [
                                            {
                                                "nss-ref": "slice_hackfest_nsd_1",
                                                "nsd-connection-point-ref": "nsd_cp_mgmt"
                                            },
                                            {
                                                "nss-ref": "slice_hackfest_nsd_2",
                                                "nsd-connection-point-ref": "nsd_cp_mgmt"
                                            }
                                        ]
                                    },
                                    {
                                         "id": "slice_hackfest_vld_data",
                                         "name": "slice_hackfest_vld_data",
                                         "type": "ELAN",
                                         "nss-connection-point-ref": [
                                            {
                                                "nss-ref": "slice_hackfest_nsd_1",
                                                "nsd-connection-point-ref": "nsd_cp_data"
                                            },
                                            {
                                                "nss-ref": "slice_hackfest_nsd_2",
                                                "nsd-connection-point-ref": "nsd_cp_data"
                                            }
                                         ]
                                    }
                                ]
                             }
                    ]
                    }"""
                )
            }

            return res

        } catch (RestClientException error) {

            println("RestClientException" + error)
            return null
        }
    }

    RestResponse delete_eMBB_VNFD(String refId) {

        try {

            def res = restBuilder.delete("$url/osm/vnfpkgm/v1/vnf_packages/${refId.trim()}") {
                contentType("application/json")
                accept("application/json")
                header("Bearer", $bearer)
            }

            return res

        } catch (RestClientException error) {

            println("RestClientException" + error)
            return null
        }
    }

    RestResponse delete_eMBB_NSD(String refId) {

        try {

            def res = restBuilder.delete("$url/osm/nsd/v1/ns_descriptors/${refId.trim()}") {
                contentType("application/json")
                accept("application/json")
                header("Bearer", $bearer)
            }

            return res

        } catch (RestClientException error) {

            println("RestClientException" + error)
            return null
        }
    }

    RestResponse delete_eMBB_NSTD(String refId) {

        try {

            def res = restBuilder.delete("$url/osm/nst/v1/netslice_templates/${refId.trim()}") {
                contentType("application/json")
                accept("application/json")
                header("Bearer", $bearer)
            }

            return res

        } catch (RestClientException error) {

            println("RestClientException" + error)
            return null
        }
    }
}
