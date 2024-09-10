package org.baouz.employeemanagementsystem.email;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailDTO {
    private String to;
    private EmailTemplateName emailTemplate;
    private String subject;
    private Map<String, Object> properties;
}
