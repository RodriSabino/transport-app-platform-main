package com.transport.app.platform.check.application.internal.outboundservices.acl;


import com.transport.app.platform.iotProcess.domain.model.valueobjects.IotProcessId;
import com.transport.app.platform.iotProcess.interfaces.acl.IotProcessContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalIotProcessService {
    private IotProcessContextFacade iotProcessContextFacade;

    public ExternalIotProcessService(IotProcessContextFacade iotProcessContextFacade) {
        this.iotProcessContextFacade = iotProcessContextFacade;
    }

    public Optional<IotProcessId> createIotProcess(Long requestid, Double temperature, Double weight) {
        var iotProcessId = iotProcessContextFacade.createIotProcess(requestid, temperature, weight);
        if (iotProcessId == 0L) return Optional.empty();
        return Optional.of(new IotProcessId(iotProcessId));
    }

    public void updateIotProcess(long requestId, Double updatedTemperature, Double updatedWeight) {
        if (updatedTemperature != null) {
            iotProcessContextFacade.updateTemperature(requestId, updatedTemperature);
        }
        if (updatedWeight != null) {
            iotProcessContextFacade.updateWeight(requestId, updatedWeight);
        }
    }

}
