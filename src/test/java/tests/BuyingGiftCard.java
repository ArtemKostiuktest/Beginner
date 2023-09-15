package tests;

import base.AbstractBaseTest;
import org.testng.annotations.Test;
import pages.BillingAddressPage;
import pages.CreateDesignGiftCardPage;
import pages.GiftCardsPage;
import pages.HomePage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.UserData.*;

public class BuyingGiftCard extends AbstractBaseTest {

    @Test
    public void BuyGiftCard() {
        HomePage homePage = new HomePage(driver);
        GiftCardsPage giftCards = new GiftCardsPage(driver);
        CreateDesignGiftCardPage createDesignGiftCard = new CreateDesignGiftCardPage(driver);
        BillingAddressPage billingAddressPage = new BillingAddressPage(driver);

        homePage.clickCookie();
        homePage.clickGiftCards();
        giftCards.clickButtonBuyGiftCard();
        createDesignGiftCard.chooseDesignCard();
        createDesignGiftCard.choosePriceCard();
        createDesignGiftCard.getPrice();
        createDesignGiftCard.setSenderName(USER_LAST_NAME);
        createDesignGiftCard.setSenderEmail(USER_EMAIL);
        createDesignGiftCard.setRecipientName(RECIPIENT_NAME);
        createDesignGiftCard.setRecipientEmail(RECIPIENT_EMAIL);
        createDesignGiftCard.setMassage(MESSAGE_WITH_CARD);
        createDesignGiftCard.clickCheckBox();
        createDesignGiftCard.clickBuyNowButton();
        billingAddressPage.selectUkraineCountry();
        billingAddressPage.setFirstName(USER_FIRST_NAME);
        billingAddressPage.setLastName(USER_LAST_NAME);
        billingAddressPage.setAddress(ADDRESS);
        billingAddressPage.setPhoneNumber(USER_PHONE_NUMBER);
        billingAddressPage.setEmail(USER_EMAIL);
        billingAddressPage.setCityName(CITY);
        billingAddressPage.setPostCode(POST_CODE);
        billingAddressPage.clickConfirmAddressButton();
        billingAddressPage.getFinalPrice();

        sleep(2);
        assertEquals(BillingAddressPage.finalPrice, CreateDesignGiftCardPage.price);
        assertTrue(billingAddressPage.getActualData().contains(USER_FIRST_NAME));
        assertTrue(billingAddressPage.getActualData().contains(USER_LAST_NAME));
        assertTrue(billingAddressPage.getActualData().contains(ADDRESS));
        assertTrue(billingAddressPage.getActualData().contains(CITY));
        assertTrue(billingAddressPage.getActualData().contains(POST_CODE));
        assertTrue(billingAddressPage.getActualData().contains(USER_PHONE_NUMBER));
        assertTrue(billingAddressPage.getActualData().contains(USER_EMAIL));
    }
}