package com.transport.app.platform.profiles.interfaces.rest.resources;

import java.util.Date;

public record CreateProfileResource(
        long id, String firstName, String lastName, String email, String address, Date birthday, long dni, String phone) {}

