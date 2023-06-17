package com.codechallenge.taxes.dataaccess.repository;

import com.codechallenge.taxes.model.TaxClassCollection;

public interface TaxClassRepository {
    TaxClassCollection findAll();
}
