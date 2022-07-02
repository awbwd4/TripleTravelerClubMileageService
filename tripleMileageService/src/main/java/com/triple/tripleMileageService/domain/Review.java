package com.triple.tripleMileageService.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.lang.reflect.Member;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
public class Review {

    @Id
    @Column(name="review_id")
    private String uuid;

    @NotEmpty
    private String content;

    @OneToMany(mappedBy = "review")
    private List<Photo> photos; // photo 객체 필요

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id")
    private Place place;


    /**생성자**/
    private Review() {
    }

    /**
     * 생성 메서드
     **/
    public static Review createReview(String content, User user, Place place) {
        Review review = new Review();
        review.setUuid(UUID.randomUUID().toString());
        review.setContent(content);
        review.setUser(user);
        review.setPlace(place);

        // point 증가
//        user.getPoint().increasePoint(1);
                ///////글자수 체크, 사진 여부 체크 로직 필요////

        // point history 추가

        // place - review_count 증가
        place.increaseReviewCount();

        return review;
    }
//
//    /**
//     * 연관관계 메서드 : 사진 첨부시 사용.
//     * **/
//    public addPhoto(Photo photo) {
//
//    }


}
