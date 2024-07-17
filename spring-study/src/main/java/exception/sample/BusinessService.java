package exception.sample;

public class BusinessService {
    private final JpaRepository jpaRepository;

    public BusinessService(JpaRepository businessService) {
        this.jpaRepository = businessService;
    }

    public boolean save(BusinessDto businessDto) {
        return jpaRepository.save(businessDto);
    }
}
