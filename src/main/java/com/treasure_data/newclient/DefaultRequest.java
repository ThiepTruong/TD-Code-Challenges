package com.treasure_data.newclient;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.treasure_data.newclient.auth.Signer;
import com.treasure_data.newclient.auth.TreasureDataCredentials;
import com.treasure_data.newclient.model.TreasureDataServiceRequest;

public class DefaultRequest<T extends TreasureDataServiceRequest> implements Request<T> {

    private final T originalRequest;

    private Map<String, String> parameters = new HashMap<String, String>();
    private Map<String, String> headers = new HashMap<String, String>();
    private String endpoint;
    private String resourcePath;

    private TreasureDataCredentials credentials;
    private Signer signer;

    private MethodName methodName;
    private InputStream content;

    public DefaultRequest(T originalRequest) {
        this.originalRequest = originalRequest;
    }

    public TreasureDataServiceRequest getOriginalRequest() {
        return originalRequest;
    }

    public TreasureDataCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(TreasureDataCredentials credentials) {
        this.credentials = credentials;
    }

    public void setSigner(Signer signer) {
        this.signer = signer;
    }

    public Signer getSinger() {
        return signer;
    }

    @Override
    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public void setHeaders(Map<String, String> headers) {
        this.headers.clear();
        this.headers.putAll(headers);
    }

    @Override
    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public String getResourcePath() {
        return resourcePath;
    }

    @Override
    public void addParameter(String name, String value) {
        parameters.put(name, value);
    }

    @Override
    public Map<String, String> getParameters() {
        return parameters;
    }

    @Override
    public void setParameters(Map<String, String> parameters) {
        this.parameters.clear();
        this.parameters.putAll(parameters);
    }

    @Override
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String getEndpoint() {
        return endpoint;
    }

    @Override
    public void setMethodName(MethodName methodName) {
        this.methodName = methodName;
    }

    @Override
    public MethodName getMethodName() {
        return methodName;
    }

    @Override
    public InputStream getContent() {
        return content;
    }

    @Override
    public void setContent(InputStream content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sbuf = new StringBuilder();

        sbuf.append(getMethodName().toString() + " ");
        sbuf.append(getEndpoint().toString() + " ");

        sbuf.append("/" + (getResourcePath() != null ? getResourcePath() : "")
                + " ");

        if (!getParameters().isEmpty()) {
            sbuf.append("Parameters: (");
            for (String key : getParameters().keySet()) {
                String value = getParameters().get(key);
                sbuf.append(key + ": " + value + ", ");
            }
            sbuf.append(") ");
        }

        if (!getHeaders().isEmpty()) {
            sbuf.append("Headers: (");
            for (String key : getHeaders().keySet()) {
                String value = getHeaders().get(key);
                sbuf.append(key + ": " + value + ", ");
            }
            sbuf.append(") ");
        }

        return sbuf.toString();
    }
}
