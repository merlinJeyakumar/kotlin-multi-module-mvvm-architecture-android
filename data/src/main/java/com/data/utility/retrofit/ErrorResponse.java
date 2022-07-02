package com.data.utility.retrofit;

public class ErrorResponse {
  Error error;
  
  public static class Error {
    Data data;
  
    public static class Data {
       String message;
    }  
  }
}