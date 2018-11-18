package dtos.trulayer;

public class Provider {

    public Provider() {}

    private String display_name;
    private String logo_uri;
    private String provider_id;

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getLogo_uri() {
        return logo_uri;
    }

    public void setLogo_uri(String logo_uri) {
        this.logo_uri = logo_uri;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }
}
