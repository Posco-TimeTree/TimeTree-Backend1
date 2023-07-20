package pack.springjpa1.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.springjpa1.data.dao.MessageDAO;
import pack.springjpa1.data.dto.MessageDTO;
import pack.springjpa1.data.entity.MessageEntity;

import java.util.List;

@Service
public class MessageService {
    private final MessageDAO messageDAO;

    @Autowired
    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public void createMessage(MessageDTO dto) {
        messageDAO.createMessage(dto);
    }

    public List<MessageEntity> readAllMessages() {
        return messageDAO.readAllMessages();
    }

    public MessageEntity readMessagesByUserId(Long userId) {
        return messageDAO.readMessagesByUserId(userId);
    }
}
