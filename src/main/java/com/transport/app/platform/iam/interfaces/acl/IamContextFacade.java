package com.transport.app.platform.iam.interfaces.acl;

import com.transport.app.platform.iam.domain.model.commands.SignUpCommand;
import com.transport.app.platform.iam.domain.model.entities.Role;
import com.transport.app.platform.iam.domain.model.queries.GetClientByIdQuery;
import com.transport.app.platform.iam.domain.model.queries.GetClientByUsernameQuery;
import com.transport.app.platform.iam.domain.services.ClientCommandService;
import com.transport.app.platform.iam.domain.services.ClientQueryService;
import com.transport.app.platform.profiles.domain.model.aggregates.Profile;
import com.transport.app.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.transport.app.platform.profiles.domain.model.queries.GetProfileByUserIdQuery;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * IamContextFacade
 * <p>
 *     This class is a facade for the IAM context. It provides a simple interface for other bounded contexts to interact with the
 *     IAM context.
 *     This class is a part of the ACL layer.
 * </p>
 *
 */
@Service
public class IamContextFacade {
    private final ClientCommandService clientCommandService;
    private final ClientQueryService clientQueryService;

    public IamContextFacade(ClientCommandService clientCommandService, ClientQueryService clientQueryService) {
        this.clientCommandService = clientCommandService;
        this.clientQueryService = clientQueryService;
    }


    public Long createUser(String username, String password, List<String> roleNames) {
        var roles = roleNames != null ? roleNames.stream().map(Role::toRoleFromName).toList() : new ArrayList<Role>();
        var signUpCommand = new SignUpCommand(username, password, roles);
        var result = clientCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    public Long fetchUserIdByUsername(String username) {
        var getUserByUsernameQuery = new GetClientByUsernameQuery(username);
        var result = clientQueryService.handle(getUserByUsernameQuery);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    public String fetchUsernameByUserId(Long userId) {
        var getUserByIdQuery = new GetClientByIdQuery(userId);
        var result = clientQueryService.handle(getUserByIdQuery);
        if (result.isEmpty()) return Strings.EMPTY;
        return result.get().getUsername();
    }

    public Profile fetchUserProfileByUserId(Long userId) {
        var getProfileByUserIdQuery = new GetProfileByUserIdQuery(userId);
        var result = clientQueryService.handle(getProfileByUserIdQuery);
        if (result.isEmpty()) return null;
        return result.get();
    }

}