package course_manager_v1.controller;

import course_manager_v1.model.User;

import java.util.Scanner;
public interface UserController<T extends User> {
    void showMenu(T user, Scanner sc);
}