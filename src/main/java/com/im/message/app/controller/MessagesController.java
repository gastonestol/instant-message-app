package com.im.message.app.controller;

import com.im.message.app.action.MessageActions;
import com.im.message.app.model.entities.Message;
import com.im.message.app.resources.MessageListResource;
import com.im.message.app.resources.MessageResource;
import com.im.message.app.resources.SendMessageResource;
import com.im.message.app.utils.JSONUtil;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;
import java.util.List;
import java.util.stream.Collectors;

public class MessagesController {

    private Long defaultLimit = 100L;
    public static final String SENDER_PROPERTY = "sender";
    public static final String RECIPIENT_PROPERTY = "recipient";
    public static final String CONTNET_PROPERTY = "content";
    public static final String TYPE_PROPERTY = "type";
    public static final String TEXT_PROPERTY = "text";
    public static final String START_PROPERTY = "start";
    public static final String LIMIT_PROPERTY = "limit";

    private MessageActions actions;

    public MessagesController(MessageActions actions){
        this.actions = actions;
    }

    public Route sendMessage = (Request req, Response rep) -> {
        JSONObject body = new JSONObject(req.body());
        JSONObject content = body.getJSONObject(CONTNET_PROPERTY);
        Message message = actions.saveMessage(
                body.getLong(SENDER_PROPERTY),
                body.getLong(RECIPIENT_PROPERTY),
                content.getString(TYPE_PROPERTY),
                content.getString(TEXT_PROPERTY)
                );
        return JSONUtil.dataToJson(new SendMessageResource(message));
    };

    public Route getMessages = (Request req, Response rep) -> {
        Long limit = req.queryParams().contains(LIMIT_PROPERTY)?
                Long.parseLong(req.queryParams(LIMIT_PROPERTY)):
                defaultLimit;
        List<Message> messageList = actions.getMessages(
                Long.parseLong(req.queryParams(RECIPIENT_PROPERTY)),
                Long.parseLong(req.queryParams(START_PROPERTY)),
                limit
        );
        return JSONUtil.dataToJson(new MessageListResource(
                messageList.stream().map(MessageResource::new).collect(Collectors.toList())));
    };

}
