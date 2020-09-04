package com.example.okhttp_crud;

public class ProductDetails {
    String Product, Company, Address, Email, Phone;

    public ProductDetails(String product, String company, String address, String email, String phone) {
        this.setProduct(product);
        this.setCompany(company);
        this.setAddress(address);
        this.setEmail(email);
        this.setPhone(phone);
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
