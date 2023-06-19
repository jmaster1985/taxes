package com.codechallenge.taxes.usecase;

import com.codechallenge.taxes.dataaccess.repository.TaxClassRepository;
import com.codechallenge.taxes.dataaccess.repository.exception.DataAccessException;
import com.codechallenge.taxes.exceptionhandler.ExceptionHandler;
import com.codechallenge.taxes.model.cart.CartItem;
import com.codechallenge.taxes.model.tax.TaxClass;
import com.codechallenge.taxes.usecase.exception.InvalidInputException;
import com.codechallenge.taxes.usecase.exception.UseCaseRunFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculateCartItemUseCase {
    private final TaxClassRepository taxClassRepository;
    private final ExceptionHandler exceptionHandler;
    private final RoundUpCalculatedTaxUseCase roundUpCalculatedTaxUseCase;

    @Autowired
    public CalculateCartItemUseCase(TaxClassRepository taxClassRepository, ExceptionHandler exceptionHandler, RoundUpCalculatedTaxUseCase roundUpCalculatedTaxUseCase) {
        this.taxClassRepository = taxClassRepository;
        this.exceptionHandler = exceptionHandler;
        this.roundUpCalculatedTaxUseCase = roundUpCalculatedTaxUseCase;
    }

    public void run(CartItem cartItem) throws UseCaseRunFailedException {
        try {
            TaxClass taxClass = taxClassRepository.findByKey(cartItem.getTaxClassKey());

            if (taxClass == null) {
                throw new InvalidInputException("taxClassKey: " + cartItem.getTaxClassKey() + "is invalid! See GET on the endpoint /class for the valid taxClassKeys!");
            }

            if (cartItem.getQuantity() < 1) {
                throw new InvalidInputException("A minimum quantity of 1 is required!");
            }

            Double initialTaxAmount = cartItem.getUnitNetPrice() * taxClass.getRate();
            Double roundedTaxAmount = roundUpCalculatedTaxUseCase.run(initialTaxAmount);

            Double grossPrice = cartItem.getUnitNetPrice() + roundedTaxAmount;
            BigDecimal grossPriceAsBigDecimal = new BigDecimal(grossPrice).setScale(2, RoundingMode.HALF_UP);

            cartItem.setUnitGrossPrice(grossPriceAsBigDecimal.doubleValue());
            cartItem.setUnitTaxAmount(roundedTaxAmount);

            Double totalGrossPrice = cartItem.getQuantity() * cartItem.getUnitGrossPrice();
            Double totalTaxAmount = cartItem.getQuantity() * cartItem.getUnitTaxAmount();

            BigDecimal totalGrossPriceAsBigDecimal = new BigDecimal(totalGrossPrice).setScale(2, RoundingMode.HALF_UP);
            BigDecimal totalTaxAmountAsBigDecimal = new BigDecimal(totalTaxAmount).setScale(2, RoundingMode.HALF_UP);

            cartItem.setTotalGrossPrice(totalGrossPriceAsBigDecimal.doubleValue());
            cartItem.setTotalTaxAmount(totalTaxAmountAsBigDecimal.doubleValue());
        } catch (DataAccessException e) {
            this.exceptionHandler.handle(e);
            throw new UseCaseRunFailedException(e);
        } catch (InvalidInputException e) {
            throw new UseCaseRunFailedException(e);
        }
    }
}
