/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author NguyenDucAnh
 */
public class UserData {

    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public boolean isDuplicatedAccountName(String useraccount) {
        for (User account : userList) {
            if (account.getAccountName().equals(useraccount)) {
                return true;
            }
        }
        return false;
    }

    public boolean loginAccout(User login) {
        for (User userAccount : userList) {
            if (userAccount.getAccountName().equals(login.getAccountName())
                    && userAccount.getPassword().equals(login.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
