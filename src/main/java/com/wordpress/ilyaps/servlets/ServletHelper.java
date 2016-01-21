package com.wordpress.ilyaps.servlets;

/**
 * Created by ilyap on 21.01.2016.
 */
public class ServletHelper {
    public static final String BAD_FORM = "BAD_FORM";
    public static final String SUCCESSFUL = "SUCCESSFUL";
    public static final String ERROR = "ERROR";

    public static String getHtmlRedirect(String site) {
        return "<meta http-equiv=\"refresh\" content=\"1; URL=" + site +"\" />";
    }

}
