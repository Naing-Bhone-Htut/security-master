package com.example.securitymaster.security;

import com.example.securitymaster.security.util.RolesHierarchyBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import static com.example.securitymaster.security.SecurityRoles.*;

@Configuration
public class RoleHierarchyConfiguration {
    @Bean
    public RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy=new RoleHierarchyImpl();
        roleHierarchy.setHierarchy(
                new RolesHierarchyBuilder()
                        .append(ROLES_ADMIN,CUSTOMERS_ADMIN)
                        .append(CUSTOMERS_ADMIN,CUSTOMERS_CREATE)
                        .append(CUSTOMERS_ADMIN,CUSTOMERS_READ)
                        .append(CUSTOMERS_ADMIN,CUSTOMERS_DELETE)
                        .append(CUSTOMERS_ADMIN,CUSTOMERS_PAG_VIEW)

                        .append(ROLES_ADMIN,EMPLOYEES_ADMIN)
                        .append(EMPLOYEES_ADMIN,EMPLOYEES_CREATE)
                        .append(EMPLOYEES_ADMIN,EMPLOYEES_DELETE)
                        .append(EMPLOYEES_ADMIN,EMPLOYEES_READ)
                        .append(EMPLOYEES_ADMIN,EMPLOYEES_PAG_VIEW)

                        .append(ROLES_ADMIN,DEPARTMENTS_ADMIN)
                        .append(DEPARTMENTS_ADMIN,DEPARTMENTS_READ)
                        .append(DEPARTMENTS_ADMIN,DEPARTMENTS_CREATE)
                        .append(DEPARTMENTS_ADMIN,DEPARTMENTS_DELETE)
                        .append(DEPARTMENTS_ADMIN,DEPARTMENTS_PAG_VIEW)

                        .build()
        );
        return roleHierarchy;
    }

}
