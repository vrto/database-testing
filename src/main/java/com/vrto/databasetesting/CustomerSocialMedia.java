package com.vrto.databasetesting;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "customers_social_media")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CustomerSocialMedia {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column(name = "customer_id")
    Long customerId;

    @Column(name = "social_media")
    @Convert(converter = SocialMedia.SocialMediaConverter.class)
    SocialMedia socialMedia;

    public CustomerSocialMedia(Long customerId, SocialMedia socialMedia) {
        this.customerId = customerId;
        this.socialMedia = socialMedia;
    }

}
