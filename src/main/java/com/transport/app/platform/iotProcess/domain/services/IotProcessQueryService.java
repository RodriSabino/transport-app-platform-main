package com.transport.app.platform.iotProcess.domain.services;

import com.transport.app.platform.iotProcess.domain.model.aggregates.IotProcess;
import com.transport.app.platform.iotProcess.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface IotProcessQueryService {
    List<IotProcess> handle(GetAllIotProcessQuery query);
    //Optional<IotProcess> handle(GetIotProcessByIdQuery query);
    Optional<IotProcess> handle(GetIotProcessByRequestIdQuery query);
    Optional<Double> handle(GetTemperatureByRequestIdQuery query);
    Optional<Double> handle(GetWeightByRequestIdQuery query);

}
