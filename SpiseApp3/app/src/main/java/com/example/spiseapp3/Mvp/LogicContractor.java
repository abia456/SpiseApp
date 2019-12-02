package com.example.spiseapp3.Mvp;

public interface LogicContractor {


    interface View1{
        void onSuccess(String message);
        void onError(String message);
        void onSuccess(String message, Boolean b);
    }

    interface Presenter{
        void doLogin(String email,String pass);
    }

}
