package team.light.cyberfarm.service;

import lombok.NonNull;
import team.light.cyberfarm.entity.User;

public interface AddUser {

    int addUser(User user);
    int addNormalUser(@NonNull User user);
}
