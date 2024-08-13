package com.ms.user.service.impl;

import com.ms.user.config.IHotelServiceFeign;
import com.ms.user.dto.RankingDto;
import com.ms.user.dto.UserDTO;
import com.ms.user.dto.UserRankingsDto;
import com.ms.user.dto.jms.JmsEmailsDto;
import com.ms.user.exception.MyHandledException;
import com.ms.user.model.UserEntity;
import com.ms.user.producer.IMsEmailProducer;
import com.ms.user.repositoy.UserRepository;
import com.ms.user.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final IHotelServiceFeign iHotelServiceFeign;
    private final IMsEmailProducer iMsEmailProducer;
    @Override
    public ResponseEntity<UserEntity> save(UserDTO userDTO) {

        var currentUser = this.userRepository.findByDocument(userDTO.getDocument());

        if (currentUser.isPresent()) {
            throw new MyHandledException("User already exists");
        }

        UserEntity newUser = UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .document(userDTO.getDocument())
                .name(userDTO.getName())
                .information((userDTO.getName()))
                .email(userDTO.getEmail())
                .build();

        var userCreated = userRepository.save(newUser);

        JmsEmailsDto jmsEmailsDto = new JmsEmailsDto();
        jmsEmailsDto.setRecipient(userCreated.getEmail());
        jmsEmailsDto.setSubject("Welcome to rating's company");
        jmsEmailsDto.setMsgBody("We are happy to have you. This is a welcome message. \n Good bye!");

        this.iMsEmailProducer.generateTransactionEmail(jmsEmailsDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userCreated);
    }

    @Override
    public ResponseEntity<UserEntity> updateById(String id, UserDTO userDTO) {
        //Todo Validaciones de que no existe el usuario
        var userResponse = userRepository.findById(id);

        if(userResponse.isPresent()) {
            UserEntity user = userResponse.get();
            user.setDocument(userDTO.getDocument());
            user.setName(userDTO.getName());
            user.setEmail((userDTO.getEmail()));
            user.setInformation(userDTO.getInformation());
            var updatedUser = userRepository.save(user);

            return ResponseEntity.ok(updatedUser);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new UserEntity());
    }

    @Override
    public ResponseEntity<UserEntity> updateByDocument(String document, UserDTO userDTO) {
        //Todo Validaciones de que no existe el usuario

        var userResponse = this.userRepository
                .findByDocument(document)
                .orElseThrow(() -> new MyHandledException("User does not exist"));

        //if(userResponse.isPresent()) {
        //UserEntity user = userResponse.get();
        userResponse.setDocument(userDTO.getDocument());
        userResponse.setName(userDTO.getName());
        userResponse.setEmail((userDTO.getEmail()));
        userResponse.setInformation(userDTO.getInformation());
        var updatedUser = userRepository.save(userResponse);

        return ResponseEntity.ok(updatedUser);
        //}

        //return ResponseEntity
        //        .status(HttpStatus.NOT_FOUND)
        //        .body(new UserEntity());
    }

    @Override
    public ResponseEntity<?> deleteById(String id) {
        this.userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<UserEntity>> getAll() {
        List<UserEntity> list = userRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<UserEntity> getById(String id) {
        //Todo Validaciones de que no existe el usuario

        var user = userRepository.findById(id)
                .orElse(new UserEntity());
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserEntity> getByDocument(String document) {
        //Todo Validaciones de que no existe el usuario

        var user = userRepository.findByDocument(document)
                .orElse(new UserEntity());
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<?> getReviewByUserId(String id) {
        UserRankingsDto userRankingsDto =  this
                .userRepository
                .findById(id)
                .map(
                        user -> UserRankingsDto
                                .builder()
                                .id(user.getId())
                                .name(user.getName())
                                .document(user.getDocument())
                                .email(user.getEmail())
                                .information(user.getInformation())
                                .build()
                ).orElseThrow(()->new MyHandledException("user does not exist"));
        RankingDto[] rankings = this.restTemplate.getForObject("http://RANKING-MS/api/v1/ranking/user/"+id, RankingDto[].class);

        if (rankings != null) {
            /*
                       var listRanking = Arrays.stream(rankings).toList();
           var rankinFull = listRanking.stream()
                   .peek(ran -> {
                       var hotelResponse = this.restTemplate.getForObject(
                               "http://localhost:8080/api/v1/hotel/" + ran.getHotelId(),
                               HotelDto.class
                       );
                       ran.setHotel(hotelResponse);
                   })
                   .collect(Collectors.toList());
            * */
            var listRanking = Arrays.stream(rankings).toList();
            var rankinFull = listRanking.stream()
                    .peek(ran -> {
                        /*var hotelResponse = this.restTemplate.getForObject(
                                "http://localhost:8080/api/v1/hotel/" + ran.getHotelId(),
                                HotelDto.class
                        );*/
                        var uuid = UUID.fromString(ran.getHotelId());
                        var hotelResponse = this.iHotelServiceFeign.getHotelById(uuid);
                        ran.setHotel(hotelResponse);
                    })
                    .collect(Collectors.toList());

            userRankingsDto.setRankings(rankinFull);
        }

        return ResponseEntity.ok(userRankingsDto);
    }

}
