package com.transport.app.platform.iotProcess.domain.model.commands;

public record UpdateIotProcessCommand(long requestId, Double temperature, Double weight) {
}
