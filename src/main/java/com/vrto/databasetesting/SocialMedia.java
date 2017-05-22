package com.vrto.databasetesting;

import javax.persistence.AttributeConverter;

public enum SocialMedia {
    FACEBOOK, TWITTER, OTHER;

    public static class SocialMediaConverter implements AttributeConverter<SocialMedia, String> {
        @Override
        public String convertToDatabaseColumn(SocialMedia socialMedia) {
            return socialMedia.toString().toUpperCase();
        }

        @Override
        public SocialMedia convertToEntityAttribute(String dbValue) {
            return SocialMedia.valueOf(dbValue.toUpperCase());
        }
    }

}
