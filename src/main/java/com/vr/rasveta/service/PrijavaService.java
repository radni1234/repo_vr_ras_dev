package com.vr.rasveta.service;

import com.vr.rasveta.domain.Prijava;
import com.vr.rasveta.domain.PrijavaStatus;
import com.vr.rasveta.domain.Status;
import com.vr.rasveta.domain.Stub;
import com.vr.rasveta.repository.PrijavaRepository;
import com.vr.rasveta.repository.PrijavaStatusRepository;
import com.vr.rasveta.repository.StatusRepository;
import com.vr.rasveta.repository.StubRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Service
@Transactional
public class PrijavaService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final PrijavaRepository prijavaRepository;

    private final PrijavaStatusRepository prijavaStatusRepository;

    private final StatusRepository statusRepository;

    private final StubRepository stubRepository;

    public PrijavaService(PrijavaRepository prijavaRepository,
                          PrijavaStatusRepository prijavaStatusRepository,
                          StatusRepository statusRepository,
                          StubRepository stubRepository) {
        this.prijavaRepository = prijavaRepository;
        this.prijavaStatusRepository = prijavaStatusRepository;
        this.statusRepository = statusRepository;
        this.stubRepository = stubRepository;
    }

    public Prijava novaPrijava(Prijava prijava){
        log.debug("REST nova prijava!!!");

        //status 2 - prijava kvara
        Status status = statusRepository.getOne(2l);

        //nova prijava
        Prijava result = prijavaRepository.save(prijava);

        //prva stavka prijave
        PrijavaStatus prijavaStatus = new PrijavaStatus();
        prijavaStatus.setPrijava(result);
        prijavaStatus.setOpis(result.getOpis());
        prijavaStatus.setStatus(status);
        prijavaStatus.setDatum(Instant.now());
        PrijavaStatus result2 = prijavaStatusRepository.save(prijavaStatus);

        //promena statusa stuba
        Stub stub = stubRepository.getOne(prijava.getStub().getId());
        stub.setStatus(status);
        Stub result3 = stubRepository.save(stub);

        return result;

    }

}
