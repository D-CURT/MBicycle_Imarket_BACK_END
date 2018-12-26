package com.mbicycle.imarket.utils.generators;

public enum Language {
    RUS {
        @Override
        public String getChars() {
            return "абвгдеёжзийклмнопрстуфхцчъыьэюя";
        }
    },
    ENG {
        @Override
        public String getChars() {
            return  "abcdefghijklmnopqrstuvwxyz";
        }
    },
    DIGIT {
        @Override
        public String getChars() {
            return  "0123456789";
        }
    };

    public abstract String getChars();
}