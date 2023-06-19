package com.codechallenge.taxes.dataaccess.repository.implementation;

import com.codechallenge.taxes.dataaccess.repository.TaxClassRepository;
import com.codechallenge.taxes.dataaccess.repository.exception.DataAccessException;
import com.codechallenge.taxes.model.tax.TaxClass;
import com.codechallenge.taxes.model.tax.TaxClassCollection;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.File;

@Service
public class XMLTaxClassRepositoryImpl implements TaxClassRepository {

    private final Environment environment;

    @Autowired
    public XMLTaxClassRepositoryImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public TaxClassCollection findAll() throws DataAccessException {
        try {
            File taxClassXMLFile = ResourceUtils.getFile(environment.getProperty("tax.classes.file"));
            JAXBContext jaxbContext = JAXBContext.newInstance(TaxClassCollection.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return  (TaxClassCollection) jaxbUnmarshaller.unmarshal(taxClassXMLFile);
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public TaxClass findByKey(String taxClassKey) throws DataAccessException {
        TaxClassCollection taxClassCollection = this.findAll();

        for (TaxClass taxClass : taxClassCollection.getTaxClassList()) {
            if (taxClass.getKey().equals(taxClassKey)) {
                return taxClass;
            }
        }

        return null;
    }
}
