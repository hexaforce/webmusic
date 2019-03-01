package hello;

import lombok.Getter;

@Getter
public enum PageDefine {
    WELCOME("/", "index"), 
    INDEX("/index", "index"), 
    LOGIN("/login", "login"), 
    APP("/app", "app"),
    HEALTHCHECK("/healthcheck", "healthcheck"),
    ;

    private final String viewController;
    private final String viewName;

    PageDefine(String viewController, String viewName) {
        this.viewController = viewController;
        this.viewName = viewName;
    }

}
