package com.wordle.demo.service;

import com.wordle.demo.controller.dto.TopsDto;
import com.wordle.demo.controller.dto.UserRating;
import com.wordle.demo.repository.UserRepository;
import com.wordle.demo.repository.entity.UserEntity;
import com.wordle.demo.service.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public MyUser getUserByUsername(String login)
            throws UsernameNotFoundException {
        Optional<UserEntity> user = repository.findByLogin(login);

        if(user.isPresent())
            return new MyUser(user.get());
        else throw new UsernameNotFoundException(login + " not found");
    }

    public TopsDto getTops(Long userId){
        ArrayList<UserEntity> topWins = repository.getTopByWins();
        ArrayList<UserEntity> topRatio = repository.getTopByRatio();
        if (userId != MyUser.ANONYMOUS_ID){
            Optional<UserEntity> me = repository.findById(userId);
            if(me.isPresent()){
                UserEntity userEntity = me.get();
                boolean fl = false;
                for (UserEntity user : topWins) {
                    if (userEntity.getId() == user.getId()){
                        fl = true;
                        break;
                    }
                }
                if (!fl) topWins.add(userEntity);

                fl = false;
                for (UserEntity user : topRatio) {
                    if (userEntity.getId() == user.getId()){
                        fl = true;
                        break;
                    }
                }
                if (!fl) topRatio.add(userEntity);
            }

        }
        ArrayList<UserRating> topByWins = new ArrayList<>();
        ArrayList<UserRating> topByRatio = new ArrayList<>();

        for (UserEntity user : topWins) {
            topByWins.add(new UserRating(user.getLogin(),
                    user.getWins(), user.getGames()));

        }

        for (UserEntity user : topRatio) {
            topByRatio.add(new UserRating(user.getLogin(),
                    user.getWins(), user.getGames()));

        }
        return new TopsDto(topByWins, topByRatio);
    }


}
