package com.example.sessionreplication;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.session.ReactiveMapSessionRepository;
import org.springframework.session.ReactiveSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;

import static com.example.sessionreplication.SessionreplicationApplication.MAP_CONFIG_NAME;

@EnableSpringWebSession
public class SessionConfig {

    // Auto configured. See https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-hazelcast.html
    @Autowired
    HazelcastInstance hazelcastInstance;

    @Bean
    public ReactiveSessionRepository reactiveSessionRepository() {
        final IMap<String, Session> map = hazelcastInstance.getMap(MAP_CONFIG_NAME);
        return new ReactiveMapSessionRepository(map);
    }
}
