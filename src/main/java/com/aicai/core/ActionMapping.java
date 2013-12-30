package com.aicai.core;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;

public class ActionMapping {

    private String              name;
    private String              namespace;
    private String              method;
    private String              extension;
    private Map<String, Object> params;
    private Result              result;

    /**
     * Constructs an ActionMapping
     */
    public ActionMapping() {
    }

    /**
     * Constructs an ActionMapping with a default result
     * 
     * @param result
     *            The default result
     */
    public ActionMapping(Result result) {
        this.result = result;
    }

    /**
     * Constructs an ActionMapping with its values
     * 
     * @param name
     *            The action name
     * @param namespace
     *            The action namespace
     * @param method
     *            The method
     * @param params
     *            The extra parameters
     */
    public ActionMapping(String name, String namespace, String method,
            Map<String, Object> params) {
        this.name = name;
        this.namespace = namespace;
        this.method = method;
        this.params = params;
    }

    /**
     * @return The action name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The action namespace
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * @return The extra parameters
     */
    public Map<String, Object> getParams() {
        return params;
    }

    /**
     * @return The method
     */
    public String getMethod() {
        if (null != method && "".equals(method)) {
            return null;
        } else {
            return method;
        }
    }

    /**
     * @return The default result
     */
    public Result getResult() {
        return result;
    }

    /**
     * @return The extension used during this request
     */
    public String getExtension() {
        return extension;
    }

    /**
     * @param result
     *            The result
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * @param name
     *            The action name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param namespace
     *            The action namespace
     */
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * @param method
     *            The method name to call on the action
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @param params
     *            The extra parameters for this mapping
     */
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    /**
     * @param extension
     *            The extension used in the request
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "ActionMapping{" + "name='" + name + '\'' + ", namespace='"
                + namespace + '\'' + ", method='" + method + '\''
                + ", extension='" + extension + '\'' + ", params=" + params
                + ", result="
                + (result != null ? result.getClass().getName() : "null") + '}';
    }

    public ActionMapping build(HttpServletRequest req, HttpServletResponse res) {
        String uri = req.getRequestURI();
        uri.indexOf("/");
        // TODO get the best performence of dealing url
        String[] array = uri.split("/");
        this.namespace = array[0];
        this.name = array[1];
        this.method = array[2];
        return this;
    }
}
