package com.transport.app.platform.iotProcess.domain.model.aggregates;

import com.transport.app.platform.iotProcess.domain.model.commands.CreateIotProcessCommand;
import com.transport.app.platform.iotProcess.domain.model.commands.UpdateIotProcessCommand;
import com.transport.app.platform.iotProcess.domain.model.valueobjects.IotProcessId;
import com.transport.app.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;

@Entity
public class IotProcess extends AuditableAbstractAggregateRoot<IotProcess> {

    @Embedded
    @Column(name = "iot_id")
    private final IotProcessId iotProcessId ;

    private long requestId;

    private double temperature;
    private double weight;

    public IotProcess() {
        this.iotProcessId = new IotProcessId();
    }

    public IotProcess(long requestId, Double temperature, Double weight) {
        this();
        this.requestId = requestId;
        this.temperature = temperature;
        this.weight = weight;
    }
    public IotProcess(CreateIotProcessCommand command) {
        this.iotProcessId = new IotProcessId();
        this.requestId = command.requestId();
        this.temperature = command.temperature();
        this.weight = command.weight();
    }
    public IotProcess updateIotProcess(Double temperature, Double weight) {

        this.temperature = temperature;
        this.weight = weight;
        return this;
    }

    public IotProcessId getIotDeviceId() {
        return iotProcessId;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWeight() {
        return weight;
    }

    public void updateTemperature(double newTemperature) {
        this.temperature = newTemperature;
    }

    public void updateWeight(double newWeight) {
        this.weight = newWeight;
    }

}
