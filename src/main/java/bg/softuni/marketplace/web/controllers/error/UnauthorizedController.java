package bg.softuni.marketplace.web.controllers.error;

import bg.softuni.marketplace.config.WebConfig;
import bg.softuni.marketplace.web.annotations.Layout;
import bg.softuni.marketplace.web.annotations.Title;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Layout
@Controller
public class UnauthorizedController {

    private static final String VIEW_ERROR_UNAUTHORIZED = "error/unauthorized";

    @GetMapping(WebConfig.URL_ERROR_UNAUTHORIZED)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @Title(title = "error.unauthorized.title", append = false)
    public String viewUnauthorized() {
        return VIEW_ERROR_UNAUTHORIZED;
    }
}
