// package com.fullness.ec.controller;

// import java.lang.ProcessBuilder.Redirect;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.SessionAttributes;
// import org.springframework.web.bind.support.SessionStatus;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// import com.fullness.ec.entity.Employee;
// import com.fullness.ec.form.EmployeeForm;
// import com.fullness.ec.service.EmployeeService;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// @SessionAttributes({ "employeeForm", "employeeList" })
// @RequestMapping("registeraccount")
// @Controller
// public class AddAccountController {
// @ModelAttribute("employeeForm")
// public EmployeeForm set() {
// return new EmployeeForm();
// }

// @Autowired
// EmployeeForm employeeForm;

// @GetMapping("input")
// public String input(Model model) {
// model.addAttribute("employeeList",
// employeeService.addAccount(formEmployeeAccount));
// return "registeraccount/input";
// }

// @PostMapping("confirm")
// public String comfirm(
// @Validated @ModelAttribute("employeeForm") EmployeeForm employeeForm,
// BindingResult bindingResult, Model model
// ) {
// System.out.println("employeeForm:" + employeeForm);
// System.out.println("bindingResult:" + bindingResult);
// if (bindingResult.hasErrors()) return "registeraccount/input";

// Employee employee =null;
// for(Employee emp : EmployeeService.){
// if(emp.getId().equals(employeeForm.getEmpId())){
// employee = emp;
// break;
// }

// }

// model.addAttribute("employee",employee);
// model.addAttribute("employeeForm",employeeForm);return"registeraccount/confirm";
// }

// @PostMapping("execute")
// public String execute(@ModelAttribute("employeeForm") EmployeeForm
// employeeForm,
// RedirectAttributes redirectAttributes) {
// EmployeeService.addAccount(EmployeeConverter.convertFormEmpAccountToEmpAccount(employeeForm));
// redirectAttributes.addFlashAttribute("employeeForm", employeeForm);
// return "redirect:/registeraccount/complete";
// }

// @GetMapping("complete")
// public String complete(@ModelAttribute("employeeForm") EmployeeForm
// employeeForm, SessionStatus sessionStatus) {
// System.out.println("employeeForm:" + employeeForm);
// sessionStatus.setComplete();
// }
// }
