package pai.final_project.controllers;

import org.springframework.data.domain.Sort;
import org.springframework.validation.ObjectError;
import pai.final_project.dao.*;
import pai.final_project.entity.*;

import java.security.Principal;
import java.util.*;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pai.final_project.enums.UserRoles;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao userDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private SubjectDao subjectDao;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model m) {
        m.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerPagePOST(@Valid User user, BindingResult binding) {
        if (binding.hasErrors()) {
            return "register";
        }
        User user1 = userDao.findByLogin(user.getLogin());
        if (user1 == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            switch (user.getRole()) {
                case STUDENT:
                    Student student = new Student(user);
                    studentDao.save(student);
                    break;
                case TEACHER:
                    Teacher teacher = new Teacher(user);
                    teacherDao.save(teacher);
                    break;
            }
        } else {
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/")
    public String mainPage(Model m, Principal principal) {
        User user = userDao.findByLogin(principal.getName());
        List<Subject> subjects = subjectDao.findAll(Sort.by(Sort.Direction.ASC, "name"));
        int gradesMaxCount = 1;
        LinkedHashMap<Subject, List<Grade>> gradesBySubject = new LinkedHashMap<>();

        switch (user.getRole()) {
            case STUDENT:
                for (var subject : subjects) {
                    var grades = gradeDao.findAllByStudentLoginAndSubjectName(user.getLogin(), subject.getName());
                    gradesBySubject.put(subject, grades);
                    gradesMaxCount = Math.max(gradesMaxCount, grades.size());
                }

                m.addAttribute("subjects", gradesBySubject);
                break;
            case TEACHER:
                List<Student> students = studentDao.findAll(Sort.by(Sort.Direction.ASC, "surname"));
                LinkedHashMap<Student, LinkedHashMap<Subject, List<Grade>>> gradesBySubjectAndStudent = new LinkedHashMap<>();

                for (var student : students) {
                    gradesBySubject = new LinkedHashMap<>();
                    for (var subject : subjects) {
                        var grades = gradeDao.findAllByStudentLoginAndSubjectName(student.getLogin(), subject.getName());
                        gradesBySubject.put(subject, grades);
                        gradesMaxCount = Math.max(gradesMaxCount, grades.size());
                    }

                    gradesBySubjectAndStudent.put(student, gradesBySubject);
                }

                m.addAttribute("studentsGrades", gradesBySubjectAndStudent);
                m.addAttribute("students", students);
                m.addAttribute("subjects", subjects);
                m.addAttribute("grade", new Grade());
                break;
        }
        m.addAttribute("grades", gradeDao.findAll());
        m.addAttribute("gradesMaxCount", gradesMaxCount);
        m.addAttribute("user", user);

        return "index";
    }

    @GetMapping("/editUser")
    public String editUser(Model m, Principal principal) {
        m.addAttribute("user", userDao.findByLogin(principal.getName()));
        return "edit";
    }

    @PostMapping("/editUser")
    public String editUserPUT(@Valid User user, BindingResult binding, Principal principal) {
        if (binding.hasErrors()) {
            return "edit";
        }

        User user1 = userDao.findByLogin(user.getLogin());
        if (Objects.equals(principal.getName(), user1.getLogin())) {
            user1.setName(user.getName());
            user1.setSurname(user.getSurname());
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
            switch (user1.getRole()) {
                case STUDENT:
                    Student student = studentDao.findByLogin(user1.getLogin());
                    studentDao.save(student);
                    break;
                case TEACHER:
                    Teacher teacher = teacherDao.findByLogin(user1.getLogin());
                    teacherDao.save(teacher);
                    break;
            }
            return "redirect:/";
        } else {
            binding.addError(new ObjectError("Wrong account logged-in", "The login provided does not match the current logged in user"));
            return "edit";
        }
    }

    @GetMapping("/deleteUser")
    public String deleteUser(Model m, Principal principal) {
        m.addAttribute("user", userDao.findByLogin(principal.getName()));
        return "delete";
    }

    @PostMapping("deleteUser")
    public String deleteUserDELETE(@ModelAttribute User user, Principal principal) {
        User user1 = userDao.findByLogin(user.getLogin());
        if (Objects.equals(principal.getName(), user1.getLogin())) {
            userDao.delete(user1);
            return "redirect:/logout";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/addGrade/{studentLogin}")
    public String addGrade(@PathVariable String studentLogin, Model m, Principal principal) {
        m.addAttribute("user", userDao.findByLogin(principal.getName()));
        m.addAttribute("student", studentDao.findByLogin(studentLogin));
        m.addAttribute("grade", new Grade());
        m.addAttribute("subjects", subjectDao.findAll());
        return "grade/add";
    }

    @PostMapping("/addGrade")
    public String addGradePOST(@RequestParam("studentLogin") String studentLogin, @RequestParam("subjectName") String subjectName, @Valid Grade grade, BindingResult binding, Principal principal) {
        if (binding.hasErrors()) {
            return "redirect:/addGrade/" + studentLogin;
        }

        User user = userDao.findByLogin(principal.getName());
        if (user.getRole() == UserRoles.TEACHER) {
            Teacher teacher = teacherDao.findByLogin(user.getLogin());
            Student student = studentDao.findByLogin(studentLogin);
            Subject subject = subjectDao.findByName(subjectName);

            grade.setTeacher(teacher);
            grade.setStudent(student);
            grade.setSubject(subject);
            gradeDao.save(grade);
        } else {
            binding.addError(new ObjectError("No permissions", "The logged-in user is not teacher"));
            return "redirect:/addGrade/" + studentLogin;
        }
        return "redirect:/";
    }
}