package utils;

abstract public class UserData {

    public static final String USER_FIRST_NAME = "Kostyuk";
    public static final String USER_LAST_NAME = "Artem";
    public static final String USER_EMAIL = "artem@gmail.com";
    public static final String RECIPIENT_NAME = "Zahar";
    public static final String RECIPIENT_EMAIL = "zahar@gmail.com";
    public static final String MESSAGE_WITH_CARD = "It is for you";
    public static final String ADDRESS = "вулиця Винниченка 69";
    public static final String USER_PHONE_NUMBER = "0526895142";
    public static final String CITY = "ЛУЦЬК";
    public static final String POST_CODE = "43000";
    public static final String UN_POST_CODE = "EC1A 1HQ";
    public static final String UN_CITY = "London";
    public static final String CARD_NUM = "3566000020000410";
    public static final String CARD_MONTH_BUT = "12";
    public static final String CARD_YEAR_BUT = "25";
    public static final String SECURITY_CODE = "123";
    public static final String NAME_OF_HOLDER = " Artem Ivanov";


    public static String[] dataToCheckInGiftCard = {USER_FIRST_NAME, USER_LAST_NAME, ADDRESS, CITY, POST_CODE, USER_PHONE_NUMBER, USER_EMAIL};
    public static String[] dataPay = {USER_FIRST_NAME, USER_LAST_NAME, ADDRESS, UN_CITY, UN_POST_CODE, USER_PHONE_NUMBER, USER_EMAIL};
}