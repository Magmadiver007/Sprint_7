package org.example;

import org.apache.commons.lang3.RandomStringUtils;
public class CourierGenerator {
    public static Courier getRandom(){
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, password, firstName);
    }
    public static Courier getRandomNoLogin(){
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(null, password, firstName);
    }
    public static Courier getRandomNoPassword(){
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, null, firstName);
    }
    public static Courier getRandomNoFirstName(){
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, password, null);
    }
    public static Courier getSpecific(){
        final String login = "TestovskyTT";
        final String password = "QWERTY123";
        final String firstName = "Тестовский";
        return new Courier(login, password, firstName);
    }
}
