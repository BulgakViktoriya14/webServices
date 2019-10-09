package by.deniskruglik.soapwebservice.service.endpoints;

import by.bsu.soap_web_service.GetUserByIdRequest;
import by.bsu.soap_web_service.GetUserByIdResponse;
import by.bsu.soap_web_service.User;
import by.deniskruglik.soapwebservice.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigInteger;

@Endpoint
public class UserEndpoint {
    private UserDAO userDAO;

    @Autowired
    public UserEndpoint(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @PayloadRoot(namespace = EndpointSharedConstants.NAMESPACE_URI, localPart = "getUserByIdRequest")
    @ResponsePayload
    public GetUserByIdResponse getUserById(@RequestPayload GetUserByIdRequest request) {
        GetUserByIdResponse response = new GetUserByIdResponse();
        by.deniskruglik.soapwebservice.service.models.User userFromDB = this.userDAO.getById(request.getId().longValue());
        User user = new User();
        user.setId(BigInteger.valueOf(userFromDB.getId()));
        user.setNickname(userFromDB.getNickname());
        response.setUser(user);
        return response;
    }
}
