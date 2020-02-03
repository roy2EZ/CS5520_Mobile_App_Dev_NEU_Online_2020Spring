package edu.neu.madcourse.numad20s_rongyichen;

/**
 * to represent a user saved website element with its name and URL
 */
public class Website {
    private String name;
    private String url;

    public Website(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
