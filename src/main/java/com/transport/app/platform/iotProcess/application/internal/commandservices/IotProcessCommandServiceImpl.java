package com.transport.app.platform.iotProcess.application.internal.commandservices;

import com.transport.app.platform.check.infrastructure.persistence.jpa.repositories.RequestRepository;

import com.transport.app.platform.iotProcess.domain.model.aggregates.IotProcess;
import com.transport.app.platform.iotProcess.domain.model.commands.*;
import com.transport.app.platform.iotProcess.domain.services.IotProcessCommandService;
import com.transport.app.platform.iotProcess.infrastructure.persistence.jpa.repositories.IotProcessRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class IotProcessCommandServiceImpl implements IotProcessCommandService {



    private final IotProcessRepository iotProcessRepository;
    private final RequestRepository requestRepository;


    public IotProcessCommandServiceImpl(
            IotProcessRepository iotProcessRepository,
            RequestRepository requestRepository) {
        this.iotProcessRepository = iotProcessRepository;
        this.requestRepository = requestRepository;

    }
    @Override
    public Optional<IotProcess> handle(CreateIotProcessCommand command) {

        var iotProcess = new IotProcess(command);
        iotProcessRepository.save(iotProcess);
        return Optional.of(iotProcess);
    }


    @Override
    public void handle(UpdateTemperatureCommand command) {
        var iotProcess = iotProcessRepository.findByRequestId(command.requestId())
                .orElseThrow(() -> new IllegalArgumentException("IotProcess not found with ID: " + command.requestId()));

        iotProcess.updateTemperature(command.temperature());
        iotProcessRepository.save(iotProcess);

    }

    @Override
    public void handle(UpdateWeightCommand command) {
        var iotProcess = iotProcessRepository.findById(command.requestId())
                .orElseThrow(() -> new IllegalArgumentException("IotProcess not found with ID: " + command.requestId()));

        iotProcess.updateWeight(command.weight());
        iotProcessRepository.save(iotProcess);
    }
    @Override
    public Optional<IotProcess> handle(UpdateIotProcessCommand command) {
        var result = iotProcessRepository.findByRequestId(command.requestId());
        if (result.isEmpty()) throw new IllegalArgumentException("Profile does not exist");
        var iotProcessToUpdate = result.get();
        try {
            var updatedIotProcess = iotProcessRepository.save(iotProcessToUpdate.updateIotProcess(command.weight(), command.temperature()));
            return Optional.of(updatedIotProcess);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating course: " + e.getMessage());
        }
    }










}
