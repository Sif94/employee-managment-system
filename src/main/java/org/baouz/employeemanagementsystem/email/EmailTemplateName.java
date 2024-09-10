package org.baouz.employeemanagementsystem.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {

    ACTIVATE_ACCOUNT("activate_account"),
    TASK_ASSIGNATION("task_assignation")
    ;


    private final String name;
    EmailTemplateName(String name) {
        this.name = name;
    }
}