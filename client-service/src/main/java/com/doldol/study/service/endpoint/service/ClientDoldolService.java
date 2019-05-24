package com.doldol.study.service.endpoint.service;

import com.doldol.study.core.model.ClientService;
import com.doldol.study.core.repository.ClientDoldolServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientDoldolService {
	private final ClientDoldolServiceRepository clientDoldolServiceRepository;
	public Iterable<ClientService> list(Pageable pageable) {
		log.info("Listing all doldol");
		return clientDoldolServiceRepository.findAll(pageable);
	}

}
