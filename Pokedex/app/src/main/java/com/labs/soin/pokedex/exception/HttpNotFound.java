package com.labs.soin.pokedex.exception;

import retrofit2.adapter.rxjava.HttpException;

public class HttpNotFound {

  public final static String SERVER_INTERNET_ERROR =
      "Unable to resolve host \"multimedia.telesurtv.net\": No address associated with hostname";

  private HttpNotFound() {}

  public static boolean isHttp404(Throwable error) {
    return error instanceof HttpException && ((HttpException) error).code() == 404;
  }
}
