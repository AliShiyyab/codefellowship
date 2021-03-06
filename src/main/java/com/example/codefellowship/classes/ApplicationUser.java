package com.example.codefellowship.classes;

import com.example.codefellowship.repositories.ApplicationUserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "application_user")
public class ApplicationUser implements UserDetails {

    @OneToMany(mappedBy = "applicationUser")
    List<Post> post;

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    private String firstName;
    private String lastName;
    private String dateOfBirth;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;

    @Column(columnDefinition = "text")
    private String bio ;

    public ApplicationUser(){

    }

    public ApplicationUser(String username , String password){
        this.username = username ;
        this.password = password ;
    }

    public ApplicationUser( String username , String password, String firstName , String lastName , String dateOfBirth , String bio ){
        this.firstName = firstName ;
        this.lastName = lastName ;
        this.dateOfBirth = dateOfBirth ;
        this.bio = bio ;
        this.username = username ;
        this.password = password ;
    }

    public Long getId(){return this.id;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @ManyToMany
    @JoinTable(name="applicationUserAccount",
    joinColumns = @JoinColumn(name = "followerUser"), inverseJoinColumns = @JoinColumn(name = "followwingUser"))
    Set<ApplicationUser> following = new HashSet<>();

    public void setFollowing(Set<ApplicationUser> following) {
        this.following = following;
    }

    public Set<ApplicationUser> getFollowing() {
        return following;
    }

    @ManyToMany(mappedBy = "following")
    List<ApplicationUser> followers;

    public List<ApplicationUser> getFollowers() {
        return followers;
    }

    public void setFollowers(List<ApplicationUser> followers) {
        this.followers = followers;
    }

    public Set<ApplicationUser> addFollowing(ApplicationUser users){
        this.following.add(users);
        return this.following;
    }
}