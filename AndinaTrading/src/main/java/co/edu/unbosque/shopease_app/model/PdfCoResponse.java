package co.edu.unbosque.shopease_app.model;

public class PdfCoResponse {
    private boolean error;
    private String url;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
