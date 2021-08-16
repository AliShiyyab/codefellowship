package com.example.codefellowship.classes;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String body ;

    @CreationTimestamp
    private Date createAt;

    @ManyToOne
    ApplicationUser applicationUser;

    public Post(String body, ApplicationUser app){
        this.applicationUser = app;
        this.body = body;
    }

    public Post(){}

    public String getBody() {
        return body;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public Long getId() {
        return id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
}
