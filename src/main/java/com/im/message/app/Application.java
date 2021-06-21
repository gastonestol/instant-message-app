package com.im.message.app;

import com.im.message.app.action.AuthActions;
import com.im.message.app.action.MessageActions;
import com.im.message.app.action.UserActions;
import com.im.message.app.controller.AuthController;
import com.im.message.app.controller.HealthController;
import com.im.message.app.controller.MessagesController;
import com.im.message.app.controller.UsersController;
import com.im.message.app.filter.TokenValidatorFilter;
import com.im.message.app.model.implementations.MessageRepositoryImpl;
import com.im.message.app.model.implementations.MetadataRepositoryImpl;
import com.im.message.app.model.implementations.UserRepositoryImpl;
import com.im.message.app.model.repositories.MessageRepository;
import com.im.message.app.model.repositories.MetadataRepository;
import com.im.message.app.model.repositories.UserRepository;
import com.im.message.app.services.MessageService;
import com.im.message.app.services.MetadataService;
import com.im.message.app.services.TokenService;
import com.im.message.app.services.UserService;
import com.im.message.app.utils.Path;
import static spark.Spark.*;

public class Application {

    private static final String SECRET_JWT = "secret_jwt";


    public static void main(String[] args) {
        Application application = new Application();
        application.init();

    }

    void init(){

        // Spark Configuration
        port(8080);

        //Create Repositories
        UserRepository userRepository = new UserRepositoryImpl();
        MessageRepository messageRepository = new MessageRepositoryImpl();
        MetadataRepository metadataRepository = new MetadataRepositoryImpl();

        //Create services
        TokenService tokenService = new TokenService(SECRET_JWT);
        UserService userService = new UserService(userRepository);
        MessageService messageService = new MessageService(messageRepository);
        MetadataService metadataService = new MetadataService(metadataRepository);

        //Create actions
        MessageActions messageActions = new MessageActions(messageService,metadataService);
        UserActions userActions = new UserActions(userService,tokenService);
        AuthActions authActions = new AuthActions(userService,tokenService);

        //Create Controllers
        AuthController authController = new AuthController(authActions);
        UsersController usersController = new UsersController(userActions);
        MessagesController messagesController = new MessagesController(messageActions);

        // Configure Endpoints
        before("/*" , new TokenValidatorFilter(tokenService));
        // Users
        post(Path.USERS, usersController.createUser);
        get(Path.USERS, usersController.getUser);
        // Auth
        post(Path.LOGIN, authController.login);
        // Messages
        post(Path.MESSAGES, messagesController.sendMessage);
        get(Path.MESSAGES, messagesController.getMessages);
        put(Path.MESSAGES, messagesController.updateMessage);
        // Health
        post(Path.HEALTH, HealthController.check);
        //type and CORS
        options("/*", (request, response) -> "OK");
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "OPTIONS,ORDER,GET,PUT,POST,DELETE,PATCH,ORIGIN");
            response.header("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept,Authorization");
            response.header("Content-Type", "application/json");
        });
    }


}
