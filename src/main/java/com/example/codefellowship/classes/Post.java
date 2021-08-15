package com.example.codefellowship.classes;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(columnDefinition = "text")
    private String body ;

    private String createdAt ;

    @ManyToOne
    private ApplicationUser applicationUser ;

    public Post(){

    }

    public Post(String body , String createdAt){
        this.body = body ;
        this.createdAt = createdAt ;
    }

    public Long getId() {
        return id;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
