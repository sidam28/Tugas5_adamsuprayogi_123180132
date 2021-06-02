package com.example.localdb;

public class EmployeeModelClass {

    private Integer id;
    private String name, email, pesan;

    public EmployeeModelClass(String name, String email, String pesan) {
        this.name = name;
        this.email = email;
        this.pesan = pesan;
    }

    public EmployeeModelClass(Integer id, String name, String email, String pesan) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pesan = pesan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
