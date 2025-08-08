package com.example.fantasy.application.service;

import com.example.fantasy.application.port.out.StadiumRepository;
import com.example.fantasy.domain.model.Stadium;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StadiumServiceImpl {
    private final StadiumRepository stadiumRepo;

    @Transactional
    public Stadium findOrCreate(Long id, String name, Integer capacity) {
        log.debug("Looking for stadium with name: {}", name);

        // Try to find by name first (most reliable identifier)
        if (name != null && !name.isEmpty()) {
            var stadiumsByName = stadiumRepo.findByName(name);
            if (!stadiumsByName.isEmpty()) {
                log.debug("Found stadium by name: {}", name);
                return stadiumsByName.get(0);
            }
        }

        // Try to find by ID if name search failed
        if (id != null) {
            var stadiumById = stadiumRepo.findById(id);
            if (stadiumById.isPresent()) {
                log.debug("Found stadium by id: {}", id);
                return stadiumById.get();
            }
        }

        // Create new stadium if not found
        log.info("Creating new stadium: {} (capacity: {})", name, capacity);
        Stadium newStadium = Stadium.builder()
                .id(id)
                .name(name)
                .capacity(capacity)
                .build();

        return stadiumRepo.save(newStadium);
    }
}
