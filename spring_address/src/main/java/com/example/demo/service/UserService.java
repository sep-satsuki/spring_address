package com.example.demo.service;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
/**
 * ユーザー情報 Service
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {
  /**
   * ユーザー情報 Repository
   */
  @Autowired
  private UserRepository userRepository;
  /**
   * ユーザー情報 全検索
   * @return 検索結果
   */
  public List<User> searchAll() {
    return userRepository.findAll();
  }
  /**
   * ユーザー情報 新規登録
   * @param user ユーザー情報
   */
  public void create(UserRequest userRequest) {
    Date now = new Date();
    User user = new User();
    user.setName(userRequest.getName());
    user.setAddress(userRequest.getAddress());
    user.setTel(userRequest.getTel());
    //user.setErrmsg(now);
    userRepository.save(user);
  }

  /**
   * ユーザー情報 主キー検索
   * @return 検索結果
   */
  public User findById(Long id) {
      return userRepository.findById(id).get();
  }


  /**
   * ユーザー情報 更新
   * @param user ユーザー情報
   */
  public void update(UserRequest userRequest) {
      User user = findById(userRequest.getId());
      user.setAddress(userRequest.getAddress());
      user.setName(userRequest.getName());
      user.setTel(userRequest.getTel());
      userRepository.save(user);
  }
}