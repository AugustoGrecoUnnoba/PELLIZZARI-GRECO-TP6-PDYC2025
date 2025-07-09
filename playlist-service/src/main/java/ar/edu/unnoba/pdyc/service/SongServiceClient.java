package ar.edu.unnoba.pdyc.service;

import ar.edu.unnoba.pdyc.dto.SongDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceClient {

    @Autowired
    private RestClient restClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    public SongDTO getSongById(Long songId) throws Exception {
        String songServiceUri = getSongServiceUri();
        return restClient.get()
                .uri(songServiceUri + "/api/songs/" + songId)
                .retrieve()
                .body(SongDTO.class);
    }

    private String getSongServiceUri() throws Exception {
        List<ServiceInstance> instances = discoveryClient.getInstances("song-service");
        if (instances.isEmpty()) {
            throw new Exception("No hay instancias disponibles del servicio song-service");
        }
        return instances.get(0).getUri().toString();
    }
}
