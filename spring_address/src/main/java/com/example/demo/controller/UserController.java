package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
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

  @RequestMapping(value = "user/AddCheck", method = RequestMethod.POST)
  public String create(@Validated @ModelAttribute UserRequest userRequest, BindingResult result, Model model) {
    if (result.hasErrors()) {
      List<String> errorList = new ArrayList<String>();
      for (ObjectError error : result.getAllErrors()) {
        errorList.add(error.getDefaultMessage());
      }
      model.addAttribute("validationError", errorList);
      return "user/AddCheck";
    }
    // ユーザー情報の登録
    userService.create(userRequest);
    return "redirect:/user/list";
  }


  @RequestMapping(value = "user/Edit", method = RequestMethod.GET)
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