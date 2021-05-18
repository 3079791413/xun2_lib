package com.example.common;

public class PayEvent {
    public PayEvent(boolean isPaySuccess) {
        this.isPaySuccess = isPaySuccess;
    }

    public boolean isPaySuccess = false;
}
