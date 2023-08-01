package com.codechallenge.taxes.usecase;

import com.codechallenge.taxes.dataaccess.repository.TaxClassDBRepository;
import com.codechallenge.taxes.exceptionhandler.ExceptionHandler;
import com.codechallenge.taxes.model.cart.CartItem;
import com.codechallenge.taxes.model.tax.TaxClass;
import com.codechallenge.taxes.usecase.exception.InvalidInputException;
import com.codechallenge.taxes.usecase.exception.UseCaseRunFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculateCartItemUseCase {
    private final TaxClassDBRepository taxClassDBRepository;
    private final ExceptionHandler exceptionHandler;
    private final RoundUpCalculatedTaxUseCase roundUpCalculatedTaxUseCase;

    @Autowired
    public CalculateCartItemUseCase(
            TaxClassDBRepository taxClassDBRepository,
            ExceptionHandler exceptionHandler,
            RoundUpCalculatedTaxUseCase roundUpCalculatedTaxUseCase
    ) {
        this.taxClassDBRepository = taxClassDBRepository;
        this.exceptionHandler = exceptionHandler;
        this.roundUpCalculatedTaxUseCase = roundUpCalculatedTaxUseCase;
    }

    public void run(CartItem cartItem) throws UseCaseRunFailedException {
        try {
            TaxClass taxClass = taxClassDBRepository.findOneByKey(cartItem.getTaxClassKey());

            if (taxClass == null) {
                throw new InvalidInputException("taxClassKey: " + cartItem.getTaxClassKey() + "is invalid! See GET on the endpoint /class for the valid taxClassKeys!");
            }

            if (cartItem.getQuantity() < 1) {
                throw new InvalidInputException("A minimum quantity of 1 is required!");
            }

            Double initialTaxAmount = cartItem.getUnitNetPrice() * taxClass.getRate();
            Double roundedTaxAmount = roundUpCalculatedTaxUseCase.run(initialTaxAmount);

            Double grossPrice = cartItem.getUnitNetPrice() + roundedTaxAmount;

            cartItem.setUnitGrossPrice(SanitizeDoubleValueUseCase.run(grossPrice));
            cartItem.setUnitTaxAmount(roundedTaxAmount);

            Double totalGrossPrice = cartItem.getQuantity() * cartItem.getUnitGrossPrice();
            Double totalTaxAmount = cartItem.getQuantity() * cartItem.getUnitTaxAmount();

            cartItem.setTotalGrossPrice(SanitizeDoubleValueUseCase.run(totalGrossPrice));
            cartItem.setTotalTaxAmount(SanitizeDoubleValueUseCase.run(totalTaxAmount));
        } catch (InvalidInputException e) {
            throw new UseCaseRunFailedException(e);
        } catch (Throwable t) {
            this.exceptionHandler.handle(t);
            throw new UseCaseRunFailedException(t);
        }
    }
}
