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

    private String fromCurrency;
    private String toCurrency;
    private double rate;

    private double value;
    private double convertedValue;

    public CurrencyResponse() {}

    public CurrencyResponse(Long id, String fromCurrency, String toCurrency, double rate, double value, double convertedValue) {
        this.id = id;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
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

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
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
                ", from='" + fromCurrency + '\'' +
                ", to='" + toCurrency + '\'' +
                ", rate=" + rate +
                ", value=" + value +
                ", convertedValue=" + convertedValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyResponse that = (CurrencyResponse) o;
        return Double.compare(rate, that.rate) == 0 && Double.compare(value, that.value) == 0 && Double.compare(convertedValue, that.convertedValue) == 0 && Objects.equals(id, that.id) && Objects.equals(fromCurrency, that.fromCurrency)
                && Objects.equals(toCurrency, that.toCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromCurrency, toCurrency, rate, value, convertedValue);
    }
}
