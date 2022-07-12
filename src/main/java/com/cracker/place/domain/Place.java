package com.cracker.place.domain;


import com.cracker.comment.domain.Comment;
import com.cracker.user.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import static javax.persistence.FetchType.LAZY;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String addr;

    @Column
    private String addrRoad;

    @Column(nullable = false)
    private String coordX;

    @Column(nullable = false)
    private String coordY;

    @Column
    private String phoneNum;

    @Column
    private String cate;


    @OneToMany(mappedBy = "place")
    private List<Comment> comments = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private Users users;

    @Builder
    public Place(String name, String addr, String addrRoad,
                 String coordX, String coordY,String phoneNum, String cate) {
        this.name = name;
        this.addr = addr;
        this.addrRoad = addrRoad;
        this.coordX = coordX;
        this.coordY = coordY;
        this.phoneNum = phoneNum;
        this.cate = cate;
    }

    public void registUser(Users user){
        this.users = user;
        user.getPlaces().add(this);
    }

}
