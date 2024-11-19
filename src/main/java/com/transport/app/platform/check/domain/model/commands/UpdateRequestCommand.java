package com.transport.app.platform.check.domain.model.commands;

public record UpdateRequestCommand(
        long requestId,
        Double updatedTemperature,
        Double updatedWeight
) {}