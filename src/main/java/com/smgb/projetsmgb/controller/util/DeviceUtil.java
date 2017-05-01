package com.smgb.projetsmgb.controller.util;

import com.smgb.projetsmgb.bean.UserDevice;
import com.smgb.projetsmgb.service.UserDeviceFacade;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;
import eu.bitwalker.useragentutils.*;
import static java.lang.System.out;
import java.net.UnknownHostException;
import javax.ejb.EJB;

public class DeviceUtil {

    private static final DeviceUtil instance = new DeviceUtil();
    
    public static DeviceUtil getInstance() {
        return instance;
    }

    private DeviceUtil() {
    }

    private static ReadableUserAgent getUserAgent() {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
        ReadableUserAgent agent = parser.parse(httpServletRequest.getHeader("User-Agent"));
        return agent;
    }

    public static UserDevice getDevice() throws UnknownHostException {
        ReadableUserAgent ag = getUserAgent();
        UserDevice device = new UserDevice();
//        device.setBrowser(ag.getFamily().getName());
//        device.setOperatingSystem(ag.getOperatingSystem().getName());
//        device.setDeviceCategorie(ag.getDeviceCategory().getName());
//        System.out.println("Device :: family :: " + ag.getFamily().getName());
//        System.out.println("Operating système :: " + ag.getOperatingSystem().getName());
//        System.out.println("Device cat :: " + ag.getDeviceCategory().getName());
//        System.out.println("Systeme version number :: " + ag.getOperatingSystem().getVersionNumber());
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        String userAgent = externalContext.getRequestHeaderMap().get("User-Agent");
//        System.out.println("Header :: "+userAgent);
//        return device;
        device.setOperatingSystem(ag.getOperatingSystem().getName());
        device.setDeviceCategorie(ag.getDeviceCategory().getName());
        System.out.println("Device :: family :: " + ag.getFamily().getName());
        System.out.println("Operating système :: " + ag.getOperatingSystem().getName());
        System.out.println("Device cat :: " + ag.getDeviceCategory().getName());
        System.out.println("Systeme version number :: " + ag.getOperatingSystem().getVersionNumber());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String userAgent = externalContext.getRequestHeaderMap().get("User-Agent");
        UserAgent ua = UserAgent.parseUserAgentString(userAgent);
        Version browserVersion = (Version) ua.getBrowserVersion();
        String browserName = ua.getBrowser().toString();
       // int majVersion = Integer.parseInt(browserVersion.getVersion());
        device.setBrowser(browserName);
//        device.setAdresseIP(browserVersion.getVersion());
        System.out.println("Header :: " + browserName+" :: "+browserVersion.getVersion());
        return device;
    }

}
