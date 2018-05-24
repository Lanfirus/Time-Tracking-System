package ua.training.tts.controller.util;

import ua.training.tts.model.entity.Employee;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AccessRights {
    Employee.AccountRole[] acceptedRoles();
    boolean isAvailableForGuests();
}
