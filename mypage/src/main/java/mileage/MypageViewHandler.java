package mileage;

import mileage.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MypageViewHandler {


    @Autowired
    private MypageRepository mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenMemberJoined_then_CREATE_1 (@Payload MemberJoined memberJoined) {
        try {
            if (memberJoined.isMe()) {
                // view 객체 생성
                Mypage mypage = new Mypage();
                // view 객체에 이벤트의 Value 를 set 함
                mypage.setMemberId(memberJoined.getMemberId());
                mypage.setNickname(memberJoined.getNickname());
                mypage.setPhoneNo(memberJoined.getMdnNo());
                mypage.setMemberStatus(memberJoined.getMemberStatus());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenPointSaved_then_UPDATE_1(@Payload PointSaved pointSaved) {
        try {
            if (pointSaved.isMe()) {
                // view 객체 조회
                List<Mypage> mypageList = mypageRepository.findByMemberId(pointSaved.getMemberId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setRemainPoint(pointSaved.getRemainPoint());
                    // view 레파지 토리에 save
                    mypageRepository.save(mypage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPointUsed_then_UPDATE_2(@Payload PointUsed pointUsed) {
        try {
            if (pointUsed.isMe()) {
                // view 객체 조회
                List<Mypage> mypageList = mypageRepository.findByMemberId(pointUsed.getMemberId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setRemainPoint(pointUsed.getRemainPoint());
                    // view 레파지 토리에 save
                    mypageRepository.save(mypage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenMemberWithdrawn_then_DELETE_1(@Payload MemberWithdrawn memberWithdrawn) {
        try {
            if (memberWithdrawn.isMe()) {
                // view 레파지 토리에 삭제 쿼리
                mypageRepository.deleteByMemberId(memberWithdrawn.getMemberId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}