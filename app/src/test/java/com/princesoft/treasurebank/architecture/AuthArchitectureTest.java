package com.princesoft.treasurebank.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.transaction.annotation.Transactional;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = {
        "com.princesoft.auth_api",
        "com.princesoft.auth_internal",
        "com.princesoft.shared_kernel"
})
public class AuthArchitectureTest {

    //-------------- AUTH ARCH UNIT TEST --------------------
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

    //---------------- SHARED KERNEL ARCH UNIT TEST --------------------------
//    @ArchTest
//    static final ArchRule shared_kernel_must_not_depend_on_modules =
//            noClasses()
//                    .that()
//                    .resideInAnyPackage("..shared.kernel..")
//                    .should()
//                    .dependOnClassesThat()
//                    .resideInAnyPackage(
//                            "..auth_api..",
//                            "..auth_internal.."
//                    );

    //---------------- DATABASE OWNERSHIP ARCH UNIT TEST --------------------------
//    @ArchTest
//    static final ArchRule auth_persistence_must_stay_inside_auth_internal =
//            noClasses()
//                    .that()
//                    .resideOutsideOfPackage("..auth_internal..")
//                    .should()
//                    .dependOnClassesThat()
//                    .resideInAnyPackage("..auth_internal.infrastructure.persistence..");
//

    //---------------- AUTH_API DATA BOUNDARY ARCH UNIT TEST --------------------------
    @ArchTest
    static final ArchRule auth_api_must_not_expose_internal_data_access =
            noClasses()
                    .that()
                    .resideInAnyPackage("..auth_api..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(
                            "..auth_internal.domain..",
                            "..auth_internal.application.impl..",
                            "..auth_internal.infrastructure.."
                    );

    //---------------- TRANSACTIONAL ARCH UNIT TEST --------------------------
//    @ArchTest
//    static final ArchRule transactions_must_be_defined_in_application =
//            classes()
//                    .that()
//                    .areAnnotatedWith(Transactional.class)
//                    .should()
//                    .resideInAnyPackage("..auth_internal.application.service.."
//                    );

}
