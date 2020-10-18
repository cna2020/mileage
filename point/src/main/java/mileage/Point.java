package mileage;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Point_table")
public class Point {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long memberId;
    private Long remainPoint;

    @PostPersist
    public void onPostPersist(){
        PointSaved pointSaved = new PointSaved();
        BeanUtils.copyProperties(this, pointSaved);
        pointSaved.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        PointForfeited pointForfeited = new PointForfeited();
        BeanUtils.copyProperties(this, pointForfeited);
        pointForfeited.publishAfterCommit();


        PointUsed pointUsed = new PointUsed();
        BeanUtils.copyProperties(this, pointUsed);
        pointUsed.publishAfterCommit();


    }


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




}
