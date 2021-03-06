/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.User;
import com.smgb.projetsmgb.bean.UserDevice;
import com.smgb.projetsmgb.controller.util.DeviceUtil;
import com.smgb.projetsmgb.controller.util.HashageUtil;
import com.smgb.projetsmgb.controller.util.SessionUtil;
import java.net.UnknownHostException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ACER
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    @EJB
    UserDeviceFacade userDeviceFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public int seConnecter(User user, UserDevice device) {
        User loadedUser = find(user.getLogin());
        if (loadedUser == null) {
            return -4;
        } else if (!loadedUser.getPassword().equals(HashageUtil.sha256(user.getPassword()))) {
            if (loadedUser.getNbrCnx() < 3) {
                loadedUser.setNbrCnx(loadedUser.getNbrCnx() + 1);
            } else if (loadedUser.getNbrCnx() > 3) {
                loadedUser.setBlocked(1);
            }
            return -3;
        } else if (loadedUser.getBlocked() == 1) {
            return -2;
        } else {
            loadedUser.setNbrCnx(0);
            user = clone(loadedUser);
            user.setPassword(null);
            SessionUtil.registerUser(user);
            int res = userDeviceFacade.checkDevice(user, device);
            if (res > 0) {
                if (res == 1) {
                    userDeviceFacade.save(device, user);
                }
                return 1;
            } else {
                return -1;
            }
        }
    }
    
     private void clone(User userSource,User userDestination){
        userDestination.setBlocked(userSource.getBlocked());
        userDestination.setUserDevices(userSource.getUserDevices());
        userDestination.setNbrCnx(userSource.getNbrCnx());
        userDestination.setEmail(userSource.getEmail());
        userDestination.setNom(userSource.getNom());
        userDestination.setLogin(userSource.getLogin());
        userDestination.setPassword(userSource.getPassword());
        userDestination.setPrenom(userSource.getPrenom());
        userDestination.setTel(userSource.getTel());
        userDestination.setMdpChanged(userSource.isMdpChanged());
        userDestination.setQuestion(userSource.getQuestion());
        userDestination.setReponse(userSource.getReponse());
    }
   
    
    public User clone(User user){
        User cloned = new User();
        clone(user,cloned);
         return cloned;
    }
    public int addUser(User user){
        if(user!=null){
        user.setPassword(HashageUtil.sha256(user.getPassword()));
            edit(user);
            System.out.println("user"+user);
        return 1;
        }
        return -1;
    }
    
    public void seDeconnecter(){
        SessionUtil.getSession().invalidate();
    }
    
    public int verifierReponseEtTel(User user, String reponse, String tel) throws UnknownHostException {
        User loaded = find(user.getLogin());
        if (reponse.equals(loaded.getReponse()) && tel.equals(loaded.getTel())) {
            userDeviceFacade.save(DeviceUtil.getDevice(), loaded);
            return 1;
        } else {
            loaded.setBlocked(1);
            edit(loaded);
            return -1;
        }
    }
    
}
