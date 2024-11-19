package com.transport.app.platform.iotProcess.interfaces.acl;

import com.transport.app.platform.iotProcess.domain.model.commands.CreateIotProcessCommand;
import com.transport.app.platform.iotProcess.domain.model.commands.UpdateTemperatureCommand;
import com.transport.app.platform.iotProcess.domain.model.commands.UpdateWeightCommand;
import com.transport.app.platform.iotProcess.domain.services.IotProcessCommandService;
import com.transport.app.platform.iotProcess.domain.services.IotProcessQueryService;
import org.springframework.stereotype.Service;

@Service
public class IotProcessContextFacade {
    private final IotProcessCommandService iotProcessCommandService;
    private final IotProcessQueryService iotProcessQueryService;

    public IotProcessContextFacade(IotProcessCommandService iotProcessCommandService, IotProcessQueryService iotProcessQueryService) {
        this.iotProcessCommandService = iotProcessCommandService;
        this.iotProcessQueryService = iotProcessQueryService;
    }

    public long createIotProcess(long requestId,  Double temperature, Double weight) {
        var createIotProcessCommand = new CreateIotProcessCommand(requestId, temperature, weight);
        var iotProcess = iotProcessCommandService.handle(createIotProcessCommand);
        if (iotProcess.isEmpty()) return 0L;
        return iotProcess.get().getId();
    }

    public void updateTemperature(long requestId, Double temperature) {
        var updateTemperatureCommand = new UpdateTemperatureCommand(requestId, temperature);
        iotProcessCommandService.handle(updateTemperatureCommand);
    }

    public void updateWeight(long requestId, Double weight) {
        var updateWeightCommand = new UpdateWeightCommand(requestId, weight);
        iotProcessCommandService.handle(updateWeightCommand);
    }


}
