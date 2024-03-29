package springtodo.springtodo.embedables;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private String city;
    private String zipcode;
    private String country;

    public Address(String street, String city, String zipcode, String country) {
        setStreet(street);
        setCity(city);
        setZipcode(zipcode);
        setCountry(country);
    }

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
