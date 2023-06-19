package com.codechallenge.taxes.dataaccess.repository;

import com.codechallenge.taxes.dataaccess.repository.exception.DataAccessException;
import com.codechallenge.taxes.model.tax.TaxClass;
import com.codechallenge.taxes.model.tax.TaxClassCollection;

public interface TaxClassRepository {
    TaxClassCollection findAll() throws DataAccessException;
    TaxClass findByKey(String taxClassKey) throws DataAccessException;
}
