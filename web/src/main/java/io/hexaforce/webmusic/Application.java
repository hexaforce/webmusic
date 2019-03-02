package io.hexaforce.webmusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.Getter;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Throwable {
		SpringApplication.run(Application.class, args);
	}
	
	@Getter
	public enum PageDefine {
		
	    WELCOME("/", "index"), 
	    
	    INDEX("/index", "index"), 
	    
	    LOGIN("/login", "login"), 
	    
	    APP("/app", "app");

	    private final String viewController;
	    private final String viewName;

	    PageDefine(String viewController, String viewName) {
	        this.viewController = viewController;
	        this.viewName = viewName;
	    }

	}

	@Configuration
	public class WebMvcConfig implements WebMvcConfigurer {
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			for (PageDefine p : PageDefine.values())
				registry.addViewController(p.getViewController()).setViewName(p.getViewName());
		}
	}

}
