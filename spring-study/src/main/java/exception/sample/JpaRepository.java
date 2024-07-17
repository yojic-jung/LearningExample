package exception.sample;

public class JpaRepository {

    public boolean save(BusinessDto businessDto) {
        try {
            return saveDto(businessDto);
        } catch (JpaCheckException e) {
            throw new RuntimeException("영속화 오류");
        }
    }

    private boolean saveDto(BusinessDto businessDto) throws JpaCheckException {
        if (businessDto.getId() == 0) {
            throw new JpaCheckException();
        } else {
            // 처리 로직
            return true;
        }
    }
}
