package utilities;

import models.ControllerResult;
import models.Response;

public class ResponseUtility {
    public static <K> Response<K> fromSuccess(K data) {
        Response<K> response = new Response<>();
        response.setResult(ControllerResult.SUCCESS);
        response.setData(data);
        return response;
    }

    public static <K> Response<K> fromFailure(String errorMessage) {
        Response<K> response = new Response<>();
        response.setResult(ControllerResult.FAILURE);
        response.setErrorMessage(errorMessage);
        return response;
    }
}
