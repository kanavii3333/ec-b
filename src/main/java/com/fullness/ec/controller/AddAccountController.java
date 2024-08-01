package com.fullness.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullness.ec.entity.Employee;

import com.fullness.ec.form.EmployeeForm;
import com.fullness.ec.form.EmployeeFormValidator;
import com.fullness.ec.service.EmployeeService;
import com.fullness.ec.service.EmployeeServiceImpl;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

@SessionAttributes({ "employeeForm", "employeeList" })
@RequestMapping("admin/registeraccount")
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

    @Autowired
    EmployeeFormValidator validator;

    @InitBinder("employeeForm")
    public void InitBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping("input")
    public String input(Model model) {

        model.addAttribute("employeeList", employeeService.getEmployeeList());
        return "account/register/input";
    }

    @PostMapping("confirm")
    public String comfirm(
            @Validated @ModelAttribute("employeeForm") EmployeeForm employeeForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {
        System.out.println("employeeForm:" + employeeForm);
        System.out.println("bindingResult:" + bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("employeeForm", employeeForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.employeeForm",
                    bindingResult);
            return "redirect:/registeraccount/input";
        }

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

    @GetMapping("confirm")
    public String comfirmGet(
            @ModelAttribute("employeeForm") EmployeeForm employeeForm,
            Model model) {
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

    // @GetMapping("confirm")
    // public String confirmGet(RedirectAttributes redirectAttributes) {

    // return "redirect:/registeraccount/error";
    // }

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
        if(employeeForm.getId()==null) return "redirect:/menu";
        Employee employee = null;
        for (Employee emp : employeeService.getEmployeeList()) {
            if (emp.getEmpId().equals(employeeForm.getId())) {
                employee = emp;
                model.addAttribute("employeeName", employee.getEmpName());
                model.addAttribute("employeeAccountName", employeeForm.getEmpAccountName());
                break;
            }
        }
        
        sessionStatus.setComplete();
        return "account/register/complete";

    }
}