package com.codechallenge.taxes.usecase;

import com.codechallenge.taxes.dataaccess.repository.TaxClassDBRepository;
import com.codechallenge.taxes.model.tax.TaxClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTaxClassUseCase {
    private final TaxClassDBRepository taxClassDBRepository;

    @Autowired
    CreateTaxClassUseCase(TaxClassDBRepository taxClassDBRepository) {
        this.taxClassDBRepository = taxClassDBRepository;
    }

    public void run(TaxClass taxClass) {
        this.taxClassDBRepository.save(taxClass);
    }
}
