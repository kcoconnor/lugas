package co.mbwa.lugus;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("co.mbwa.lugus");

        noClasses()
            .that()
            .resideInAnyPackage("co.mbwa.lugus.service..")
            .or()
            .resideInAnyPackage("co.mbwa.lugus.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..co.mbwa.lugus.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
