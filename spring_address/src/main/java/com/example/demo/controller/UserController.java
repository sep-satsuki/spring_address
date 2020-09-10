package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
/**
 * ユーザー情報 Controller
 */
@Controller
public class UserController {
  /**
   * ユーザー情報 Service
   */
  @Autowired
  UserService userService;


  @GetMapping(value = "/user/add")

  public String displayAdd(Model model) {

    model.addAttribute("userRequest", new UserRequest());
    return "user/add";
  }




  @RequestMapping(value = "user/AddCheck", method = RequestMethod.POST)
 	public String AddCheck(@ModelAttribute("User") User form) {
             // 遷移先を返す
 		return "user/AddCheck";
 	}

  @RequestMapping(value = "user/Edit", method = RequestMethod.POST)
	public String Edit(Model model){
              // 空のフォームオブジェクトをModelに設定
		model.addAttribute("User", new User());
              // 遷移先を返す
		return "user/Edit";
	}

  @RequestMapping(value = "user/EditCheck", method = RequestMethod.POST)
	public String EditCheck(@ModelAttribute("User") User form) {
              // 遷移先を返す
		return "user/EditCheck";
	}


@RequestMapping(value = "user/ｌist", method = RequestMethod.POST)
public String Delete(Model model){
          // 空のフォームオブジェクトをModelに設定
	model.addAttribute("User", new User());
          // 遷移先を返す
	return "user/Delete";
}


}