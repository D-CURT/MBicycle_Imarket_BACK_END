package com.mbicycle.imarket.utils.generators;

import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductGenerator {

    private static Random RND = new Random();
    private static String RUS = "абвгдеёжзийклмнопрстуфхцчъыьэюя";
    private static String ENG = "abcdefghijklmnopqrstuvwxyz";
    private static String DIG = "0123456789";
    private static String SMALL_DIG = "0123456789";


    public ProductGenerator() {

    }

    public List<Product> generate() {

        List<Group> mobileGroups = new ArrayList<>();
        Group tabletGroup = new Group();
        tabletGroup.setName("Планшеты");
        Group phoneGroup = new Group();
        phoneGroup.setName("Телефоны");
        mobileGroups.add(tabletGroup);
        mobileGroups.add(phoneGroup);

        Category mobileCategory = new Category("Мобильные девайсы");
        tabletGroup.setCategory(mobileCategory);
        phoneGroup.setCategory(mobileCategory);

        List<Product> tablets = fillGroup(tabletGroup, 34);
        List<Product> phones = fillGroup(phoneGroup, 41);

        List<Group> householdGroups = new ArrayList<>(); //household appliances, irons, vacuum cleaners
        Group ironGroup = new Group();
        ironGroup.setName("Утюги");
        Group vacuumGroup = new Group();
        vacuumGroup.setName("Пылесосы");
        householdGroups.add(tabletGroup);
        householdGroups.add(phoneGroup);

        Category householdCategory = new Category("Бытовая техника");
        ironGroup.setCategory(householdCategory);
        vacuumGroup.setCategory(householdCategory);

        List<Product> irons = fillGroup(ironGroup, 25);
        List<Product> vacuums = fillGroup(vacuumGroup, 52);

        List<Product> allProduct = new ArrayList<>();
        allProduct.addAll(tablets);
        allProduct.addAll(phones);
        allProduct.addAll(irons);
        allProduct.addAll(vacuums);

        return allProduct;

    }

    private List<Product>  fillGroup(Group group, int procudtCount) {
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < procudtCount; i++) {
//            Product product = new Product(getWord(), getSentense(8), getSentense(36),
//                    getStrDigit(2), getWord(), getWord(), getStrDigit(2),
//                    group);

//            System.out.println(product);
//            result.add(product);
        }

        return result;
    }


    private String getWordByLength(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(getChar());
        }
        return builder.toString();
    }


    private String getWord() {
        int length = 2 + RND.nextInt(15);
        return getWordByLength(length);
    }

    private String getSentense(int wordCount) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < wordCount; i++) {
            builder.append(getWord()).append(' ');
        }
        return builder.toString();

    }


    private char getChar() {
        return RUS.charAt(RND.nextInt(RUS.length()));
    }

    private char getOneDigit() {
        return DIG.charAt(RND.nextInt(DIG.length()));
    }

    private String getStrDigit(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(getOneDigit());
        }
        return builder.toString();
    }
}
