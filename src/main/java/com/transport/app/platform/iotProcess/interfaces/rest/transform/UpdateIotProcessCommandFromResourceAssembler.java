package com.transport.app.platform.iotProcess.interfaces.rest.transform;

import com.transport.app.platform.iotProcess.domain.model.commands.UpdateIotProcessCommand;
import com.transport.app.platform.iotProcess.interfaces.rest.resources.UpdateIotProcessResource;

public class UpdateIotProcessCommandFromResourceAssembler {
    public static UpdateIotProcessCommand toCommandFromResource(long requestId, UpdateIotProcessResource resource) {
        return new UpdateIotProcessCommand(
                requestId,
                resource.temperature(),
                resource.weight()
        );
    }
}
