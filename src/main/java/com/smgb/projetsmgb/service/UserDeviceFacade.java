/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.User;
import com.smgb.projetsmgb.bean.UserDevice;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ACER
 */
@Stateless
public class UserDeviceFacade extends AbstractFacade<UserDevice> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    public static List<UserDevice> devices = new ArrayList<>();
    
    public int saveDevice(UserDevice device){
        if(device.getUser()!=null){
        create(device);
        devices.add(device);
            System.out.println(""+device);
        return 1;
        }
        return -1;
        
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserDeviceFacade() {
        super(UserDevice.class);
    }
    
    public int checkDevice(User user, UserDevice connectedDevice) {
        List<UserDevice> list = findDeviceByUser(user);
        if (list.isEmpty()) {
            return 1;
        } else {

            for (int i = 0; i < list.size(); i++) {
                UserDevice device = list.get(i);
                if (device.getDeviceCategorie().equals(connectedDevice.getDeviceCategorie())
                        && device.getBrowser().equals(connectedDevice.getBrowser())
                        && device.getOperatingSystem().equals(connectedDevice.getOperatingSystem())) {
                    return 2;
                }
            }
        }
        return -1;
    }
    
    public List<UserDevice> findDeviceByUser(User user) {
        if (user == null || user.getLogin() == null) {
            return new ArrayList<>();
        } else {
            String req = "SELECT d FROM UserDevice d WHERE d.user.login='" + user.getLogin() + "'";
            return em.createQuery(req).getResultList();
        }
    }

    public UserDevice findAdress(UserDevice device) throws java.net.UnknownHostException {
        InetAddress ip;
        try {
            
            ip = InetAddress.getLocalHost();
            System.out.println("Current IP address : " + ip.getHostAddress());
            device.setAdressIp(ip.getHostAddress());
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            
            byte[] mac = network.getHardwareAddress();
            if (mac != null) {
                System.out.print("Current MAC address : ");
                
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                System.out.println(sb.toString());
                device.setAdressMac(sb.toString());
                return device;
                
            }
        } catch (SocketException e) {
            
            e.printStackTrace();
            
        }
        return device;
    }
    
    public void save(UserDevice device, User user){
        device.setUser(user);
        create(device);
    }
    
}
