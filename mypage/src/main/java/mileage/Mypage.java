package mileage;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Mypage_table")
public class Mypage {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long memberId;
        private String nickname;
        private String phoneNo;
        private Long remainPoint;
        private String memberStatus;


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
        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
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

}
