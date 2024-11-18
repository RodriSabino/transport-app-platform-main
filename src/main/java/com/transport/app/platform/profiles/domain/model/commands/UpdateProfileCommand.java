package com.transport.app.platform.profiles.domain.model.commands;

import java.util.Date;

public record UpdateProfileCommand(Long id, String firstName, String lastName, String email, String address, Date birthday, long dni, String phone) {

}
