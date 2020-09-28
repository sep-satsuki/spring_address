package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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



//ページング処理、件数制御、一覧画面表示
	   @RequestMapping(value="/user/list", method=RequestMethod.GET)
	    public String list (@PageableDefault(page = 0, size = 10) Pageable pageable, Model model) {
	        Page<User> userPage = userService.getAllUser(pageable);
	        PageWrapper<User> page = new PageWrapper<User>(userPage, "/user/list");
	        model.addAttribute("page", page);
	        model.addAttribute("userlist", page.getContent());

	        return "/user/list";
	    }





  /**
   * ユーザー新規登録画面を表示
   * @param model Model
   * @return ユーザー情報一覧画面
   */
  @GetMapping(value = "/user/add")
  public String displayAdd(Model model) {
    model.addAttribute("userRequest", new UserRequest());
    return "user/add";
  }


//登録画面から確認画面への遷移
  @RequestMapping(value = "user/AddCheck", method = RequestMethod.POST)
	public String AddCheck(@ModelAttribute UserRequest userRequest,Model model) {
	    model.addAttribute("userRequest",userRequest);
 		return "user/AddCheck";
 	}

  //ユーザー情報の登録
  @RequestMapping(value = "user/create", method = RequestMethod.POST)
  public String create(@ModelAttribute UserRequest userRequest,Model model) {
	  userService.create(userRequest);
	    return "redirect:/user/list";
  }

//編集画面への画面遷移
  @GetMapping("/user/{id}")
  public String displayView(@PathVariable Long id, Model model) {
    User user = userService.findById(id);
    model.addAttribute("userRequest", user);
    return "user/Edit";
  }

//編集画面から編集確認画面への遷移
  @RequestMapping(value = "user/EditCheck", method = RequestMethod.POST)
	public String EditCheck(@ModelAttribute UserRequest userRequest,Model model) {
	    model.addAttribute("userRequest",userRequest);
 		return "user/EditCheck";
 	}

  //編集情報の登録
  @RequestMapping(value = "user/update", method = RequestMethod.POST)
    public String update(@ModelAttribute UserRequest userRequest,Model model) {
	 userService.update(userRequest);
	    return "redirect:/user/list";
  }


  /**
   * ユーザー編集画面を表示
   * @param id 表示するユーザーID
   * @param model Model
   * @return ユーザー編集画面
   */
 // @PostMapping("/user/edit")
  //public String displayEdit(@ModelAttribute User userRequest,Model model) {
      //User user = userService.findById(id);
     // UserRequest userRequest = new UserRequest();
	  /*
      userRequest.setId(user.getId());
      userRequest.setName(user.getName());
      userRequest.setTel(user.getTel());
      userRequest.setAddress(user.getAddress());
      */
 //     model.addAttribute("userRequest", userRequest);
 //     return "user/edit";
 // }
  /**
   * ユーザー更新
   * @param userRequest リクエストデータ
   * @param model Model
   * @return ユーザー情報詳細画面
   */
  /*
  @RequestMapping(value="/user/edit", method=RequestMethod.POST)
  public String edit(@Validated @ModelAttribute UserRequest userRequest, BindingResult result, Model model) {
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

*/
//  @RequestMapping(value = "user/EditCheck", method = RequestMethod.POST)
//	public String EditCheck(@ModelAttribute("User") User form) {
              // 遷移先を返す
//		return "user/EditCheck";
//	}


@RequestMapping(value = "user/Delete", method = RequestMethod.POST)
public String Delete(Model model){
          // 空のフォームオブジェクトをModelに設定
	model.addAttribute("userRequest", new UserRequest());
          // 遷移先を返す
	return "user/Delete";
}


}


