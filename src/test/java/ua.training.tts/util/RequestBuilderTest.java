package ua.training.tts.util;

import org.junit.jupiter.api.Test;
import org.testng.Assert;
import ua.training.tts.constant.TestConstants;
import ua.training.tts.model.util.RequestBuilder;

class RequestBuilderTest extends Assert{

    RequestBuilder builder = new RequestBuilder();
    String[] values = {TestConstants.VALUE1, TestConstants.VALUE2};

    @Test
    void insertIntoTable() {
        String actuals = builder.insertIntoTable(TestConstants.TABLE).build().toLowerCase();
        String expected = TestConstants.INSERT_INTO_TABLE_EXPECTED.toLowerCase();
        Assert.assertEquals(expected, actuals);
    }

    @Test
    void insertValues() {
        String actuals = builder.insertValues(values).build().toLowerCase();
        String expected = TestConstants.INSERT_VALUES_EXPECTED.toLowerCase();
        Assert.assertEquals(expected, actuals);
    }

    @Test
    void selectAllFromTable() {
        String actuals = builder.selectAllFromTable(TestConstants.TABLE).build().toLowerCase();
        String expected = TestConstants.SELECT_ALL_FROM_TABLE_EXPECTED.toLowerCase();
        Assert.assertEquals(expected, actuals);
    }

    @Test
    void where() {
        String actuals = builder.where(TestConstants.COLUMN).build().toLowerCase();
        String expected = TestConstants.WHERE_EXPECTED.toLowerCase();
        Assert.assertEquals(expected, actuals);
    }

    @Test
    void and() {
        String actuals = builder.and(TestConstants.COLUMN).build().toLowerCase();
        String expected = TestConstants.AND_EXPECTED.toLowerCase();
        Assert.assertEquals(expected, actuals);
    }

    @Test
    void update() {
        String actuals = builder.update(TestConstants.TABLE, values).build().toLowerCase();
        String expected = TestConstants.UPDATE_EXPECTED.toLowerCase();
        Assert.assertEquals(expected, actuals);
    }

    @Test
    void delete() {
        String actuals = builder.delete(TestConstants.COLUMN).build().toLowerCase();
        String expected = TestConstants.DELETE.toLowerCase();
        Assert.assertEquals(expected, actuals);
    }
}