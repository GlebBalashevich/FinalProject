package by.balashevich.finalproject.validator;

import by.balashevich.finalproject.model.entity.Car;

public class CarValidator {
    private static final String PRICE_PATTERN = "\\d{2,3};\\d{2,3}";
    private static final String PRICE_DELIMITER = ";";

    public boolean validatePriceRangeData(String priceRangeData) {
        boolean isPriceRangeValid = false;

        if (priceRangeData != null && priceRangeData.matches(PRICE_PATTERN)) {
            String[] prices = priceRangeData.split(PRICE_DELIMITER);
            int priceFrom = Integer.parseInt(prices[0]);
            int priceTo = Integer.parseInt(prices[1]);
            if (priceFrom <= priceTo) {
                isPriceRangeValid = true;
            }
        }

        return isPriceRangeValid;
    }

    public boolean validateCarTypeData(String carTypeData){
        boolean isCarTypeValid = false;

        if (carTypeData != null){
            Car.Type[] types = Car.Type.values();
            for(int i = 0; i < types.length; i++){
                if (carTypeData.toUpperCase().equals(types[i].name())){
                    isCarTypeValid = true;
                }
            }
        }

        return isCarTypeValid;
    }

}
