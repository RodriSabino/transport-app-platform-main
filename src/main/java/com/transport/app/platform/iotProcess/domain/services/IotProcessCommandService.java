package com.transport.app.platform.iotProcess.domain.services;

import com.transport.app.platform.iotProcess.domain.model.aggregates.IotProcess;
import com.transport.app.platform.iotProcess.domain.model.commands.*;

import java.util.Optional;

public interface IotProcessCommandService {

    Optional<IotProcess> handle(CreateIotProcessCommand command);
    void handle(UpdateTemperatureCommand command);
    void handle(UpdateWeightCommand command);
    Optional<IotProcess> handle(UpdateIotProcessCommand command);

}
