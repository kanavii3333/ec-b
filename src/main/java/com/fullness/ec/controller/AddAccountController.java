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

    @GetMapping("input")
    public String input(Model model) {

        model.addAttribute("employeeList", employeeService.getEmployeeList());
        return "registeraccount/input";
    }

    @PostMapping("confirm")
    public String comfirm(
            @ModelAttribute("employeeForm") EmployeeForm employeeForm,
            BindingResult bindingResult, Model model) {
        System.out.println("employeeForm:" + employeeForm);
        System.out.println("bindingResult:" + bindingResult);
        if (bindingResult.hasErrors())
            return "registeraccount/input";

        Employee employee = null;
        for (Employee emp : employeeService.getEmployeeList()) {
            if (emp.getEmpId().equals(employeeForm.getId())) {
                employee = emp;
                break;
            }

        }

        model.addAttribute("employee", employee);
        model.addAttribute("employeeForm", employeeForm);
        return "registeraccount/confirm";
    }

    @PostMapping("execute")
    public String execute(@ModelAttribute("employeeForm") EmployeeForm employeeForm,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("employeeForm", employeeForm);
        return "redirect:/registeraccount/complete";
    }

    @GetMapping("complete")

    public String complete(@ModelAttribute("employeeForm") EmployeeForm employeeForm, SessionStatus sessionStatus) {
        System.out.println("employeeForm:" + employeeForm);
        sessionStatus.setComplete();
        return "registeraccount/complete";
    }
}
