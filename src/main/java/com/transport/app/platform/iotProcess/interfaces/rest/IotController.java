package com.transport.app.platform.iotProcess.interfaces.rest;


import com.transport.app.platform.iotProcess.domain.model.queries.GetIotProcessByRequestIdQuery;
import com.transport.app.platform.iotProcess.domain.services.IotProcessCommandService;
import com.transport.app.platform.iotProcess.domain.services.IotProcessQueryService;
import com.transport.app.platform.iotProcess.interfaces.rest.resources.CreateIotProcessResource;
import com.transport.app.platform.iotProcess.interfaces.rest.resources.IotProcessResource;
import com.transport.app.platform.iotProcess.interfaces.rest.resources.UpdateIotProcessResource;
import com.transport.app.platform.iotProcess.interfaces.rest.transform.CreateIotProcessCommandFromResourceAssembler;
import com.transport.app.platform.iotProcess.interfaces.rest.transform.IotProcessResourceFromEntityAssembler;
import com.transport.app.platform.iotProcess.interfaces.rest.transform.UpdateIotProcessCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/iot-process", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Iot-process", description = "IotProcess Management Endpoints")
public class IotController {

    private final IotProcessCommandService iotProcessCommandService;
    private final IotProcessQueryService iotProcessQueryService;

    public IotController(IotProcessCommandService iotProcessCommandService, IotProcessQueryService iotProcessQueryService) {
        this.iotProcessCommandService = iotProcessCommandService;
        this.iotProcessQueryService = iotProcessQueryService;
    }

    @PostMapping
    public ResponseEntity<IotProcessResource> createIotProcess(@RequestBody CreateIotProcessResource resource) {
        var createIotProcessCommand = CreateIotProcessCommandFromResourceAssembler.toCommandFromResource(resource);
        var iotProcess = iotProcessCommandService.handle(createIotProcessCommand);
        if (iotProcess.isEmpty()) return ResponseEntity.badRequest().build();
        var iotProcessResource = IotProcessResourceFromEntityAssembler.toResourceFromEntity(iotProcess.get());
        return new ResponseEntity<>(iotProcessResource, HttpStatus.CREATED);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<IotProcessResource> getIotProcessByRequestId(@PathVariable long requestId) {
        // Crea el query utilizando el requestId
        var query = new GetIotProcessByRequestIdQuery(requestId);
        var iotProcess = iotProcessQueryService.handle(query);
        if (iotProcess.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var iotProcessResource = IotProcessResourceFromEntityAssembler.toResourceFromEntity(iotProcess.get());
        return ResponseEntity.ok(iotProcessResource);
    }
    @PutMapping("/{requestId}")
    public ResponseEntity<IotProcessResource> updateIotProcessByRequestId(
            @PathVariable long requestId,
            @RequestBody @Valid UpdateIotProcessResource resource) {

        var command = UpdateIotProcessCommandFromResourceAssembler.toCommandFromResource(requestId, resource);
        var updatedIotProcess = iotProcessCommandService.handle(command);
        if (updatedIotProcess.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var iotProcessResource = IotProcessResourceFromEntityAssembler.toResourceFromEntity(updatedIotProcess.get());
        return ResponseEntity.ok(iotProcessResource);
    }
}
