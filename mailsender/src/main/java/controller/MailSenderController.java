package controller;

import controller.doc.MailSenderDoc;
import dto.MailDto;
import model.MailEntity;
import org.springframework.http.ResponseEntity;

public class MailSenderController implements MailSenderDoc {
    @Override
    public ResponseEntity<MailEntity> sendMail(MailDto userEntity) {
        return null;
    }
}
