package cn.fu.service;
 
import java.util.Date;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import cn.fu.entity.User;

 

@WebService
@SOAPBinding(style = Style.RPC)
@SuppressWarnings("deprecation")
public class ComplexUserService implements IComplexUserService {
    
    public User getUserByName(@WebParam(name = "name") String name) {
        User user = new User();
        user.setId(new Date().getSeconds());
        user.setName(name);
        user.setAddress("china");
        user.setEmail(name + "@hoo.com");
        return user;
    }
    
    public void setUser(User user) {
        System.out.println("############Server setUser###########");
        System.out.println("setUser:" + user);
    }
}