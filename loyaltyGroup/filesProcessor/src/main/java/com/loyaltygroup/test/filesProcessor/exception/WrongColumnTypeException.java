package com.loyaltygroup.test.filesProcessor.exception;

public class WrongColumnTypeException extends Exception {

        public WrongColumnTypeException(String nameAttr) {
            super("В атрибуте " + nameAttr + " указан не верный тип данных");
        }

}
