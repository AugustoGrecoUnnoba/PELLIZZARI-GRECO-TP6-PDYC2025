package ar.edu.unnoba.pdyc.user_service.service;

import ar.edu.unnoba.pdyc.user_service.model.User;
import ar.edu.unnoba.pdyc.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public List<User> getUsers();
    public User findById(Long id);
}
