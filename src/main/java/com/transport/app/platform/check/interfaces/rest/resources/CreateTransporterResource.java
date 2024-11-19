package com.transport.app.platform.check.interfaces.rest.resources;

import java.util.Date;

public record CreateTransporterResource(long id, String firstName, String lastName, String email, String address, Date birthday, long dni, String phone) {
}
