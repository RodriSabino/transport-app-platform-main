package com.transport.app.platform.profiles.interfaces.rest.resources;

import java.util.Date;

public record ProfileResource(
        Long id,
        String fullName,
        String email,
        String city,
        long dni,
        String phone,
        Date birthday) { }
