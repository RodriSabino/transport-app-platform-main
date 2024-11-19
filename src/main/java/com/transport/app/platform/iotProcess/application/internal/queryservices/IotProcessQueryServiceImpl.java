package com.transport.app.platform.iotProcess.application.internal.queryservices;

import com.transport.app.platform.iotProcess.domain.model.aggregates.IotProcess;
import com.transport.app.platform.iotProcess.domain.model.queries.*;
import com.transport.app.platform.iotProcess.domain.services.IotProcessQueryService;
import com.transport.app.platform.iotProcess.infrastructure.persistence.jpa.repositories.IotProcessRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IotProcessQueryServiceImpl implements IotProcessQueryService {

    private final IotProcessRepository iotProcessRepository;

    public IotProcessQueryServiceImpl(IotProcessRepository iotProcessRepository) {
        this.iotProcessRepository = iotProcessRepository;
    }

    @Override
    public List<IotProcess> handle(GetAllIotProcessQuery query) {
        return iotProcessRepository.findAll();
    }

    @Override
    public Optional<IotProcess> handle(GetIotProcessByRequestIdQuery query) {
        return iotProcessRepository.findByRequestId(query.requestId());
    }

    @Override
    public Optional<Double> handle(GetTemperatureByRequestIdQuery query) {
        return iotProcessRepository.findTemperatureByRequestId(query.requestId());
    }

    @Override
    public Optional<Double> handle(GetWeightByRequestIdQuery query) {
        return iotProcessRepository.findWeightByRequestId(query.requestId());
    }

}
