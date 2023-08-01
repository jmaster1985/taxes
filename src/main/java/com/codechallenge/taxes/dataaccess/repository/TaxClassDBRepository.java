package com.codechallenge.taxes.dataaccess.repository;

import com.codechallenge.taxes.model.tax.TaxClass;
import org.springframework.data.repository.CrudRepository;

public interface TaxClassDBRepository extends CrudRepository<TaxClass, Integer> {
    TaxClass findOneByKey(String key);
}
