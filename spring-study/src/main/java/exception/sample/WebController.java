package exception.sample;

public class WebController {
    private final BusinessService businessService;

    public WebController(BusinessService businessService) {
        this.businessService = businessService;
    }

    public Boolean save(BusinessDto businessDto) {
        return businessService.save(businessDto);
    }
}
