package cn.fu.service;
 
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import cn.fu.entity.User;

 

@WebService
@SOAPBinding(style = Style.RPC)
public interface IComplexUserService {
    
    public User getUserByName(@WebParam(name = "name") String name);
    
    public void setUser(User user);
}