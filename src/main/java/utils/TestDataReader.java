package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * TestDataReader - Reads test data from properties file
 */
public class TestDataReader {
    private static Properties testData;
    private static final String TEST_DATA_FILE = "src/test/resources/testdata/testdata.properties";

    static {
        loadTestData();
    }

    /**
     * Load test data from properties file
     */
    private static void loadTestData() {
        testData = new Properties();
        try {
            FileInputStream fis = new FileInputStream(TEST_DATA_FILE);
            testData.load(fis);
            fis.close();
            LoggerUtil.info("Test data loaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load test data file: " + TEST_DATA_FILE);
        }
    }

    /**
     * Get test data value by key
     */
    public static String getData(String key) {
        String value = testData.getProperty(key);
        if (value == null) {
            LoggerUtil.warn("Test data not found for key: " + key);
            return "";
        }
        return value;
    }

    /**
     * Get valid username
     */
    public static String getValidUsername() {
        return getData("valid.username");
    }

    /**
     * Get valid password
     */
    public static String getValidPassword() {
        return getData("valid.password");
    }

    /**
     * Get invalid username
     */
    public static String getInvalidUsername() {
        return getData("invalid.username");
    }

    /**
     * Get invalid password
     */
    public static String getInvalidPassword() {
        return getData("invalid.password");
    }

    /**
     * Get registration data
     */
    public static String getRegistrationFirstName() {
        return getData("registration.firstName");
    }

    public static String getRegistrationLastName() {
        return getData("registration.lastName");
    }

    public static String getRegistrationAddress() {
        return getData("registration.address");
    }

    public static String getRegistrationCity() {
        return getData("registration.city");
    }

    public static String getRegistrationState() {
        return getData("registration.state");
    }

    public static String getRegistrationZipCode() {
        return getData("registration.zipCode");
    }

    public static String getRegistrationPhone() {
        return getData("registration.phone");
    }

    public static String getRegistrationSSN() {
        return getData("registration.ssn");
    }

    /**
     * Get transfer funds data
     */
    public static String getTransferAmount() {
        return getData("transfer.amount");
    }

    public static String getTransferFromAccountIndex() {
        return getData("transfer.fromAccountIndex");
    }

    public static String getTransferToAccountIndex() {
        return getData("transfer.toAccountIndex");
    }

    /**
     * Get bill payment data
     */
    public static String getBillPayPayeeName() {
        return getData("billpay.payeeName");
    }

    public static String getBillPayAddress() {
        return getData("billpay.address");
    }

    public static String getBillPayCity() {
        return getData("billpay.city");
    }

    public static String getBillPayState() {
        return getData("billpay.state");
    }

    public static String getBillPayZipCode() {
        return getData("billpay.zipCode");
    }

    public static String getBillPayPhone() {
        return getData("billpay.phone");
    }

    public static String getBillPayAccount() {
        return getData("billpay.account");
    }

    public static String getBillPayAmount() {
        return getData("billpay.amount");
    }

    public static String getBillPayFromAccountIndex() {
        return getData("billpay.fromAccountIndex");
    }
}
