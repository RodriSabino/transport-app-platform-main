package com.transport.app.platform.iotProcess.domain.model.commands;

public record CreateIotProcessCommand(long requestId, Double temperature, Double weight) {
}
