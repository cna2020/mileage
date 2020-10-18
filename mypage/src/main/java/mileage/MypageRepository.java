package mileage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MypageRepository extends CrudRepository<Mypage, Long> {

    List<Mypage> findByMemberId(Long memberId);
    List<Mypage> findByMemberId(Long memberId);

        void deleteByMemberId(Long memberId);
}