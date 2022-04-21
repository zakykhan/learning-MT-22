package com.sagapattern.commonService.AppUtil;

public class AppUtil {

    public enum orderStatus {

        CREATED("created"), CANCELLED("cancelled"), PAID("paid"), APPROVED("approved");

        final String value;

        orderStatus(String i) {
            value = i;
        }

        public String getValue() {
            return value;
        }
    }

    public enum shipmentStatus {

        SHIPPED("shipped"), IN_TRANSIT("in_transit"), COMPLETED("completed");

        final String value;

        shipmentStatus(String i) {
            value = i;
        }

        public String getValue() {
            return value;
        }
    }
}
