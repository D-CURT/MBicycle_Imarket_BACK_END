package com.mbicycle.imarket.utils.generators;

import com.mbicycle.imarket.beans.entities.*;
import com.mbicycle.imarket.utils.enums.DeliveryType;
import com.mbicycle.imarket.utils.enums.PaymentType;
import com.mbicycle.imarket.utils.enums.RoleType;

import java.util.*;

public class BaseGenerator {

    private static Random RND = new Random();
    private List<Profile> profiles = new ArrayList<>();

    private List<Category> categories = new ArrayList<>();
    private List<Group> groups = new ArrayList<>();
    private List<Product> products;

    public BaseGenerator() {

    }

    public List<Category> getCategories() {

        if (categories.isEmpty()) {
            Category mobileCategory = new Category("Мобильные девайсы");
            Category householdCategory = new Category("Бытовая техника");
            categories.add(mobileCategory);
            categories.add(householdCategory);
        }
        return categories;
    }

    public void generateGroupsAndProducts(int count) {
        if (categories.isEmpty()) {
            getCategories();
        }

        List<Group> mobileGroups = new ArrayList<>();
        Group tabletGroup = new Group();
        tabletGroup.setName("Планшеты");
        Group phoneGroup = new Group();
        phoneGroup.setName("Телефоны");
        mobileGroups.add(tabletGroup);
        mobileGroups.add(phoneGroup);

        List<Group> householdGroups = new ArrayList<>(); //household appliances, irons, vacuum cleaners
        Group ironGroup = new Group();
        ironGroup.setName("Утюги");
        Group vacuumGroup = new Group();
        vacuumGroup.setName("Пылесосы");
        householdGroups.add(tabletGroup);
        householdGroups.add(phoneGroup);

        groups.add(tabletGroup);
        groups.add(phoneGroup);
        groups.add(ironGroup);
        groups.add(vacuumGroup);

        categories.get(0).setGroups(mobileGroups);
        tabletGroup.setCategory(categories.get(0));
        phoneGroup.setCategory(categories.get(0));

        categories.get(1).setGroups(householdGroups);
        ironGroup.setCategory(categories.get(1));
        vacuumGroup.setCategory(categories.get(1));


        List<Product> tablets = fillGroup(tabletGroup, count);
        List<Product> phones = fillGroup(phoneGroup, count);

        List<Product> irons = fillGroup(ironGroup, count);
        List<Product> vacuums = fillGroup(vacuumGroup, count);

        products = new ArrayList<>();
        products.addAll(tablets);
        products.addAll(phones);
        products.addAll(irons);
        products.addAll(vacuums);

    }

    public List<Group> getGroups() {
        return groups;
    }


    public List<Product> getProduсts() {
        return products;
    }


    public List<User> generateUsers(int count) {

        List<User> users = new ArrayList<>();
        Language ENG = Language.ENG;
        Language RUS = Language.RUS;
        Language DIGIT = Language.DIGIT;

        for (int i = 0; i < count; i++) {
            User user = new User(getWord(ENG), getWord(ENG));
            List<Role> roles = new ArrayList<>();
            Role role = new Role();

            Role customerRole = new Role(RoleType.CUSTOMER);
            Role managerRole = new Role(RoleType.MANAGER);
            Role adminRole = new Role(RoleType.ADMIN);

            if (RND.nextInt(100) > 10) {
                roles.add(customerRole);

            } else {
                roles.add(managerRole);
                if (RND.nextInt(100) < 10) {
                    roles.add(adminRole);
                }
            }


            user.setRoles(roles);
            Profile profile = new Profile(getWord(ENG), getWord(ENG), getWord(DIGIT), getWord(RUS), user, getWord(RUS));
            List<Profile> profiles = new ArrayList<>();
            profiles.add(profile);

            List<Coupon> coupons = new ArrayList<>();
            for (int j = 0; j < RND.nextInt(4); j++) {
                Coupon coupon = new Coupon(getSentense(RUS, 8), RND.nextInt(20), profiles);
                coupons.add(coupon);
            }
            profile.setCoupons(coupons);
            profile.setUser(user);

            System.out.println(user);
            profiles.add(profile);
            users.add(user);
        }


        return users;
    }

    public List<Order> generateOrders(int procent) {
        List<Order> orders = new ArrayList<>();
        for (Profile profile : profiles) {
            if (RND.nextInt(100) < procent) {
                Order order = new Order(profile, PaymentType.NOW, DeliveryType.DELIVERY, getDate(), getDate(), getDate(), getDate(), getDate(), getDate());
            }
        }
        return orders;
    }

    private List<Product> fillGroup(Group group, int procudtCount) {
        Language RUS = Language.RUS;
        Language ENG = Language.ENG;
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < procudtCount; i++) {
            Product product = new Product(getWord(RUS), getSentense(RUS, 8), getSentense(RUS, 36),
                    getDigit(1000), getWord(ENG), true, getDigit(100), group, null);
            System.out.println(product);
            result.add(product);
        }
        return result;
    }


    private String getWord(Language language) {
        int length = 2 + RND.nextInt(15);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(getChar(language));
        }
        return builder.toString();
    }


    private String getSentense(Language language, int wordCount) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < wordCount; i++) {
            String word = getWord(language);
            if (builder.length() + word.length() > 255) {
                break;
            }
            builder.append(word).append(' ');
        }
        return builder.toString();
    }

    private char getChar(Language language) {
        String chars = language.getChars();
        int maxLength = chars.length();
        return chars.charAt(RND.nextInt(maxLength));
    }

    private int getDigit(int max) {
        return RND.nextInt(max);
    }

    private Date getDate() {
        int year = 2018;
        int dayOfYear = RND.nextInt(365);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
        return calendar.getTime();

    }


}
