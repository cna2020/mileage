package mileage;

import mileage.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PolicyHandler {
    @Autowired
    PointRepository pointRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString) {

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMemberStatusChanged_UpdateMemberStatus(@Payload MemberStatusChanged memberStatusChanged) {

        if (memberStatusChanged.isMe()) {
            System.out.println("##### memberStatusChanged : " + memberStatusChanged.toJson());

           // Optional<Point> pointOptional = pointRepository.findByMemberId(memberStatusChanged.getMemberId());
           // Point point = pointOptional.get();


            Optional<Point> pointOptional = pointRepository.findByMemberId(memberStatusChanged.getMemberId());
            Point point = pointOptional.get();
            point.setMemberStatus(memberStatusChanged.getMemberStatus());
            pointRepository.save(point);

            /*
            Point point = new Point();
            point.setMemberId(memberStatusChanged.getMemberId());
            point.setMemberStatus(memberStatusChanged.getMemberStatus());

            if ("NORMAL".equals(memberStatusChanged.getMemberStatus())) {
                point.setRemainPoint(1000L);
            } else {
                point.setRemainPoint(0L);
            }

            pointRepository.save(point);
            */
        }

    }
}
