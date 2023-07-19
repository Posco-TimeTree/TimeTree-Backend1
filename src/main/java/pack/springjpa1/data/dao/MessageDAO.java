package pack.springjpa1.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pack.springjpa1.data.dto.MessageDTO;
import pack.springjpa1.data.entity.MessageEntity;
import pack.springjpa1.data.repository.MessageRepository;

import java.util.List;

@Repository
public class MessageDAO {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageDAO(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void createMessage(MessageDTO dto) {
        MessageEntity messageEntity = new MessageEntity();

        messageEntity.setContent( dto.getContent());
        messageEntity.setUserId(dto.getUserId());
        messageEntity.setMessageId( dto.getMessageId());
        messageEntity.setBoxId(dto.getBoxId());
        this.messageRepository.save(messageEntity);
    }

    public List<MessageEntity> readAllMessages() {
        return this.messageRepository.findAll();
    }
    public MessageEntity readMessagesByUserIdAndBoxId(Long userId, Long boxId) {
        return  this.messageRepository.findByUserIdAndBoxId(userId, boxId);
    }
}
