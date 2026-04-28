package course_manager_v2.controller;

import course_manager_v2.model.User;

public interface UserController<T extends User>{
    void showMenu(T user);
}
