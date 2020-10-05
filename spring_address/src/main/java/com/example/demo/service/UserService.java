package com.example.demo.service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Page<User> getAllUser(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Transactional(rollbackOn = Exception.class)

	/**
	 * ユーザー情報 新規登録
	 * @param user ユーザー情報
	 */
	public void create(UserRequest userRequest) {
		User user = new User();
		user.setName(userRequest.getName());
		user.setAddress(userRequest.getAddress());
		String tel=(userRequest.getTel());
		if(tel!=null){
			tel=tel.replace("-","");
		}
		user.setTel(tel);
		user.setDelete_flg(0);
		userRepository.save(user);
	}

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
		String tel=(userRequest.getTel());
		if(tel!=null){
			tel=tel.replace("-","");
		}
		user.setTel(tel);
		user.setDelete_flg(0);
		userRepository.save(user);
	}

	/**
	 * 編集画面のハイフンの表示
	 *
	 */
	public void check(User  user) {

		String tel1 = user.getTel();
		String tel = "";
		if (tel1.length() == 11) {
		Pattern p = Pattern.compile("(\\d{3})(\\d{4})(\\d{4})");

        Matcher abc = p.matcher(tel1);

        tel = abc.replaceAll("$1-$2-$3");
		}
        user.setTel(tel);
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
		return userRepository.findSearch(address,pageable);
	}


}