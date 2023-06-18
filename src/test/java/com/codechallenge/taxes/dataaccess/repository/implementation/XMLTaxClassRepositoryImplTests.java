package com.codechallenge.taxes.dataaccess.repository.implementation;

import com.codechallenge.taxes.dataaccess.repository.TaxClassRepository;
import com.codechallenge.taxes.dataaccess.repository.exception.DataAccessException;
import com.codechallenge.taxes.model.tax.TaxClass;
import com.codechallenge.taxes.model.tax.TaxClassCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class XMLTaxClassRepositoryImplTests {
    private final Environment environment;
    private final TaxClassRepository taxClassRepository;

    @Autowired
    public XMLTaxClassRepositoryImplTests(Environment environment, TaxClassRepository taxClassRepository) {
        this.environment = environment;
        this.taxClassRepository = taxClassRepository;
    }

    @Test
    public void isTaxClassesXMLFilePathDefinedTest() {
        assertNotNull(environment.getProperty("tax.classes.file"));
    }

    @Test
    public void doesTaxClassesXMLFileExistTest() throws FileNotFoundException {
        File taxClassXMLFile = ResourceUtils.getFile(environment.getProperty("tax.classes.file"));
        assertTrue(taxClassXMLFile.exists());
    }

    @Test
    public void isTaxClassesDefinitionValidTest() throws DataAccessException {
        Map<String, Boolean> seenKeys = new HashMap<>();
        TaxClassCollection taxClassCollection = this.taxClassRepository.findAll();

        for(TaxClass taxClass : taxClassCollection.getTaxClassList()) {
            if (taxClass.getKey() == null || taxClass.getKey().isBlank()) {
                fail("TaxClass with null or empty key detected");
            }

            if (taxClass.getRate() == null) {
                fail("TaxClass with null rate detected");
            }

            if (seenKeys.containsKey(taxClass.getKey())) {
                fail("Duplicate key in TaxClass(es) found: " + taxClass.getKey());
            }

            seenKeys.put(taxClass.getKey(), true);
        }
    }
}
