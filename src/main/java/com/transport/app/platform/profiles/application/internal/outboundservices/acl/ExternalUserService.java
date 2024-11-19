package com.transport.app.platform.profiles.application.internal.outboundservices.acl;

import com.transport.app.platform.iam.interfaces.acl.IamContextFacade;
import com.transport.app.platform.profiles.domain.model.aggregates.Profile;
import com.transport.app.platform.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalUserService {

    private IamContextFacade iamContextFacade;

    public ExternalUserService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public Profile getProfileByUserId(Long userId) {
        return iamContextFacade.fetchUserProfileByUserId(userId);
    }

}
