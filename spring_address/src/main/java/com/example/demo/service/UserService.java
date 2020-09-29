package com.example.demo.service;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
/**
 * ユーザー情報 Service
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> getAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

     /**
     * ユーザー情報 全検索
     * @return 検索結果
     *     public List<User> searchAll() {
     return userRepository.findAll();
 }
     */





@Transactional(rollbackOn = Exception.class)

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
    user.setDelete_flg(0);
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

      user.setName(userRequest.getName());
      user.setAddress(userRequest.getAddress());
      user.setTel(userRequest.getTel());
      user.setDelete_flg(0);
      userRepository.save(user);
  }

  /**
   * ユーザー情報 物理削除
   * @param id ユーザーID
   */
  public void delete(UserRequest userRequest) {
      User user = findById(userRequest.getId());
      userRepository.delete(user);
  }

  /**
  * 検索
  * @return 検索結果
  */
  public Page<User> findSearch(String address, Pageable pageable) {
	  System.out.println(address);
  return userRepository.findSearch(address,pageable);
}


}