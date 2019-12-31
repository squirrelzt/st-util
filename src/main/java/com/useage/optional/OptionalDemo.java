package com.useage.optional;

import lombok.Data;

import java.util.Optional;

@Data
public class OptionalDemo {

    static class User {
        private Address address;
        private String isocode;
        private String email;
        private String pass;

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public String getIsocode() {
            return isocode;
        }

        public void setIsocode(String isocode) {
            this.isocode = isocode;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }
    }

    static class Address {
        private Country country;

        public Optional<Country> getCountry() {
            return Optional.ofNullable(country);
        }
    }

    static class Country {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    public static void whenCreateOfNullableOptional_thenOk() {
        String name = "John";
        Optional<String> optional = Optional.ofNullable(name);
        System.out.println(optional.get());
    }

    public static void whenCheckIfPresent_thenOk() {
        User user = new User();
        user.setEmail("hello@126.com");
        user.setPass("123456");
        Optional<User> optional = Optional.ofNullable(user);
        if (optional.isPresent()) {
            System.out.println(user.getEmail());
            System.out.println(user.getPass());
        }
    }

    public static void whenEmptyValue_thenReturnDefault() {
        User user = null;
        User user1 = new User();
        user1.setEmail("world@126.com");
        User result = Optional.ofNullable(user).orElse(user1);
        User result1 = Optional.ofNullable(user).orElseGet(() -> user1);
        System.out.println(result.getEmail());
        System.out.println(result1.getEmail());
    }

    /**
     * 转换默认值，为null取默认值
     */
    public static void whenMap_thenOk() {
        User user = new User();
//        user.setEmail("hello@126.com");
        String email = Optional.ofNullable(user).map(u -> u.getEmail()).orElse("default@126.com");
        System.out.println(email);
    }

    /**
     * 过滤
     */
    public static void whenFilter_thenOk() {
        User user = new User();
//        user.setEmail("ttt@126.com");
        Optional<User> optional = Optional.ofNullable(user).filter(u -> u.getEmail() != null && u.getEmail().contains("@"));
        System.out.println(optional.isPresent());
    }

    public static void whenChaining_thenOk() {
        User user = new User();
        user.setEmail("ttt@126.com");
        String result = Optional.ofNullable(user)
                .flatMap(u -> u.getAddress())
                .flatMap(a -> a.getCountry())
                .map(c -> c.getName())
                .orElse("default");
        System.out.println(user);
    }
    public static void main(String[] args) {
        OptionalDemo demo = new OptionalDemo();
        OptionalDemo.whenCreateOfNullableOptional_thenOk();
        OptionalDemo.whenCheckIfPresent_thenOk();
        OptionalDemo.whenEmptyValue_thenReturnDefault();
        OptionalDemo.whenMap_thenOk();
        OptionalDemo.whenFilter_thenOk();
        OptionalDemo.whenChaining_thenOk();
    }
}
