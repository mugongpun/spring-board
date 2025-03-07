package com.mysite.sbb.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk

    @Column(unique = true)
    private String username; //사용자명 ID

    private String password; //비번

    @Column(unique = true)
    private String email; //이메일
}
