package com.engine.rule.domain;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by manoj on 23/08/18.
 */
public class SignalData {
    private String dateFormat = "yyyy-MM-dd hh:mm:ss";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

    @SerializedName("signal")
    private String signal;

    @SerializedName("value_type")
    private String valueType;

    @SerializedName("value")
    private String value;

    public String getSignal() {
        return signal;
    }

    public DataType getValueType() {
        return DataType.valueOf(valueType.toUpperCase());
    }

    public Object getValue() {
        switch (getValueType()) {
            case STRING: {
                return LevelType.valueOf(value.toUpperCase());
            }
            case INTEGER: {
                return Double.valueOf(value);
            }
            case DATETIME: {
                try {
                    return simpleDateFormat.parse(value);
                } catch (ParseException e) {
                    throw new IllegalArgumentException("Date is incorrect : Date format should be " + dateFormat , e);
                }
            }
            default: {
                return value;
            }

        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SignalData that = (SignalData) o;

        if (!signal.equals(that.signal)) return false;
        if (!valueType.equals(that.valueType)) return false;
        return value.equals(that.value);

    }

    @Override
    public int hashCode() {
        int result = signal.hashCode();
        result = 31 * result + valueType.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SignalData{" +
                "signal='" + signal + '\'' +
                ", valueType='" + valueType + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
