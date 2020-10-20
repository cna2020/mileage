package mileage;

import javax.persistence.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Point_table")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long memberId;
    private Long remainPoint;
    private String memberStatus;
    private Long requirePoint;

    @PostPersist
    public void onPostPersist() {
        System.out.println("\n$$$onPostPersist");
        PointSaved pointSaved = new PointSaved();
        BeanUtils.copyProperties(this, pointSaved);

        //pointSaved.setMemberId(pointSaved.getMemberId());


        pointSaved.publishAfterCommit();
    }

    /*
    @Autowired
    private PointRepository pointRepository;

     */

    @PreUpdate
    public void onPreUpdate() {
        Long newPoint = 0L;

        PointUsed pointUsed = new PointUsed();

        //List<Point> pointList = pointRepository.findByMemberId()

        if(this.memberStatus.equals("NORMAL")) {

            newPoint = this.getRemainPoint() + this.getRequirePoint();
            this.setRemainPoint(newPoint);
            BeanUtils.copyProperties(this, pointUsed);
            pointUsed.publishAfterCommit();
        }

        System.out.println("\n$$$onPreUpdate : " + newPoint);
    }

    /*
    @PostUpdate
    public void onPostUpdate() {
        //Long newPoint;
        System.out.println("\n$$$onPostUpdate");

        System.out.println("1 : " + requirePoint);

        PointUsed pointUsed = new PointUsed();

        if(this.memberStatus.equals("NORMAL")) {
            BeanUtils.copyProperties(this, pointUsed);
            pointUsed.publishAfterCommit();
        }



        /*
        if (requirePoint <= 0) {


            PointUsed pointUsed = new PointUsed();
            BeanUtils.copyProperties(this, pointUsed);

            System.out.println("requirePoint <= 0 pointUsed.getRemainPoint()) : " + pointUsed.getRemainPoint());


            pointUsed.setMemberId(pointUsed.getMemberId());
            pointUsed.setMemberStatus("NORMAL");
            System.out.println("requirePoint <= 0 pointUsed.getRemainPoint()) : " + (pointUsed.getRemainPoint() + requirePoint));

            pointUsed.setRemainPoint(pointUsed.getRemainPoint() +requirePoint);


            pointUsed.publishAfterCommit();




        } else {
            PointSaved pointSaved = new PointSaved();
            BeanUtils.copyProperties(this, pointSaved);

            pointSaved.setMemberId(pointSaved.getMemberId());
            pointSaved.setMemberStatus("NORMAL");
            pointSaved.setRemainPoint(pointSaved.getRemainPoint() + requirePoint);


            pointSaved.publishAfterCommit();
        }

    }

     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getRemainPoint() {
        return remainPoint;
    }

    public void setRemainPoint(Long remainPoint) {
        this.remainPoint = remainPoint;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public Long getRequirePoint() {
        return requirePoint;
    }

    public void setRequirePoint(Long usePoint) {
        this.requirePoint = usePoint;
    }
}
