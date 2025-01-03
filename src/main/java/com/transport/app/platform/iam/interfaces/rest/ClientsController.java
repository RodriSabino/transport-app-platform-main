package com.transport.app.platform.iam.interfaces.rest;

import com.transport.app.platform.iam.domain.model.queries.GetAllClientsQuery;
import com.transport.app.platform.iam.domain.model.queries.GetClientByIdQuery;
import com.transport.app.platform.iam.domain.model.queries.GetProfileByClientIdQuery;
import com.transport.app.platform.iam.domain.services.ClientQueryService;
import com.transport.app.platform.iam.interfaces.rest.resources.UserResource;
import com.transport.app.platform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.transport.app.platform.profiles.application.internal.outboundservices.acl.ExternalUserService;
import com.transport.app.platform.profiles.domain.model.aggregates.Profile;
import com.transport.app.platform.profiles.domain.model.queries.GetProfileByUserIdQuery;
import com.transport.app.platform.profiles.domain.services.ProfileQueryService;
import com.transport.app.platform.profiles.interfaces.rest.resources.ProfileResource;
import com.transport.app.platform.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class ClientsController {
   private final ClientQueryService clientQueryService;
    private final ExternalUserService externalUserService;
    private final ProfileQueryService profileQueryService;
    public ClientsController(ClientQueryService clientQueryService, ExternalUserService externalUserService, ProfileQueryService profileQueryService) {

        this.clientQueryService = clientQueryService;
        this.externalUserService = externalUserService;
        this.profileQueryService = profileQueryService;
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllClientsQuery();
        var users = clientQueryService.handle(getAllUsersQuery);
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetClientByIdQuery(userId);
        var user = clientQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) return ResponseEntity.notFound().build();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

}
