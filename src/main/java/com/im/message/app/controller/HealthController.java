package com.im.message.app.controller;

import com.im.message.app.resources.HealthResource;
import com.im.message.app.utils.JSONUtil;
import spark.Request;
import spark.Response;
import spark.Route;

public class HealthController {

    public static Route check = (Request req, Response rep) -> JSONUtil.dataToJson(new HealthResource());

}
