package com.furkan.tradeport.service;

import com.furkan.tradeport.event.UserDeletedEvent;
import com.furkan.tradeport.exception.UserNotFoundException;
import com.furkan.tradeport.model.User;
import com.furkan.tradeport.port.DeleteUserPort;
import com.furkan.tradeport.port.EventPublisherPort;
import com.furkan.tradeport.port.ReadUserPort;
import com.furkan.tradeport.usecase.DeleteUserUseCase;
import com.furkan.tradeport.valueobject.UserId;

import java.util.Optional;

public class DeleteUserService implements DeleteUserUseCase {

    private final DeleteUserPort deleteUserPort;
    private final ReadUserPort readUserPort;
    private final EventPublisherPort eventPublisherPort;

    public DeleteUserService(DeleteUserPort deleteUserPort, ReadUserPort readUserPort, EventPublisherPort eventPublisherPort) {
        this.deleteUserPort = deleteUserPort;
        this.readUserPort = readUserPort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public String deleteUserById(String userId) {
        Optional<User> user = readUserPort.findById(UserId.of(userId));
        if(user.isEmpty()) {
            throw new UserNotFoundException("User can not found this id : "+userId);
        }
        deleteUserPort.delete(user.get());
        eventPublisherPort.publish(new UserDeletedEvent(userId));
        return "Kullanıcı başarıyla silindi";
    }
}
