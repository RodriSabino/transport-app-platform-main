package com.transport.app.platform.iotProcess.domain.model.commands;

import com.transport.app.platform.iotProcess.domain.model.valueobjects.IotProcessId;

public record UpdateWeightCommand(long requestId, Double weight) {

}