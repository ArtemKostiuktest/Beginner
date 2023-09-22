package tests;

import base.AbstractBaseTest;
import org.testng.annotations.Test;
import pages.BillingAddressPage;
import pages.CreateDesignGiftCardPage;
import pages.GiftCardsPage;
import pages.HeaderFragment;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.UserData.*;

public class BuyingGiftCard extends AbstractBaseTest {

    private final String COUNTRY = "UA";
    private final String PRICE = "100";

    @Test
    public void BuyGiftCard() {
        HeaderFragment headerFragment = new HeaderFragment(driver);
        GiftCardsPage giftCards = new GiftCardsPage(driver);
        CreateDesignGiftCardPage createDesignGiftCard = new CreateDesignGiftCardPage(driver);
        BillingAddressPage billingAddressPage = new BillingAddressPage(driver);

        headerFragment.clickGiftCards();

        giftCards.clickButtonBuyGiftCard();

        createDesignGiftCard.chooseDesignCard();
        createDesignGiftCard.choosePriceCard(PRICE);
        createDesignGiftCard.getPrice(PRICE);
        createDesignGiftCard.setSenderName(USER_LAST_NAME);
        createDesignGiftCard.setSenderEmail(USER_EMAIL);
        createDesignGiftCard.setRecipientName(RECIPIENT_NAME);
        createDesignGiftCard.setRecipientEmail(RECIPIENT_EMAIL);
        createDesignGiftCard.setMassage(MESSAGE_WITH_CARD);
        createDesignGiftCard.confirmTermsConditionsPrivacyPolicy();
        createDesignGiftCard.clickBuyNowButton();

        billingAddressPage.selectUkraineCountry(COUNTRY);
        billingAddressPage.setFirstName(USER_FIRST_NAME);
        billingAddressPage.setLastName(USER_LAST_NAME);
        billingAddressPage.setAddress(ADDRESS);
        billingAddressPage.setPhoneNumber(USER_PHONE_NUMBER);
        billingAddressPage.setEmail(USER_EMAIL);
        billingAddressPage.setCityName(CITY);
        billingAddressPage.setPostCode(POST_CODE);
        billingAddressPage.clickConfirmAddressButton();
        billingAddressPage.getFinalPrice();

        assertEquals(BillingAddressPage.finalPrice, CreateDesignGiftCardPage.priceValue);
        for (String data : dataToCheckInGiftCard) {
            assertTrue(billingAddressPage.getActualData().contains(data));
        }
    }
}