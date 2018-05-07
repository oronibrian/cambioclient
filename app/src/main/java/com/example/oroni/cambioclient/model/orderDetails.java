package com.example.oroni.cambioclient.model;

public class orderDetails {

    String destination;
    String status;
    String origin;
    String details;
    int tracking_no,customer_id;

    public orderDetails(String destination, String status, String origin, String details, int tracking_no, int customer_id) {
        this.destination = destination;
        this.status = status;
        this.origin = origin;
        this.details = details;
        this.tracking_no = tracking_no;
        this.customer_id = customer_id;
    }

    public String getDestination() {
        return destination;
    }

    public String getStatus() {
        return status;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDetails() {
        return details;
    }

    public int getTracking_no() {
        return tracking_no;
    }

    public int getCustomer_id() {
        return customer_id;
    }
}
