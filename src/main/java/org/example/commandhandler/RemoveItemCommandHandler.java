package org.example.commandhandler;

import org.example.command.RemoveItemCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveItemCommandHandler {

    @Autowired
    private Repository repository;

    @CommandHandler
    public void handle(RemoveItemCommand removeItemCommand){


    }
}
