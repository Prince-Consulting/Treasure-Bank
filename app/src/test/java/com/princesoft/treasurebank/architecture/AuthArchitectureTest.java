package com.princesoft.treasurebank.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = {
        "com.princesoft.auth_api",
        "com.princesoft.auth_internal"
})
public class AuthArchitectureTest {

    @ArchTest
    static final ArchRule application_must_not_depend_on_infrastructure =
            noClasses()
                    .that()
                    .resideInAPackage("..auth_internal.application.impl..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage("..auth_internal.infrastructure..");

    @ArchTest
    static final ArchRule auth_api_must_not_depend_on_internal =
            noClasses()
                    .that()
                    .resideInAPackage("..auth_api.api..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(
                            "..auth_internal.application.impl..",
                            "..auth_internal.domain..",
                            "..auth_internal.infrastructure..",
                            "..auth_internal.config.."
                    );

    @ArchTest
    static final ArchRule internal_implementation_must_not_be_used_as_public_api =
            noClasses()
                    .that()
                    .resideOutsideOfPackage("..auth_internal..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage("..auth_internal..");
}
