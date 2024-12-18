package ipss.group1.practicas.controllers.response;

public class ResponseFormat {
    private String message;
    private Object data;

    public ResponseFormat() {
    }

    public ResponseFormat(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static final class ResponseFormatBuilder {
        private String message;
        private Object data;

        private ResponseFormatBuilder() {
        }

        public static ResponseFormatBuilder aResponseFormat() {
            return new ResponseFormatBuilder();
        }

        public ResponseFormatBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ResponseFormatBuilder withData(Object data) {
            this.data = data;
            return this;
        }

        public ResponseFormat build() {
            ResponseFormat responseFormat = new ResponseFormat();
            responseFormat.setMessage(message);
            responseFormat.setData(data);
            return responseFormat;
        }
    }
}
