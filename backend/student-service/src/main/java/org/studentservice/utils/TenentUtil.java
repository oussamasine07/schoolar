package org.studentservice.utils;

import org.springframework.stereotype.Component;

@Component
public class TenentUtil {
    public String nameTenent ( String tenentName ) {
        return "tn_" + tenentName.replace(" ", "_");
    }

}
