package ipss.group1.practicas.controllers.response;


import java.util.List;

public class ResponseFormatLists {
    private String message;
    private List<?> data;


    public ResponseFormatLists() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }


    public static final class ResponseFormatListsBuilder {
        private String message;
        private List<?> data;

        private ResponseFormatListsBuilder() {
        }

        public static ResponseFormatListsBuilder aResponseFormatLists() {
            return new ResponseFormatListsBuilder();
        }

        public ResponseFormatListsBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ResponseFormatListsBuilder withData(List<?> data) {
            this.data = data;
            return this;
        }

        public ResponseFormatLists build() {
            ResponseFormatLists responseFormatLists = new ResponseFormatLists();
            responseFormatLists.setMessage(message);
            responseFormatLists.setData(data);
            return responseFormatLists;
        }
    }
}
