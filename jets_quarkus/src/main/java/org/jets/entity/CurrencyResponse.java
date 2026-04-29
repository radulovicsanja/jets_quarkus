package org.jets.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;


@Entity
public class CurrencyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String from;
    private String to;
    private double rate;

    private double value;
    private double convertedValue;

    public CurrencyResponse() {}

    public CurrencyResponse(Long id, String from, String to, double rate, double value, double convertedValue) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.value = value;
        this.convertedValue = convertedValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(double convertedValue) {
        this.convertedValue = convertedValue;
    }


    @Override
    public String toString() {
        return "CurrencyResponse{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", rate=" + rate +
                ", value=" + value +
                ", convertedValue=" + convertedValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyResponse that = (CurrencyResponse) o;
        return Double.compare(rate, that.rate) == 0 && Double.compare(value, that.value) == 0 && Double.compare(convertedValue, that.convertedValue) == 0 && Objects.equals(id, that.id) && Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, rate, value, convertedValue);
    }
}
