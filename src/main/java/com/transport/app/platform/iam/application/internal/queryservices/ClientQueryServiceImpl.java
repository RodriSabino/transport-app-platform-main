package com.transport.app.platform.iam.application.internal.queryservices;

import com.transport.app.platform.iam.domain.model.aggregates.Client;
import com.transport.app.platform.iam.domain.model.queries.GetAllClientsQuery;
import com.transport.app.platform.iam.domain.model.queries.GetClientByIdQuery;
import com.transport.app.platform.iam.domain.model.queries.GetClientByUsernameQuery;
import com.transport.app.platform.iam.domain.services.ClientQueryService;
import com.transport.app.platform.iam.infrastructure.persistence.jpa.repositories.ClientRepository;
import com.transport.app.platform.profiles.domain.model.aggregates.Profile;
import com.transport.app.platform.profiles.domain.model.queries.GetProfileByUserIdQuery;
import com.transport.app.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientQueryServiceImpl implements ClientQueryService {
    private final ClientRepository clientRepository;

    private final ProfileRepository profileRepository;

    public ClientQueryServiceImpl(ClientRepository clientRepository, ProfileRepository profileRepository) {
        this.clientRepository = clientRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Client> handle(GetAllClientsQuery query) {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> handle(GetClientByIdQuery query) {
        return clientRepository.findById(query.userId());
    }

    @Override
    public Optional<Client> handle(GetClientByUsernameQuery query) {
        return clientRepository.findByUsername(query.username());
    }
    @Override
    public Optional<Profile> handle(GetProfileByUserIdQuery query) {
        return profileRepository.findByUserId(query.userId());
    }
}
