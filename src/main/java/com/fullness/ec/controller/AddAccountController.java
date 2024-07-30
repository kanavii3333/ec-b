package com.fullness.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullness.ec.entity.Employee;

import com.fullness.ec.form.EmployeeForm;

import com.fullness.ec.service.EmployeeService;
import com.fullness.ec.service.EmployeeServiceImpl;

import org.springframework.web.bind.annotation.GetMapping;

@SessionAttributes({ "employeeForm", "employeeList" })
@RequestMapping("registeraccount")
@Controller
public class AddAccountController {

    @ModelAttribute("employeeForm")
    public EmployeeForm setupForm() {
        return new EmployeeForm();
    }

    // @Autowired
    // private EmployeeForm employeeForm;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("input")
    public String input(Model model) {

        model.addAttribute("employeeList", employeeService.getEmployeeList());
        return "account/register/input";
    }

    @PostMapping("confirm")
    public String comfirm(
            @ModelAttribute("employeeForm") EmployeeForm employeeForm,
            BindingResult bindingResult, Model model) {
        System.out.println("employeeForm:" + employeeForm);
        System.out.println("bindingResult:" + bindingResult);
        if (bindingResult.hasErrors())
            return "account/register/input";

        // アカウントが既に存在するかチェック
        if (employeeService.isAccountExist(employeeForm) && employeeService.isAccountNameExist(employeeForm)) {
            model.addAttribute("errorMessage", "この社員は既に登録されています");
            model.addAttribute("errorMessage1", "このアカウント名は既に登録されています");
            model.addAttribute("employeeList", employeeService.getEmployeeList());
            return "account/register/input";
        }
        if (employeeService.isAccountExist(employeeForm)) {
            model.addAttribute("errorMessage", "この社員は既に登録されています");
            model.addAttribute("employeeList", employeeService.getEmployeeList());
            return "account/register/input";
        }
        if (employeeService.isAccountNameExist(employeeForm)) {
            model.addAttribute("errorMessage1", "このアカウント名は既に登録されています");
            model.addAttribute("employeeList", employeeService.getEmployeeList());
            return "account/register/input";
        }
        // アカウント名が既に存在するかチェック
        // if (employeeService.isAccountNameExist(employeeForm)) {
        // model.addAttribute("errorMessage1", "このアカウント名は既に登録されています");
        // model.addAttribute("employeeList", employeeService.getEmployeeList());
        // return "account/register/input";
        // }

        Employee employee = null;
        for (Employee emp : employeeService.getEmployeeList()) {
            if (emp.getEmpId().equals(employeeForm.getId())) {
                employee = emp;
                break;
            }

        }

        model.addAttribute("employee", employee);
        model.addAttribute("employeeForm", employeeForm);
        return "account/register/confirm";
    }

    @PostMapping("execute")
    public String execute(@ModelAttribute("employeeForm") EmployeeForm employeeForm,
            RedirectAttributes redirectAttributes) {
        employeeServiceImpl.addAccount(employeeForm);
        redirectAttributes.addFlashAttribute("employeeForm", employeeForm);
        return "redirect:/registeraccount/complete";
    }

    @GetMapping("complete")

    public String complete(@ModelAttribute("employeeForm") EmployeeForm employeeForm, Model model,
            SessionStatus sessionStatus) {
        System.out.println("employeeForm:" + employeeForm);
        // model.addAttribute("employeeName", );

        Employee employee = null;
        for (Employee emp : employeeService.getEmployeeList()) {
            if (emp.getEmpId().equals(employeeForm.getId())) {
                employee = emp;
                break;
            }
        }
        model.addAttribute("employee", employee);
        model.addAttribute("employeeAccountName", employeeForm.getEmpAccountName());
        sessionStatus.setComplete();
        return "account/register/complete";

    }
}