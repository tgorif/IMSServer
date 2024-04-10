package com.tgorif.IMSServer.Sku.API;

public class SkuAPIResponse {
    SKUResponseCode responseCode;

    public SkuAPIResponse(SKUResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public SKUResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(SKUResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
