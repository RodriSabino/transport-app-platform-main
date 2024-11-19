package com.transport.app.platform.iotProcess.domain.model.commands;

import com.transport.app.platform.iotProcess.domain.model.aggregates.IotProcess;

public record UpdateTemperatureCommand(long requestId, Double temperature) {

}