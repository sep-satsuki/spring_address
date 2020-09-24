package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.wrapper.PageWrapper;
/**
 * ユーザー情報 Controller
 */
@Controller


public class UserController {

	 @Autowired
	    private UserService userService;

	  /**
	   * ユーザー情報一覧画面を表示
	   * @param model Model
	   * @return ユーザー情報一覧画面
	   */
	  @GetMapping(value = "/user/list")

	  public String displayList(Model model) {
		  List<User> userlist = userService.searchAll();
	    model.addAttribute("userlist", userlist);
	    return "user/list";
	  }



//ページング処理
	   //@RequestMapping(value="/user/list", method=RequestMethod.GET)
	    public String getUserList(Model model, Pageable pageable) {
	        Page<User> userPage = userService.getAllUser(pageable);
	        PageWrapper<User> page = new PageWrapper<User>(userPage, "/user/userlist");
	        model.addAttribute("page", page);
	        model.addAttribute("users", page.getContent());

	        return "/user/list";
	    }


//最大件数設定
	    @GetMapping
	    public void list (@PageableDefault(page = 0, size = 10) Pageable pageable, Model model) {

	        Page<User> userPage =userService.getAllUser(pageable);
	        PageWrapper<User> page = new PageWrapper<User>(userPage, "/user/list");
	        model.addAttribute("page", userPage);
	        model.addAttribute("users", userPage.getContent());

	    }



  /**
   * @param model Model
   * @return ユーザー情報一覧画面
   */
  @GetMapping(value = "/user/add")
  public String displayAdd(Model model) {
    model.addAttribute("userRequest", new UserRequest());
    return "user/add";
  }

  /**
   * ユーザー新規登録
   * @param userRequest リクエストデータ
   * @param model Model
   * @return ユーザー情報一覧画面
   */
  @RequestMapping(value = "/user/create", method = RequestMethod.POST)
  public String create(@Validated @ModelAttribute UserRequest userRequest, BindingResult result, Model model) {
    if (result.hasErrors()) {
      List<String> errorList = new ArrayList<String>();
      for (ObjectError error : result.getAllErrors()) {
        errorList.add(error.getDefaultMessage());
      }
      model.addAttribute("validationError", errorList);
      return "user/add";
    }
    // ユーザー情報の登録
    userService.create(userRequest);
    return "redirect:/user/list";
  }

  //@RequestMapping(value = "user/list", method = RequestMethod.POST)
	public String list(@ModelAttribute UserRequest userRequest,Model model) {
	    model.addAttribute("userRequest",userRequest);
           // 遷移先を返す
		return "user/list";
	}



  @RequestMapping(value = "user/AddCheck", method = RequestMethod.POST)
 	public String AddCheck(@ModelAttribute UserRequest userRequest,Model model) {
	    model.addAttribute("userRequest",userRequest);
             // 遷移先を返す
 		return "user/AddCheck";
 	}

  /**
   * ユーザー編集画面を表示
   * @param id 表示するユーザーID
   * @param model Model
   * @return ユーザー編集画面
   */
  @GetMapping("/user/{id}/edit")
  public String displayEdit(@PathVariable Long id, Model model) {
      User user = userService.findById(id);
      UserRequest userRequest = new UserRequest();
      userRequest.setId(user.getId());
      userRequest.setName(user.getName());
      userRequest.setTel(user.getTel());
      userRequest.setAddress(user.getAddress());
      model.addAttribute("userRequest", userRequest);
      return "user/edit";
  }
  /**
   * ユーザー更新
   * @param userRequest リクエストデータ
   * @param model Model
   * @return ユーザー情報詳細画面
   */
  @RequestMapping(value="/user/edit", method=RequestMethod.POST)
  public String update(@Validated @ModelAttribute UserRequest userRequest, BindingResult result, Model model) {
      if (result.hasErrors()) {
          List<String> errorList = new ArrayList<String>();
          for(ObjectError error : result.getAllErrors()) {
              errorList.add(error.getDefaultMessage());
          }
            model.addAttribute("validationError", errorList);
            return "user/edit";
          }
      // ユーザー情報の更新
      userService.update(userRequest);
      return String.format("redirect:/user/%d", userRequest.getId());
  }


  @RequestMapping(value = "user/EditCheck", method = RequestMethod.POST)
	public String EditCheck(@ModelAttribute("User") User form) {
              // 遷移先を返す
		return "user/EditCheck";
	}


@RequestMapping(value = "user/Delete", method = RequestMethod.POST)
public String Delete(Model model){
          // 空のフォームオブジェクトをModelに設定
	model.addAttribute("userRequest", new UserRequest());
          // 遷移先を返す
	return "user/Delete";
}


}


