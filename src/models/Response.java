package models;

public class Response<T> {
    private String errorMessage;
    private ControllerResult result;
    private T data;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ControllerResult getResult() {
        return result;
    }

    public void setResult(ControllerResult result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "errorMessage='" + errorMessage + '\'' +
                ", result=" + result +
                ", data=" + data +
                '}';
    }
}
