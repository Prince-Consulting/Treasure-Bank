package com.princesoft.treasurebank.architecture;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class AuthArchitectureTest {

    @ArchTest
    static final ArchRule domain_must_not_depend_on_infrastructure =
            noClasses()
                    .that()
                    .resideInAPackage("..auth.domain..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage("..auth.infrastructure..");

    @ArchTest
    static final ArchRule domain_must_not_depend_on_application =
            noClasses()
                    .that()
                    .resideInAPackage("..auth.domain..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage("..auth.application..");

    @ArchTest
    static final ArchRule application_must_not_depend_on_infrastructure =
            noClasses()
                    .that()
                    .resideInAPackage("..auth.application..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage("..auth.infrastructure..");

    @ArchTest
    static final ArchRule infrastructure_must_not_depend_on_application =
            noClasses()
                    .that()
                    .resideInAPackage("..auth.infrastructure..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage("..auth.application..");
}
