import org.junit.Test;
import utils.Constants;
import utils.ParamsUtilsService;

import static org.junit.Assert.assertEquals;

/**
 * Created by elena on 1/12/17.
 */

public class ParamsUtilsServiceTest {

    @Test
    public void splitParametersStringMethodShouldReturnArrayOfParametersWhenParametersStringIsCorrectlyFormed() {
        String[] expectedParameters = {Constants.PARAM_TO_TEST, Constants.PARAM_TO_TEST_2};
        String[] obtainedParameters = ParamsUtilsService.splitParametersString(Constants.PARAM_TO_TEST+Constants.AMPERSAND+Constants.PARAM_TO_TEST_2);
        assertEquals(Constants.WARNING, 2, obtainedParameters.length);
        assertInLoop(expectedParameters, obtainedParameters);
    }

    @Test
    public void splitParametersStringMethodShouldReturnEmptyArrayOfParametersWhenParametersStringIsEmpty() {
        String[] obtainedParameters = ParamsUtilsService.splitParametersString("");
        assertEquals(Constants.WARNING, 0, obtainedParameters.length);
    }

    @Test
    public void splitParametersStringMethodShouldReturnEmptyArrayOfParametersWhenParametersStringIsNull() {
        String[] obtainedParameters = ParamsUtilsService.splitParametersString("");
        assertEquals(Constants.WARNING, 0, obtainedParameters.length);
    }

    @Test
    public void splitParametersStringMethodShouldReturnArrayOfParametersWithOneElementWhenParametersStringHasOnlyOneParameter() {
        String[] expectedParameters = {Constants.PARAM_TO_TEST};
        String[] obtainedParameters = ParamsUtilsService.splitParametersString(Constants.PARAM_TO_TEST);
        assertEquals(Constants.WARNING, 1, obtainedParameters.length);
        assertInLoop(expectedParameters, obtainedParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getParamValueMethodShouldThrowExceptionWhenParameterEmpty() throws Exception {
        ParamsUtilsService.getParamValue(Constants.EMPTY_STRING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getParamValueMethodShouldThrowExceptionWhenParameterNull() throws Exception {
        ParamsUtilsService.getParamValue(null);
    }

    @Test
    public void getParamValueMethodShouldReturnValueWhenParameterContainsCommaInValue() throws Exception {
        assertEquals("Wrong value extracted.", 100.2f, ParamsUtilsService.getParamValue("100,2"), Constants.DELTA_PRECISION_LOSS);
    }

    @Test
    public void getParamValueMethodShouldReturnValueWhenParameterContainsPointInValue() throws Exception {
        assertEquals("Wrong value extracted.", 100.2f, ParamsUtilsService.getParamValue("100.2"), Constants.DELTA_PRECISION_LOSS);
    }

    @Test
    public void getParamValueMethodShouldReturnFloatValueWhenParameterContainsWronglyAddedCharactersInValue() throws Exception {
        assertEquals("Wrong value extracted.", 100.2f, ParamsUtilsService.getParamValue("100.2afafa"), Constants.DELTA_PRECISION_LOSS);
    }

    private void assertInLoop(String[] expectedParameters, String[] obtainedParameters) {
        for (int i = 0; i < expectedParameters.length; i++) {
            assertEquals(expectedParameters[i], obtainedParameters[i]);
        }
    }
}
