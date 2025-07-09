package ar.edu.unnoba.pdyc.user_service.service;

import ar.edu.unnoba.pdyc.user_service.model.User;
import ar.edu.unnoba.pdyc.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{
    private UserRepository repository;

    @Autowired
    public UserServiceImp(UserRepository repository){
        this.repository = repository;
    }
    @Override
    public List<User> getUsers() {
        return repository.findAll();
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
