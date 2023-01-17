package com.example.securitymaster.security.util;

import org.springframework.context.annotation.Bean;

public class RolesHierarchyBuilder {
    StringBuilder stringBuilder=new StringBuilder();

    @Bean
    public RolesHierarchyBuilder append(String upLineRole,String downLineRole){
        stringBuilder.append(
                String.format("ROLE_%s > ROLE_%s\n",upLineRole,downLineRole)
        );
                return this;
    }

    public String build(){
        return stringBuilder.toString();
    }
}
