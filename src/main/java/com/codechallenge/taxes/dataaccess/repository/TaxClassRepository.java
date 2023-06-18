package com.codechallenge.taxes.dataaccess.repository;

import com.codechallenge.taxes.dataaccess.repository.exception.DataAccessException;
import com.codechallenge.taxes.model.TaxClassCollection;

public interface TaxClassRepository {
    TaxClassCollection findAll() throws DataAccessException;
}
